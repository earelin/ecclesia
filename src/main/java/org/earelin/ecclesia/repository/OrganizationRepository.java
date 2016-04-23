package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.Organization;

/**
 * Organization repository
 */
public interface OrganizationRepository 
        extends GenericRepository<Organization> {  
    List<Organization> findByOrganization(int limit, int offset);
}
