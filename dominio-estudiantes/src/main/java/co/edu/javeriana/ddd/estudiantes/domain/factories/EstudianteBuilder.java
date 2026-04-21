package co.edu.javeriana.ddd.estudiantes.domain.factories;

import co.edu.javeriana.ddd.estudiantes.domain.model.Estudiante;
import co.edu.javeriana.ddd.estudiantes.domain.model.PeriodoAcademico;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstadoEstudiante;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstudianteId;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.NombreCompleto;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.PromedioPonderado;
import co.edu.javeriana.ddd.shared.domain.valueobjects.Email;

import java.util.ArrayList;
import java.util.List;

public class EstudianteBuilder {
    private EstudianteId id;
    private NombreCompleto nombre;
    private Email email;
    private EstadoEstudiante estado;
    private PromedioPonderado promedio;
    private List<PeriodoAcademico> historia = new ArrayList<>();

    public EstudianteBuilder id(EstudianteId id) { this.id = id; return this; }
    public EstudianteBuilder nombre(NombreCompleto nombre) { this.nombre = nombre; return this; }
    public EstudianteBuilder email(Email email) { this.email = email; return this; }
    public EstudianteBuilder estado(EstadoEstudiante estado) { this.estado = estado; return this; }
    public EstudianteBuilder promedio(PromedioPonderado promedio) { this.promedio = promedio; return this; }
    public EstudianteBuilder historia(List<PeriodoAcademico> historia) { this.historia = historia; return this; }

    public Estudiante build() {
        return Estudiante.reconstruir(id, nombre, email, estado, promedio, historia);
    }
}
