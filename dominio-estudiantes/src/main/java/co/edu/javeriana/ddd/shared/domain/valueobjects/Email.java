package co.edu.javeriana.ddd.shared.domain.valueobjects;

import java.util.Objects;
import java.util.regex.Pattern;

public record Email(String value) {
    private static final Pattern PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@(.+)$");

    public Email {
        Objects.requireNonNull(value, "El correo electrónico no puede ser nulo");
        if (!PATTERN.matcher(value).matches()) {
            throw new IllegalArgumentException("Formato de correo electrónico inválido");
        }
    }
}
