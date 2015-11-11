/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.domain.Organization;

/**
 *
 * @author xcarriba
 */
public interface OrganizationService {
    public void add(Organization organization);
    public void update(Organization organization);
    public void remove(Organization organization);
    public Organization get(long id);
    public List<Organization> list();
    public List<Organization> list(int limit, int offset);
}
