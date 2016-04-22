/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repository;

import org.earelin.ecclesia.domain.OrganizationRole;

/**
 *
 * @author xcarriba
 */
public interface OrganizationRoleRepository {
    void add(OrganizationRole role);
    void update(OrganizationRole role);
    void remove(OrganizationRole role);
}
