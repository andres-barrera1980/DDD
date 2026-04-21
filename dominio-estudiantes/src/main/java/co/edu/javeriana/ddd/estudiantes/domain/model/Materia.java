package co.edu.javeriana.ddd.estudiantes.domain.model;

import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.AsignaturaId;
import co.edu.javeriana.ddd.asignaturas.domain.valueobjects.Creditos;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.Calificacion;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstadoMateria;

import java.util.Objects;

public class Materia {
    private final AsignaturaId asignaturaId;
    private final Creditos creditos;
    private EstadoMateria estado;
    private Calificacion calificacion;

    public Materia(AsignaturaId asignaturaId, Creditos creditos) {
        this.asignaturaId = Objects.requireNonNull(asignaturaId);
        this.creditos = Objects.requireNonNull(creditos);
        this.estado = EstadoMateria.EN_CURSO;
        this.calificacion = Calificacion.vacia();
    }

    public void registrarCalificacion(Calificacion nota) {
        if (this.estado != EstadoMateria.EN_CURSO) {
            throw new IllegalStateException("Solo se puede calificar una materia en curso");
        }
        this.calificacion = Objects.requireNonNull(nota);
        this.estado = nota.esAprobatoria() ? EstadoMateria.APROBADA : EstadoMateria.REPROBADA;
    }

    public void retirar() {
        if (this.estado != EstadoMateria.EN_CURSO) {
            throw new IllegalStateException("Solo se puede retirar una materia en curso");
        }
        this.estado = EstadoMateria.RETIRADA;
    }

    public AsignaturaId getAsignaturaId() { return asignaturaId; }
    public Creditos getCreditos() { return creditos; }
    public EstadoMateria getEstado() { return estado; }
    public Calificacion getCalificacion() { return calificacion; }
}
