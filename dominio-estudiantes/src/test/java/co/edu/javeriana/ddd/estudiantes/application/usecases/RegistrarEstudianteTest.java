package co.edu.javeriana.ddd.estudiantes.application.usecases;

import co.edu.javeriana.ddd.estudiantes.application.commands.RegistrarEstudianteCommand;
import co.edu.javeriana.ddd.estudiantes.domain.model.Estudiante;
import co.edu.javeriana.ddd.estudiantes.domain.repository.EstudianteRepository;
import co.edu.javeriana.ddd.estudiantes.domain.valueobjects.EstudianteId;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

class RegistrarEstudianteTest {

    @Test
    void debeRegistrarEstudianteExitosamente() {
        EstudianteRepository repository = Mockito.mock(EstudianteRepository.class);
        RegistrarEstudiante useCase = new RegistrarEstudiante(repository);
        RegistrarEstudianteCommand command = new RegistrarEstudianteCommand(
                UUID.randomUUID().toString(), "Juan", "Perez", "juan@javeriana.edu.co"
        );

        useCase.execute(command);

        verify(repository).guardar(any(Estudiante.class));
    }
}
