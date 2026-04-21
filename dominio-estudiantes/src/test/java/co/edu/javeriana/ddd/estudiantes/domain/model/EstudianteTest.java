package co.edu.javeriana.ddd.estudiantes.domain.model;

import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.AsignaturaId;
import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.Creditos;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.*;
import co.edu.javeriana.ddd.shared.domain.valueobjects.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class EstudianteTest {

    private Estudiante estudiante;

    @BeforeEach
    void setUp() {
        estudiante = Estudiante.registrar(
                new EstudianteId(UUID.randomUUID()),
                new NombreCompleto("Juan", "Perez"),
                new Email("juan.perez@javeriana.edu.co")
        );
    }

    @Test
    void debeRegistrarseEnEstadoNormal() {
        assertEquals(EstadoEstudiante.NORMAL, estudiante.getEstado());
        assertFalse(estudiante.getPromedio().tieneValor());
    }

    @Test
    void debeTransitarAExcluidoSiPromedioEsCritico() {
        // Para pruebas, forzamos un promedio a través de materias o exponemos un método de test
        // Dado que actualizarPromedio es privado, usaremos una materia con nota baja
        inscribirYCalificar("MAT-CRITICA", 2.0f);
        estudiante.recalcularPromedioGlobal();
        assertEquals(EstadoEstudiante.EXCLUIDO, estudiante.getEstado());
    }

    @Test
    void debeTransitarAPruebaSiPromedioEsBajo() {
        inscribirYCalificar("MAT-BAJA", 3.0f);
        estudiante.recalcularPromedioGlobal();
        assertEquals(EstadoEstudiante.PRUEBA_ACADEMICA, estudiante.getEstado());
    }

    private void inscribirYCalificar(String codigo, Float nota) {
        PeriodoId periodo = new PeriodoId("202610");
        estudiante.inscribirMateria(periodo, new AsignaturaId(codigo), new Creditos(3));
        estudiante.getHistoriaAcademica().get(0).getMaterias().stream()
                .filter(m -> m.getAsignaturaId().value().equals(codigo))
                .findFirst().ifPresent(m -> m.registrarCalificacion(new Calificacion(nota)));
    }

    @Test
    void debeRecalcularPromedioCorrectamenteConMaterias() {
        PeriodoId periodo = new PeriodoId("202610");
        Creditos tresCreditos = new Creditos(3);
        
        estudiante.inscribirMateria(periodo, new AsignaturaId("MAT101"), tresCreditos);
        
        // Registrar nota para aprobar
        estudiante.getHistoriaAcademica().get(0).getMaterias().get(0)
                .registrarCalificacion(new Calificacion(4.0f));
        
        estudiante.recalcularPromedioGlobal();
        
        assertEquals(4.0f, estudiante.getPromedio().value());
        assertEquals(EstadoEstudiante.NORMAL, estudiante.getEstado());
    }
}
