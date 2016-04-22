package org.earelin.ecclesia.service;

import org.earelin.ecclesia.service.dto.GroupRoleDTO;

/**
 * Group role service
 */
public interface GroupRoleService {
    void add(GroupRoleDTO groupRole);
    void update(GroupRoleDTO groupRole);
    void remove(long id);
    GroupRoleDTO get(long id);
}
