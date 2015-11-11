/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.dao;

import org.earelin.ecclesia.domain.OrganizationRole;

/**
 *
 * @author xcarriba
 */
public interface OrganizationRoleDAO {
    public void add(OrganizationRole role);
    public void update(OrganizationRole role);
    public void remove(OrganizationRole role);
}
