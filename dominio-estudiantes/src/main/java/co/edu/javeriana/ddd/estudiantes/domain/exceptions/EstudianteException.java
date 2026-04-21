package co.edu.javeriana.ddd.estudiantes.domain.exceptions;

import co.edu.javeriana.ddd.shared.domain.exceptions.DomainException;

public class EstudianteException extends DomainException {
    public EstudianteException(String message) {
        super(message);
    }
}
