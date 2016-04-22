package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.service.dto.OrganizationDTO;

/**
 * Organizations service
 */
public interface OrganizationService {
    void add(OrganizationDTO organization);
    void update(OrganizationDTO organization);
    void remove(long id);
    OrganizationDTO get(long id);
    List<OrganizationDTO> list();
    List<OrganizationDTO> list(int limit, int offset);
}
