package org.earelin.ecclesia.service.exception;

/**
 * Exception raised when a service cannon found an entity
 */
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

}
