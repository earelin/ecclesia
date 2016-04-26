package org.earelin.ecclesia.repository;

import org.earelin.ecclesia.domain.GroupRole;
import org.springframework.stereotype.Repository;

/**
 * Group role data access object
 */
@Repository
public class GroupRoleRepositoryDao  extends GenericRepositoryDaoImpl<GroupRole>
        implements GroupRoleRepository {
}
