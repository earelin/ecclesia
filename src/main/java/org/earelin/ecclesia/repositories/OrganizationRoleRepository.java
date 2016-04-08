/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repositories;

import org.earelin.ecclesia.entities.OrganizationRoleEntity;

/**
 *
 * @author xcarriba
 */
public interface OrganizationRoleRepository {
    public void add(OrganizationRoleEntity role);
    public void update(OrganizationRoleEntity role);
    public void remove(OrganizationRoleEntity role);
}
