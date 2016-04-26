package org.earelin.ecclesia.service;

import org.dozer.Mapper;
import org.earelin.ecclesia.repository.OrganizationRoleRepository;
import org.earelin.ecclesia.service.dto.OrganizationRoleDto;
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
    private final Mapper mapper;

    @Autowired
    public OrganizationRoleServiceImpl(OrganizationRoleRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void add(OrganizationRoleDto organizationRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(OrganizationRoleDto organizationRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public OrganizationRoleDto get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
