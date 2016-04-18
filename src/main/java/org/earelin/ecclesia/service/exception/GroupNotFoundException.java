package org.earelin.ecclesia.service.exception;

/**
 *
 */
public class GroupNotFoundException extends RuntimeException
        implements EntityNotFoundException {
    
    private long id;
    
    public GroupNotFoundException(long id) {
        this.id = id;
    }
    
    @Override
    public long getId() {
        return id;
    }

}
