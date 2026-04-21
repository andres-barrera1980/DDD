package co.edu.javeriana.ddd.estudiantes.application.usecases;

import co.edu.javeriana.ddd.estudiantes.application.commands.RegistrarEstudianteCommand;
import co.edu.javeriana.ddd.estudiantes.domain.exceptions.EstudianteException;
import co.edu.javeriana.ddd.estudiantes.domain.model.Estudiante;
import co.edu.javeriana.ddd.estudiantes.domain.repository.EstudianteRepository;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstudianteId;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.NombreCompleto;
import co.edu.javeriana.ddd.shared.domain.valueobjects.Email;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegistrarEstudiante {

    private static final Logger log = LoggerFactory.getLogger(RegistrarEstudiante.class);
    private final EstudianteRepository repository;

    public RegistrarEstudiante(EstudianteRepository repository) {
        this.repository = repository;
    }

    public void execute(RegistrarEstudianteCommand command) {
        log.info("Registrando nuevo estudiante: {}", command.email());
        // buscar si ya existe un estudiante con el mismo email
        if (repository.buscarPorEmail(new Email(command.email())).isPresent()) {
            log.warn("Intento de registro con email ya existente: {}", command.email());
            throw new EstudianteException("Ya existe un estudiante registrado con este email");
        }
        Estudiante estudiante = Estudiante.registrar(
                EstudianteId.nextId(),
                new NombreCompleto(command.nombres(), command.apellidos()),
                new Email(command.email()));

        repository.guardar(estudiante);
        log.info("Estudiante registrado exitosamente con ID: {}", command.id());
    }
}
