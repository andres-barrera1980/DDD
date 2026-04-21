package co.edu.javeriana.ddd.estudiantes.infrastructure.persistence.entities;

import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstadoEstudiante;
import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "estudiantes")
public class EstudianteEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoEstudiante estado;

    @Column(nullable = false)
    private Float promedio;

    public EstudianteEntity() {
        // Para JPA
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public EstadoEstudiante getEstado() { return estado; }
    public void setEstado(EstadoEstudiante estado) { this.estado = estado; }
    public Float getPromedio() { return promedio; }
    public void setPromedio(Float promedio) { this.promedio = promedio; }
}
