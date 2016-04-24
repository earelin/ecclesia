package org.earelin.ecclesia.criteria;

/**
 *
 */
public class FilteringStatement {
    
    private final String property;
    private final Object value;
    private final FilteringOperation operation;
    
    public FilteringStatement(String property, Object value) {
        this.property = property;
        this.value = value;
        this.operation = FilteringOperation.EQUAL;
    }

    public FilteringStatement(String property, Object value, FilteringOperation operation) {
        this.property = property;
        this.value = value;
        this.operation = operation;
    }

    public String getProperty() {
        return property;
    }

    public Object getValue() {
        return value;
    }

    public FilteringOperation getOperation() {
        return operation;
    }
    
}
