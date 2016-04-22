package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.Organization;

/**
 * Organization repository
 */
public interface OrganizationReponsitory {    
    Organization get(long id);
    void add(Organization organization);
    void update(Organization organization);
    void remove(Organization organization);   
    List<Organization> list();
    List<Organization> list(int limit, int offset);
}
