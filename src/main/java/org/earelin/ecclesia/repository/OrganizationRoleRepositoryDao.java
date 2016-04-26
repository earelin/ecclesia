package org.earelin.ecclesia.repository;

import org.earelin.ecclesia.domain.OrganizationRole;
import org.springframework.stereotype.Repository;

/**
 * Organization role data access object
 */
@Repository
public class OrganizationRoleRepositoryDao  extends GenericRepositoryDaoImpl<OrganizationRole>
        implements OrganizationRoleRepository {
}
