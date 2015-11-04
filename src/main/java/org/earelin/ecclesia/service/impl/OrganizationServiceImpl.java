package org.earelin.ecclesia.service.impl;

import org.earelin.ecclesia.dao.OrganizationDAO;
import org.earelin.ecclesia.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    
    private OrganizationDAO organizationDAO;
    
    @Autowired
    public OrganizationServiceImpl(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }
    
    
    
}
