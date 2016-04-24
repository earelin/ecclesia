package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.criteria.FilteringCriteria;
import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.earelin.ecclesia.service.dto.OrganizationDto;

/**
 * Organizations service
 */
public interface OrganizationService {
    void add(OrganizationDto organization);
    void update(OrganizationDto organization);
    void remove(long id);
    OrganizationDto get(long id);
    boolean exists(long id);
    List<OrganizationDto> findAll();
    List<OrganizationDto> findAll(int limit, int offset);
    List<OrganizationDto> findAll(FilteringCriteria filtering, OrderingCriteria order, int limit, int offset);
}
