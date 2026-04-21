package co.edu.javeriana.ddd.estudiantes.domain.valueobjects;

public record Calificacion(Float value) {
    public static final Float MIN_NOTA = 0.0f;
    public static final Float MAX_NOTA = 5.0f;
    public static final Float NOTA_APROBACION = 3.0f;

    public Calificacion {
        if (value != null && (value < MIN_NOTA || value > MAX_NOTA)) {
            throw new IllegalArgumentException("La calificación debe estar entre " + MIN_NOTA + " y " + MAX_NOTA);
        }
    }

    public static Calificacion vacia() {
        return new Calificacion(null);
    }

    public boolean esAprobatoria() {
        return value != null && value >= NOTA_APROBACION;
    }
}
