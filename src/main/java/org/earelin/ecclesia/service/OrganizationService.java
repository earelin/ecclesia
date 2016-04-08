/*
 * 
 */
package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.domain.Organization;

/**
 *
 */
public interface OrganizationService {
    public void add(Organization organization);
    public void update(long id);
    public void remove(long id);
    public Organization get(long id);
    public List<Organization> list();
    public List<Organization> list(int limit, int offset);
}
