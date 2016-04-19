package org.earelin.ecclesia.service.exception;

/**
 * Exception raised when organization service cannon found a organization
 */
public class OrganizationNotFoundException extends RuntimeException
        implements EntityNotFoundException {

    private long id;
    
    public OrganizationNotFoundException(long id) {
        this.id = id;
    }
    
    @Override
    public long getId() {
        return id;
    }

}
