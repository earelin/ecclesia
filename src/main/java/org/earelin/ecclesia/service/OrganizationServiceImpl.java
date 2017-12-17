package org.earelin.ecclesia.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
import org.earelin.ecclesia.criteria.FilteringCriteria;
import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.earelin.ecclesia.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.springframework.transaction.annotation.Transactional;
import org.earelin.ecclesia.repository.OrganizationRepository;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;

/**
 * Organization service implementation
 */
@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationRepository repository;
    private final Mapper mapper;
    
    @Autowired
    public OrganizationServiceImpl(OrganizationRepository dao, Mapper mapper) {
        this.repository = dao;
        this.mapper = mapper;
    }

    @Override
    public void add(OrganizationDto organizationDto) {
        
        if (organizationDto.getId() != 0) {
            throw new ValidationException("Error validating OrganizationDto: field id is not 0");
        } 
        
        Date now = new Date();
        organizationDto.setCreated(now);
        organizationDto.setUpdated(now);
        
        Organization organization = mapper.map(organizationDto, Organization.class);
        repository.add(organization);
        
        organizationDto.setId(organization.getId());
    }

    @Override
    public void update(OrganizationDto organizationDto) {
        Date now = new Date();
        organizationDto.setUpdated(now);
        
        Organization organization = repository.get(organizationDto.getId());        
        if (organization == null) {
            throw new EntityNotFoundException("Trying to update an unexisting Group with id " + organizationDto.getId());
        }        
        mapper.map(organizationDto, organization);
        
        repository.update(organization);
    }

    @Override
    public void remove(long id) {
        Organization organization = repository.get(id);
        
        if (organization == null) {
            throw new EntityNotFoundException("Trying to remove an unexisting Organization with id " + id);
        }
        
        repository.remove(organization);
    }

    @Transactional(readOnly = true)
    @Override
    public OrganizationDto get(long id) {
        Organization organization = repository.get(id);
        
        if (organization == null) {
            throw new EntityNotFoundException("Trying to load an unexisting Organization with id " + id);
        }
        
        return mapper.map(organization, OrganizationDto.class);
    }

    @Transactional(readOnly = true)
    @Override
    public boolean exists(long id) {
        Organization organization = repository.get(id);
        return organization != null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<OrganizationDto> findAll(FilteringCriteria filtering, OrderingCriteria order, int limit, int offset) {
        List<Organization> organizations = repository.findAll(filtering, order, limit, offset);
	List<OrganizationDto> organizationsDto = new ArrayList<>();
	mapper.map(organizations, organizationsDto);
	return organizationsDto;
    }
    
}
