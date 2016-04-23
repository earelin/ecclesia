package org.earelin.ecclesia.repository.dao;

import java.util.List;
import org.earelin.ecclesia.domain.Organization;
import org.springframework.stereotype.Repository;
import org.earelin.ecclesia.repository.OrganizationRepository;

/**
 * Organization data access object
 */
@Repository
public class OrganizationRepositoryDao extends GenericDaoImpl<Organization>
        implements OrganizationRepository {

    @Override
    public List<Organization> findByOrganization(int limit, int offset) {
        return (List<Organization>) currentSession()
                .createQuery("from Organization as org order by org.name")
                .setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }
    
}
