package co.edu.javeriana.ddd.estudiantes.domain.valueobjects;

import java.util.Objects;
import java.util.UUID;

public record EstudianteId(UUID value) {
    public EstudianteId {
        Objects.requireNonNull(value, "El ID del estudiante no puede ser nulo");
    }

    public static EstudianteId nextId() {
        return new EstudianteId(UUID.randomUUID());
    }

    public static EstudianteId fromString(String id) {
        return new EstudianteId(UUID.fromString(id));
    }
}
