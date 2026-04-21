package co.edu.javeriana.ddd.estudiantes.domain.events;

import co.edu.javeriana.ddd.shared.domain.events.DomainEvent;
import java.util.UUID;

public class EstudianteRegistrado extends DomainEvent {
    private final UUID estudianteId;
    private final String nombre;
    private final String email;

    public EstudianteRegistrado(UUID estudianteId, String nombre, String email) {
        super();
        this.estudianteId = estudianteId;
        this.nombre = nombre;
        this.email = email;
    }

    public UUID getEstudianteId() {
        return estudianteId;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
