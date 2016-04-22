package org.earelin.ecclesia.repository;

import org.earelin.ecclesia.domain.GroupRole;

/**
 * Group role repository
 */
public interface GroupRoleRepository {
    void add(GroupRole role);
    void update(GroupRole role);
    void remove(GroupRole role);
    GroupRole get(long id);
}
