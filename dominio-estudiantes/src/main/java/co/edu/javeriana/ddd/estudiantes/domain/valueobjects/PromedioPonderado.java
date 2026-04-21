package co.edu.javeriana.ddd.estudiantes.domain.valueobjects;

public record PromedioPonderado(Float value) {
    public static final Float MIN_VALOR = 0.0f;
    public static final Float MAX_VALOR = 5.0f;
    public static final Float UMBRAL_NORMAL = 3.25f;
    public static final Float UMBRAL_EXCLUSION = 2.5f;

    public PromedioPonderado {
        if (value != null && (value < MIN_VALOR || value > MAX_VALOR)) {
            throw new IllegalArgumentException("El promedio debe estar entre " + MIN_VALOR + " y " + MAX_VALOR);
        }
    }

    public static PromedioPonderado sinCalcular() {
        return new PromedioPonderado(null);
    }

    public boolean tieneValor() {
        return value != null;
    }

    public boolean esBajo() {
        return tieneValor() && value < UMBRAL_NORMAL;
    }

    public boolean esCritico() {
        return tieneValor() && value < UMBRAL_EXCLUSION;
    }
}
