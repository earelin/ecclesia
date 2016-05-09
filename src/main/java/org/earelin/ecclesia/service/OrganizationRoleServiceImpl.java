package org.earelin.ecclesia.service;

import org.dozer.Mapper;
import org.earelin.ecclesia.domain.OrganizationRole;
import org.earelin.ecclesia.repository.OrganizationRoleRepository;
import org.earelin.ecclesia.service.dto.OrganizationDto;
import org.earelin.ecclesia.service.dto.OrganizationRoleDto;
import org.earelin.ecclesia.service.exception.EntityNotFoundException;
import org.earelin.ecclesia.service.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Organization role service implementation
 */
@Service
@Transactional
public class OrganizationRoleServiceImpl implements OrganizationRoleService {
    
    private final OrganizationRoleRepository repository;
    private final OrganizationService organizationService;
    private final Mapper mapper;

    @Autowired
    public OrganizationRoleServiceImpl(OrganizationRoleRepository repository,
            OrganizationService organizationService, Mapper mapper) {
        this.repository = repository;
        this.organizationService = organizationService;
        this.mapper = mapper;
    }

    @Override
    public void add(OrganizationRoleDto roleDto) {
        check(roleDto);
        
        if (roleDto.getId() != 0) {
            throw new ValidationException("Error validating OrganizationRoleDto: field id is not 0");
        }
        
        OrganizationRole role = mapper.map(roleDto, OrganizationRole.class);
        repository.add(role);
        roleDto.setId(role.getId());
    }

    @Override
    public void update(OrganizationRoleDto roleDto) {
        OrganizationRole role = repository.get(roleDto.getId());        
        if (role == null) {
            throw new EntityNotFoundException("Trying to update an unexisting OrganizationRole with id " + roleDto.getId());
        }
        
        check(roleDto);
        
        mapper.map(roleDto, role);
        
        repository.update(role);
    }

    @Override
    public void remove(long id) {
        OrganizationRole role = repository.get(id);
        
        if (role == null) {
            throw new EntityNotFoundException("Trying to remove an unexisting OrganizationRole with id " + id);
        }
        
        repository.remove(role);
    }

    @Transactional(readOnly = true)
    @Override
    public OrganizationRoleDto get(long id) {
        OrganizationRole role = repository.get(id);
        
        if (role == null) {
            throw new EntityNotFoundException("Trying to load an unexisting OrganizationRole with id " + id);
        }
        
        return mapper.map(role, OrganizationRoleDto.class);
    }
    
    /**
     * Check business rules
     * @param roleDto 
     */
    private void check(OrganizationRoleDto roleDto) {
        OrganizationDto organization = roleDto.getOrganization();
        if (organization == null) {
            throw new ValidationException("Error validating OrganizationRoleDto: field organization is null");
        }
        
        if (!organizationService.exists(organization.getId())) {
            throw new EntityNotFoundException("Organization with id " + organization.getId() + " not found.");
        }
    }

}
