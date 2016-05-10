package org.earelin.ecclesia.unit.repository;

import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.earelin.ecclesia.criteria.OrderingDirection;
import org.earelin.ecclesia.repository.CriteriaToHibernateConversor;
import org.hibernate.criterion.Order;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * CriteriaToHibernateConversor unit test
 */
public class CriteriaToHibernateConversorOrderingTest {
    
    @Test
    public void shouldCreateAAscendentOrdering() {
        OrderingCriteria ordering = new OrderingCriteria("value", OrderingDirection.ASC);
        
        Order order = CriteriaToHibernateConversor.orderingConvert(ordering);
        
        assertEquals("value asc", order.toString());
    }
    
    @Test
    public void shouldCreateADescendingOrdering() {
        OrderingCriteria ordering = new OrderingCriteria("value", OrderingDirection.DESC);
        
        Order order = CriteriaToHibernateConversor.orderingConvert(ordering);
        
        assertEquals("value desc", order.toString());
    }
    
}
