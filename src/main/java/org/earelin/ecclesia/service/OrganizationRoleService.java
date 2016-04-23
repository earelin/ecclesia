package org.earelin.ecclesia.service;

import org.earelin.ecclesia.service.dto.OrganizationRoleDto;

/**
 *
 * @author xcarriba
 */
public interface OrganizationRoleService {
    void add(OrganizationRoleDto organizationRole);
    void update(OrganizationRoleDto organizationRole);
    void remove(long id);
    OrganizationRoleDto get(long id);
}
