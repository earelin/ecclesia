/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repositories;

import java.util.List;
import org.earelin.ecclesia.entities.OrganizationEntity;

/**
 *
 * @author xcarriba
 */
public interface OrganizationReponsitory {    
    public OrganizationEntity get(long id);
    public void add(OrganizationEntity organization);
    public void update(OrganizationEntity organization);
    public void remove(OrganizationEntity organization);   
    public List<OrganizationEntity> list();
    public List<OrganizationEntity> list(int limit, int offset);
}
