package co.edu.javeriana.ddd.estudiantes.domain.valueobjects;

import java.util.Objects;

public record NombreCompleto(String nombres, String apellidos) {
    public NombreCompleto {
        Objects.requireNonNull(nombres, "Los nombres no pueden ser nulos");
        Objects.requireNonNull(apellidos, "Los apellidos no pueden ser nulos");
        if (nombres.isBlank()) throw new IllegalArgumentException("Los nombres no pueden estar vacíos");
        if (apellidos.isBlank()) throw new IllegalArgumentException("Los apellidos no pueden estar vacíos");
    }

    public String nombreCompleto() {
        return nombres + " " + apellidos;
    }
}
