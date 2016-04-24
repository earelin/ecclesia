package org.earelin.ecclesia.repository.dao;

import org.earelin.ecclesia.criteria.FilteringCriteria;
import org.earelin.ecclesia.criteria.FilteringNexus;
import org.earelin.ecclesia.criteria.FilteringStatement;
import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.earelin.ecclesia.criteria.OrderingDirection;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Junction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * Converts service layer criteria objects to hibernate statements objects.
 */
public class CriteriaToHibernateConversor {
    
    /**
     * Converts a FilteringCriteria object to an hibernate Criterion object
     * @param filtering
     * @return 
     */
    public static Criterion filteringConvert(FilteringCriteria filtering) {
        Junction junction;
        
        if (filtering.getNexus() == FilteringNexus.AND) {
            junction = Restrictions.conjunction();
        } else {
            junction = Restrictions.disjunction();
        }
        
        for (FilteringStatement statement : filtering.getStatements()) {
            final String property = statement.getProperty();
            final Object value = statement.getValue();
            
            switch (statement.getOperation()) {
                case EQUAL:
                    junction.add(Restrictions.eq(property, value));
                    break;
                case NOT_EQUAL:
                    junction.add(Restrictions.ne(property, value));
                    break;
                case GREATER_THAN:
                    junction.add(Restrictions.gt(property, value));
                    break;
                case LESS_THAN:
                    junction.add(Restrictions.lt(property, value));
                    break;
                case GREATER_THAN_OR_EQUAL:
                    junction.add(Restrictions.ge(property, value));
                    break;
                case LESS_THAN_OR_EQUAL:
                    junction.add(Restrictions.le(property, value));
                    break;
                case LIKE:
                    junction.add(Restrictions.like(property, value));
                    break;
            }
        }
        
        return junction;
    }
    
    /**
     * Converts a OrderingCriteria object to an hibernate criterion object
     * @param ordering
     * @return 
     */
    public static Order orderingConvert(OrderingCriteria ordering) {
        if (ordering.getDirection() == OrderingDirection.ASC) {
            return Order.asc(ordering.getProperty());
        } else {
            return Order.desc(ordering.getProperty());
        }
    }
}
