package org.earelin.ecclesia.criteria;

/**
 * Class to model an ordering criteria
 */
public class OrderingCriteria {
    
    private final String property;
    private final OrderingDirection direction;

    public OrderingCriteria(String property) {
        this.property = property;
        this.direction = OrderingDirection.ASC;
    }
    
    public OrderingCriteria(String property, OrderingDirection direction) {
        this.property = property;
        this.direction = direction;
    }

    public String getProperty() {
        return property;
    }

    public OrderingDirection getDirection() {
        return direction;
    }
    
}
