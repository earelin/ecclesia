package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.earelin.ecclesia.entity.Organization;
import org.earelin.ecclesia.service.OrganizationService;
import org.earelin.ecclesia.service.exception.OrganizationNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repository.OrganizationReponsitory;

/**
 * OrganizationDTO service implementation
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationReponsitory dao;
    
    @Autowired
    public OrganizationServiceImpl(OrganizationReponsitory dao) {
        this.dao = dao;
    }

    @Override
    public void add(Organization organization) {
        Date now = new Date();
        organization.setCreated(now);
        organization.setUpdated(now);
        dao.add(organization);
    }

    @Override
    public void update(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(Organization organization) {
        dao.remove(organization);
    }

    @Override
    public Organization get(long id) {
        Organization organization = dao.get(id);
        
        if (organization == null) {
            throw new OrganizationNotFoundException(id);
        }
        
        return organization;
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
