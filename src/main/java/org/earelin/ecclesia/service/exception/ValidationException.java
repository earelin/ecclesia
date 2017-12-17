package org.earelin.ecclesia.service.exception;

/**
 * Exception to report an critical validation error for a dto in service layer
 */
public class ValidationException extends RuntimeException {
    
    static final long serialVersionUID = 1L;

    public ValidationException(String message) {
        super(message);
    }

}
