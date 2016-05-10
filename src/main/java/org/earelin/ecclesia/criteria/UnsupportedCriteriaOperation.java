package org.earelin.ecclesia.criteria;

/**
 * Unsupported criteria operation
 */
public class UnsupportedCriteriaOperation extends RuntimeException {
    
    public UnsupportedCriteriaOperation(String message) {
        super(message);
    }
    
}
