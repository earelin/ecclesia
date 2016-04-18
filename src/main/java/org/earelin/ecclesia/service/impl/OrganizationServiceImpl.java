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

/**
 * Organization service implementation
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationReponsitory dao;
    
    @Autowired
    public OrganizationServiceImpl(OrganizationReponsitory dao) {
        this.dao = dao;
    }

    @Override
    public void add(OrganizationDTO organizationDTO) {
        Date now = new Date();
        organizationDTO.setCreated(now);
        organizationDTO.setUpdated(now);
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(OrganizationDTO organizationDTO) {
        Date now = new Date();
        organizationDTO.setUpdated(now);
        
        throw new UnsupportedOperationException("Not supported yet.");
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
        Organization organization = dao.get(id);
        
        if (organization == null) {
            throw new OrganizationNotFoundException(id);
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
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
