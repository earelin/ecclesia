package org.earelin.ecclesia.service;

import org.earelin.ecclesia.service.dto.GroupRoleDto;

/**
 * Group role service
 */
public interface GroupRoleService {
    void add(GroupRoleDto groupRole);
    void update(GroupRoleDto groupRole);
    void remove(long id);
    GroupRoleDto get(long id);
}
