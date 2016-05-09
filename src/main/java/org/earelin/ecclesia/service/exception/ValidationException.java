package org.earelin.ecclesia.service.exception;

/**
 * Exception to report an critical validation error for a dto in service layer
 */
public class ValidationException extends RuntimeException {

    public ValidationException(String message) {
        super(message);
    }

}
