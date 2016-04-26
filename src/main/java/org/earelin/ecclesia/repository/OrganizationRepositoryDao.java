package org.earelin.ecclesia.repository;

import org.earelin.ecclesia.domain.Organization;
import org.springframework.stereotype.Repository;

/**
 * Organization data access object
 */
@Repository
public class OrganizationRepositoryDao extends GenericRepositoryDaoImpl<Organization>
        implements OrganizationRepository {
    
}
