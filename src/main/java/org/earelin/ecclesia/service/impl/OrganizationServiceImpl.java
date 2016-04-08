package org.earelin.ecclesia.service.impl;

import java.util.List;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.entities.OrganizationEntity;
import org.earelin.ecclesia.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repositories.OrganizationReponsitory;

/**
 *
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(long id) {
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
