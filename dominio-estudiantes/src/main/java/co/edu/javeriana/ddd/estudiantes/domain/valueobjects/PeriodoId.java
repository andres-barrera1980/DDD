package co.edu.javeriana.ddd.estudiantes.domain.valueobjects;

import java.util.Objects;

public record PeriodoId(String value) {
    public PeriodoId {
        Objects.requireNonNull(value, "El identificador del periodo no puede ser nulo");
        if (!value.matches("\\d{4}[1-2]0")) {
            throw new IllegalArgumentException("Formato de periodo inválido (ej: 202610)");
        }
    }
}
