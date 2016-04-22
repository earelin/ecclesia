package org.earelin.ecclesia.service;

import org.earelin.ecclesia.service.dto.OrganizationRoleDTO;

/**
 *
 * @author xcarriba
 */
public interface OrganizationRoleService {
    void add(OrganizationRoleDTO organizationRole);
    void update(OrganizationRoleDTO organizationRole);
    void remove(long id);
    OrganizationRoleDTO get(long id);
}
