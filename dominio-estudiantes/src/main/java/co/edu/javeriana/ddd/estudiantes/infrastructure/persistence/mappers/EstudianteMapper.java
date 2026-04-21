package co.edu.javeriana.ddd.estudiantes.infrastructure.persistence.mappers;

import co.edu.javeriana.ddd.estudiantes.domain.factories.EstudianteBuilder;
import co.edu.javeriana.ddd.estudiantes.domain.model.Estudiante;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstudianteId;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.NombreCompleto;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.PromedioPonderado;
import co.edu.javeriana.ddd.estudiantes.infrastructure.persistence.entities.EstudianteEntity;
import co.edu.javeriana.ddd.shared.domain.valueobjects.Email;

public class EstudianteMapper {

    public static EstudianteEntity toEntity(Estudiante estudiante) {
        if (estudiante == null) return null;
        
        EstudianteEntity entity = new EstudianteEntity();
        entity.setId(estudiante.getId().value());
        entity.setNombres(estudiante.getNombre().nombres());
        entity.setApellidos(estudiante.getNombre().apellidos());
        entity.setEmail(estudiante.getEmail().value());
        entity.setEstado(estudiante.getEstado());
        entity.setPromedio(estudiante.getPromedio().value());
        return entity;
    }

    public static Estudiante toDomain(EstudianteEntity entity) {
        if (entity == null) return null;

        return new EstudianteBuilder()
            .id(new EstudianteId(entity.getId()))
            .nombre(new NombreCompleto(entity.getNombres(), entity.getApellidos()))
            .email(new Email(entity.getEmail()))
            .estado(entity.getEstado())
            .promedio(new PromedioPonderado(entity.getPromedio()))
            .build();
    }
}
