package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.earelin.ecclesia.entity.Organization;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.exception.OrganizationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repository.OrganizationReponsitory;
import org.earelin.ecclesia.service.dto.OrganizationDTO;
import org.modelmapper.ModelMapper;

/**
 * OrganizationDTO service implementation
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationReponsitory dao;
    private final ModelMapper modelMapper;
    
    @Autowired
    public OrganizationServiceImpl(OrganizationReponsitory dao,
            ModelMapper modelMapper) {
        this.dao = dao;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(OrganizationDTO organization) {
        Date now = new Date();
        organization.setCreated(now);
        organization.setUpdated(now);
        
        Organization entity = modelMapper.map(organization, Organization.class);        
        dao.add(entity);
        
        organization.setId(entity.getId());
    }

    @Override
    public void update(OrganizationDTO organization) {
        Date now = new Date();
        organization.setUpdated(now);
        Organization entity = dao.get(organization.getId());
        modelMapper.map(organization, entity);
        dao.update(entity);
    }

    @Override
    public void remove(long id) {
        Organization organization = dao.get(id);
        
        if (organization == null) {
            throw new OrganizationNotFoundException(id);
        }
        
        dao.remove(organization);
    }

    @Override
    public OrganizationDTO get(long id) {
        Organization entity = dao.get(id);
        
        if (entity == null) {
            throw new OrganizationNotFoundException(id);
        }
        
        return modelMapper.map(entity, OrganizationDTO.class);
    }

    @Override
    public List<OrganizationDTO> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<OrganizationDTO> list(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
