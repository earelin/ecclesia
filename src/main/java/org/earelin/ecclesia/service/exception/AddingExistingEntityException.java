package org.earelin.ecclesia.service.exception;

/**
 *
 * @author xcarriba
 */
public class AddingExistingEntityException extends RuntimeException {
    
    static final long serialVersionUID = 1L;
    
    public AddingExistingEntityException(String message) {
        super(message);
    }
}
