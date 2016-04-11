package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.entity.Organization;

/**
 * Organizations service
 */
public interface OrganizationService {
    public void add(Organization organization);
    public void update(Organization organization);
    public void remove(long id);
    public Organization get(long id);
    public List<Organization> list();
    public List<Organization> list(int limit, int offset);
}
