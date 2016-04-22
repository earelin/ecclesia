/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.Organization;

/**
 *
 * @author xcarriba
 */
public interface OrganizationReponsitory {    
    Organization get(long id);
    void add(Organization organization);
    void update(Organization organization);
    void remove(Organization organization);   
    List<Organization> list();
    List<Organization> list(int limit, int offset);
}
