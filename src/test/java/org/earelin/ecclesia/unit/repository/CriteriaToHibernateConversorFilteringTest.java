package org.earelin.ecclesia.unit.repository;

import org.earelin.ecclesia.criteria.FilteringCriteria;
import org.earelin.ecclesia.criteria.FilteringNexus;
import org.earelin.ecclesia.criteria.FilteringOperation;
import org.earelin.ecclesia.criteria.FilteringStatement;
import org.earelin.ecclesia.repository.CriteriaToHibernateConversor;
import org.hibernate.criterion.Criterion;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * CriteriaToHibernateConversor unit test
 */
public class CriteriaToHibernateConversorFilteringTest {
    
    private FilteringCriteria criteria;
    
    @Before
    public void init() {
        criteria = new FilteringCriteria();
    }
    
    @Test
    public void shouldCreateAnAndFilteringCriteria() {
        criteria.setNexus(FilteringNexus.AND);
        criteria.addOperation(new FilteringStatement("property1", "value1"));
        criteria.addOperation(new FilteringStatement("property2", "value2"));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property1=value1 and property2=value2)", criterion.toString());
    }
    
    @Test
    public void shouldCreateAnOrFilteringCriteria() {
        criteria.setNexus(FilteringNexus.OR);
        criteria.addOperation(new FilteringStatement("property1", "value1"));
        criteria.addOperation(new FilteringStatement("property2", "value2"));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property1=value1 or property2=value2)", criterion.toString());
    }
    
    @Test
    public void shouldCreateAnEqualOperation() {
        criteria.addOperation(
                new FilteringStatement("property", "value", FilteringOperation.EQUAL));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property=value)", criterion.toString());
    }
    
    @Test
    public void shouldCreateANotEqualOperation() {
        criteria.addOperation(
                new FilteringStatement("property", "value", FilteringOperation.NOT_EQUAL));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property<>value)", criterion.toString());
    }
    
    @Test
    public void shouldCreateAGreaterThanOperation() {
        criteria.addOperation(
                new FilteringStatement("property", "value", FilteringOperation.GREATER_THAN));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property>value)", criterion.toString());
    }
    
    @Test
    public void shouldCreateALessThanOperation() {
        criteria.addOperation(
                new FilteringStatement("property", "value", FilteringOperation.LESS_THAN));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property<value)", criterion.toString());
    }
    
    @Test
    public void shouldCreateAGreaterThanOrEqualOperation() {
        criteria.addOperation(
                new FilteringStatement("property", "value", FilteringOperation.GREATER_THAN_OR_EQUAL));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property>=value)", criterion.toString());
    }
    
    @Test
    public void shouldCreateALessThanOrEqualOperation() {
        criteria.addOperation(
                new FilteringStatement("property", "value", FilteringOperation.LESS_THAN_OR_EQUAL));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property<=value)", criterion.toString());
    }
    
    @Test
    public void shouldCreateALikeOperation() {
        criteria.addOperation(
                new FilteringStatement("property", "%value", FilteringOperation.LIKE));
        
        Criterion criterion = CriteriaToHibernateConversor.filteringConvert(criteria);
        
        assertEquals("(property like %value)", criterion.toString());
    }
    
}
