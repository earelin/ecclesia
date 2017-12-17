package org.earelin.ecclesia.criteria;

/**
 * Unsupported criteria operation
 */
public class UnsupportedCriteriaOperation extends RuntimeException {
    
    static final long serialVersionUID = 1L;
    
    public UnsupportedCriteriaOperation(String message) {
        super(message);
    }
    
}
