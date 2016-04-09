/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.entity.Organization;

/**
 *
 * @author xcarriba
 */
public interface OrganizationReponsitory {    
    public Organization get(long id);
    public void add(Organization organization);
    public void update(Organization organization);
    public void remove(Organization organization);   
    public List<Organization> list();
    public List<Organization> list(int limit, int offset);
}
