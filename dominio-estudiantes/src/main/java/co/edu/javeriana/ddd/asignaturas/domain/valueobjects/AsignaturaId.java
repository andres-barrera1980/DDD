package co.edu.javeriana.ddd.asignaturas.domain.valueobjects;

import java.util.Objects;

public record AsignaturaId(String value) {
    public AsignaturaId {
        Objects.requireNonNull(value, "El código de la asignatura no puede ser nulo");
        if (value.isBlank()) throw new IllegalArgumentException("El código no puede estar vacío");
    }
}
