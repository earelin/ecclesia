package org.earelin.ecclesia.service.exception;

/**
 * Exception raised when a service cannon found an entity
 */
public class EntityNotFoundException extends RuntimeException {

    private long id;
    
    public EntityNotFoundException(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }

}