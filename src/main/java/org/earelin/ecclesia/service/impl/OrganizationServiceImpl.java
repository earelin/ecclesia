package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.entity.OrganizationEntity;
import org.earelin.ecclesia.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repository.OrganizationReponsitory;
import org.modelmapper.ModelMapper;

/**
 * Organization service implementation
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationReponsitory dao;
    private final ModelMapper mapper;
    
    @Autowired
    public OrganizationServiceImpl(OrganizationReponsitory dao,
            ModelMapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public Organization add(Organization organization) {
        Date now = new Date();
        OrganizationEntity entity = new OrganizationEntity(organization.getName());
        entity.setCreated(now);
        entity.setUpdated(now);
        dao.add(entity);
        return mapper.map(entity, Organization.class);
    }

    @Override
    public void update(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Organization get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Organization> list(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
