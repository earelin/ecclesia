package org.earelin.ecclesia.service.impl;

import java.util.List;
import org.earelin.ecclesia.dao.OrganizationDAO;
import org.earelin.ecclesia.domain.Organization;
import org.earelin.ecclesia.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    private final OrganizationDAO dao;
    
    @Autowired
    public OrganizationServiceImpl(OrganizationDAO dao) {
        this.dao = dao;
    }
    
        @Override
    public List<Organization> list() {
        return dao.list();
    }

    @Override
    public List<Organization> list(int limit, int offset) {
        return dao.list(limit, offset);
    }

    @Override
    public void add(Organization organization) {
        dao.add(organization);
    }

    @Override
    public void update(Organization organization) {
        dao.update(organization);
    }

    @Override
    public void remove(Organization organization) {
        dao.remove(organization);
    }

    @Override
    public Organization get(long id) {
        return dao.get(id);
    }
    
}
