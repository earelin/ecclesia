package org.earelin.ecclesia.repository.dao;

import org.earelin.ecclesia.domain.GroupRole;
import org.earelin.ecclesia.repository.GroupRoleRepository;
import org.springframework.stereotype.Repository;

/**
 * Group role data access object
 */
@Repository
public class GroupRoleRepositoryDao  extends GenericDaoImpl<GroupRole>
        implements GroupRoleRepository {
}
