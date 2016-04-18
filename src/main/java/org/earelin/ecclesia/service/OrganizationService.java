package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.service.dto.OrganizationDTO;

/**
 * Organizations service
 */
public interface OrganizationService {
    public void add(OrganizationDTO organization);
    public void update(OrganizationDTO organization);
    public void remove(long id);
    public OrganizationDTO get(long id);
    public List<OrganizationDTO> list();
    public List<OrganizationDTO> list(int limit, int offset);
}
