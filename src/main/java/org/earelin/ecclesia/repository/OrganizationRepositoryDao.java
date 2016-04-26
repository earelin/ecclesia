package org.earelin.ecclesia.repository;

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
    
}
