package org.earelin.ecclesia.repository;

import org.earelin.ecclesia.domain.OrganizationRole;

/**
 * Organization role repository
 */
public interface OrganizationRoleRepository {
    void add(OrganizationRole role);
    void update(OrganizationRole role);
    void remove(OrganizationRole role);
    OrganizationRole get(long id);
}
