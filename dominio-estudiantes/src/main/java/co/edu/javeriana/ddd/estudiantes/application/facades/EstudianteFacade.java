package co.edu.javeriana.ddd.estudiantes.application.facades;

import co.edu.javeriana.ddd.estudiantes.application.commands.RegistrarEstudianteCommand;
import co.edu.javeriana.ddd.estudiantes.application.usecases.RegistrarEstudiante;

public class EstudianteFacade {
    private final RegistrarEstudiante registrarEstudiante;

    public EstudianteFacade(RegistrarEstudiante registrarEstudiante) {
        this.registrarEstudiante = registrarEstudiante;
    }

    public void registrar(RegistrarEstudianteCommand command) {
        registrarEstudiante.execute(command);
    }
}
