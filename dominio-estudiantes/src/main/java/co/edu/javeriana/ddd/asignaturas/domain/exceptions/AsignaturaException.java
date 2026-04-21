package co.edu.javeriana.ddd.asignaturas.domain.exceptions;

import co.edu.javeriana.ddd.shared.domain.exceptions.DomainException;

public class AsignaturaException extends DomainException {
    public AsignaturaException(String message) {
        super(message);
    }
}
