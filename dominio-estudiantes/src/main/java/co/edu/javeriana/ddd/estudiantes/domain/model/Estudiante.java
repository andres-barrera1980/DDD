package co.edu.javeriana.ddd.estudiantes.domain.model;

import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.AsignaturaId;
import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.Creditos;
import co.edu.javeriana.ddd.estudiantes.domain.events.EstudianteRegistrado;
import co.edu.javeriana.ddd.estudiantes.domain.events.SituacionAcademicaCambiada;
import co.edu.javeriana.ddd.estudiantes.domain.exceptions.EstudianteException;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.*;
import co.edu.javeriana.ddd.shared.domain.model.AggregateRoot;
import co.edu.javeriana.ddd.shared.domain.valueobjects.Email;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Estudiante extends AggregateRoot {

    private final EstudianteId id;
    private NombreCompleto nombre;
    private final Email email;
    private EstadoEstudiante estado;
    private PromedioPonderado promedio;
    private final List<PeriodoAcademico> historiaAcademica;

    private Estudiante(EstudianteId id, NombreCompleto nombre, Email email, EstadoEstudiante estado, PromedioPonderado promedio, List<PeriodoAcademico> historia) {
        this.id = Objects.requireNonNull(id);
        this.nombre = Objects.requireNonNull(nombre);
        this.email = Objects.requireNonNull(email);
        this.estado = Objects.requireNonNull(estado);
        this.promedio = Objects.requireNonNull(promedio);
        this.historiaAcademica = new ArrayList<>(historia);
    }

    // --- Factory Methods ---

    public static Estudiante registrar(EstudianteId id, NombreCompleto nombre, Email email) {
        return new Estudiante(id, nombre, email, EstadoEstudiante.NORMAL, PromedioPonderado.sinCalcular(), new ArrayList<>());
    }

    public static Estudiante reconstruir(EstudianteId id, NombreCompleto nombre, Email email, EstadoEstudiante estado, PromedioPonderado promedio, List<PeriodoAcademico> historia) {
        return new Estudiante(id, nombre, email, estado, promedio, historia);
    }

    // --- Business Methods ---

    public void inscribirMateria(PeriodoId periodoId, AsignaturaId asignaturaId, Creditos creditos) {
        validarEstadoParaInscripcion();
        PeriodoAcademico periodo = buscarOCrearPeriodo(periodoId);
        periodo.inscribir(asignaturaId, creditos);
    }

    public void cambiarNombre(NombreCompleto nuevoNombre) {
        this.nombre = Objects.requireNonNull(nuevoNombre);
    }

    public void recalcularPromedioGlobal() {
        float sumaNotasPorCreditos = 0;
        int totalCreditos = 0;

        for (PeriodoAcademico p : historiaAcademica) {
            for (Materia m : p.getMaterias()) {
                if (m.getEstado() == EstadoMateria.APROBADA || m.getEstado() == EstadoMateria.REPROBADA) {
                    sumaNotasPorCreditos += (m.getCalificacion().value() * m.getCreditos().value());
                    totalCreditos += m.getCreditos().value();
                }
            }
        }

        PromedioPonderado nuevoPromedio = totalCreditos == 0 ? PromedioPonderado.sinCalcular() : new PromedioPonderado(sumaNotasPorCreditos / totalCreditos);
        actualizarPromedio(nuevoPromedio);
    }

    private void actualizarPromedio(PromedioPonderado nuevoPromedio) {
        if (this.estado == EstadoEstudiante.EXCLUIDO || this.estado == EstadoEstudiante.GRADUADO) {
            throw new EstudianteException("No se puede actualizar el promedio de un estudiante en estado " + this.estado);
        }
        this.promedio = Objects.requireNonNull(nuevoPromedio);
        evaluarEstadoAcademico();

        this.recordEvent(new SituacionAcademicaCambiada(
            id.value(),
            promedio.tieneValor() ? promedio.value() : null,
            estado
        ));
    }

    private void evaluarEstadoAcademico() {
        if (!promedio.tieneValor()) return;
        if (promedio.esCritico()) {
            this.estado = EstadoEstudiante.EXCLUIDO;
            return;
        }

        if (promedio.esBajo()) {
            if (this.estado == EstadoEstudiante.NORMAL) this.estado = EstadoEstudiante.PRUEBA_ACADEMICA;
            else if (this.estado == EstadoEstudiante.PRUEBA_ACADEMICA) this.estado = EstadoEstudiante.MATRICULA_CONDICIONAL;
        } else {
            if (this.estado == EstadoEstudiante.PRUEBA_ACADEMICA || this.estado == EstadoEstudiante.MATRICULA_CONDICIONAL) {
                this.estado = EstadoEstudiante.NORMAL;
            }
        }
    }

    private void validarEstadoParaInscripcion() {
        if (this.estado == EstadoEstudiante.EXCLUIDO || this.estado == EstadoEstudiante.SUSPENDIDO) {
            throw new EstudianteException("El estudiante no puede inscribir materias en estado " + this.estado);
        }
    }

    private PeriodoAcademico buscarOCrearPeriodo(PeriodoId periodoId) {
        return historiaAcademica.stream()
                .filter(p -> p.getPeriodoId().equals(periodoId))
                .findFirst()
                .orElseGet(() -> {
                    PeriodoAcademico nuevo = new PeriodoAcademico(periodoId);
                    this.historiaAcademica.add(nuevo);
                    return nuevo;
                });
    }

    // --- Getters ---

    public EstudianteId getId() { return id; }
    public NombreCompleto getNombre() { return nombre; }
    public Email getEmail() { return email; }
    public EstadoEstudiante getEstado() { return estado; }
    public PromedioPonderado getPromedio() { return promedio; }
    public List<PeriodoAcademico> getHistoriaAcademica() { return Collections.unmodifiableList(historiaAcademica); }
}
