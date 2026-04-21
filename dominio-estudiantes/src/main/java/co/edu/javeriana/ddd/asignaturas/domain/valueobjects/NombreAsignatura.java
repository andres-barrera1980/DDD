package co.edu.javeriana.ddd.asignaturas.domain.valueobjects;

import java.util.Objects;

public record NombreAsignatura(String value) {
    public NombreAsignatura {
        Objects.requireNonNull(value, "El nombre de la asignatura no puede ser nulo");
        if (value.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío");
    }
}
