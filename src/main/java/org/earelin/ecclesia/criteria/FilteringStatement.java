package org.earelin.ecclesia.criteria;

/**
 *
 */
public class FilteringStatement {
    
    private final String property;
    private final String value;
    private final FilteringOperation operation;
    
    public FilteringStatement(String property, String value) {
        this.property = property;
        this.value = value;
        this.operation = FilteringOperation.EQUALS;
    }

    public FilteringStatement(String property, String value, FilteringOperation operation) {
        this.property = property;
        this.value = value;
        this.operation = operation;
    }

    public String getProperty() {
        return property;
    }

    public String getValue() {
        return value;
    }

    public FilteringOperation getOperation() {
        return operation;
    }
    
}
