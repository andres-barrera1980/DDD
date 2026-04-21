package co.edu.javeriana.ddd.estudiantes.domain.events;

import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstadoEstudiante;
import co.edu.javeriana.ddd.shared.domain.events.DomainEvent;
import java.util.UUID;

public class SituacionAcademicaCambiada extends DomainEvent {
    private final UUID estudianteId;
    private final Float nuevoPromedio;
    private final EstadoEstudiante nuevoEstado;

    public SituacionAcademicaCambiada(UUID estudianteId, Float nuevoPromedio, EstadoEstudiante nuevoEstado) {
        super();
        this.estudianteId = estudianteId;
        this.nuevoPromedio = nuevoPromedio;
        this.nuevoEstado = nuevoEstado;
    }

    public UUID getEstudianteId() {
        return estudianteId;
    }

    public Float getNuevoPromedio() {
        return nuevoPromedio;
    }

    public EstadoEstudiante getNuevoEstado() {
        return nuevoEstado;
    }
}
