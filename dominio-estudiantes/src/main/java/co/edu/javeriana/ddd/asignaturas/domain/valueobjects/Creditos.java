package co.edu.javeriana.ddd.asignaturas.domain.valueobjects;

import java.util.Objects;

public record Creditos(Integer value) {
    public static final Integer MIN_CREDITOS = 1;
    public static final Integer MAX_CREDITOS = 10;

    public Creditos {
        Objects.requireNonNull(value, "Los créditos no pueden ser nulos");
        if (value < MIN_CREDITOS || value > MAX_CREDITOS) {
            throw new IllegalArgumentException("Los créditos deben estar entre " + MIN_CREDITOS + " y " + MAX_CREDITOS);
        }
    }
}
