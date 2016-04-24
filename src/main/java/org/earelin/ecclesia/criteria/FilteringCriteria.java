package org.earelin.ecclesia.criteria;

import java.util.ArrayList;
import java.util.List;

/**
 * Filtering criteria
 */
public class FilteringCriteria {
    
    private FilteringNexus nexus = FilteringNexus.AND;
    private List<FilteringStatement> statements = new ArrayList();

    public FilteringCriteria() {}
    
    public FilteringCriteria(String property, Object value) {
        FilteringStatement statement = new FilteringStatement(property, value);
        statements.add(statement);
    }
    
    public void addOperation(FilteringStatement operation) {
        statements.add(operation);
    }

    public List<FilteringStatement> getStatements() {
        return statements;
    }

    public FilteringNexus getNexus() {
        return nexus;
    }

    public void setNexus(FilteringNexus nexus) {
        this.nexus = nexus;
    }
    
}
