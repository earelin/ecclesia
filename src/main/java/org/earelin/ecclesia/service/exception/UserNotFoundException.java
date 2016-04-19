package org.earelin.ecclesia.service.exception;

/**
 * Exception raised when user service cannon found an user
 */
public class UserNotFoundException extends RuntimeException
        implements EntityNotFoundException {
    
    private long id;
    
    public UserNotFoundException(long id) {
        this.id = id;
    }
    
    @Override
    public long getId() {
        return id;
    }
}
