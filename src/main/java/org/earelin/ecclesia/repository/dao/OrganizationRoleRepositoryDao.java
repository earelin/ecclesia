package org.earelin.ecclesia.repository.dao;

import org.earelin.ecclesia.domain.OrganizationRole;
import org.springframework.stereotype.Repository;
import org.earelin.ecclesia.repository.OrganizationRoleRepository;

/**
 * Organization role data access object
 */
@Repository
public class OrganizationRoleRepositoryDao  extends GenericDaoImpl<OrganizationRole>
        implements OrganizationRoleRepository {
}
