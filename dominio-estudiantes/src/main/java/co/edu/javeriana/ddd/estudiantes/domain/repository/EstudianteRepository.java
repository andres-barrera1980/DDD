package co.edu.javeriana.ddd.estudiantes.domain.repository;

import co.edu.javeriana.ddd.estudiantes.domain.model.Estudiante;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstudianteId;
import co.edu.javeriana.ddd.shared.domain.valueobjects.Email;

import java.util.List;
import java.util.Optional;

public interface EstudianteRepository {
    void guardar(Estudiante estudiante);
    Optional<Estudiante> obtenerPorId(EstudianteId id);
    List<Estudiante> obtenerTodos();
    void eliminar(EstudianteId id);
    Optional<Estudiante> buscarPorEmail(Email email);
}
