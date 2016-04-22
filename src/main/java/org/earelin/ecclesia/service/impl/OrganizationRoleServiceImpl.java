package org.earelin.ecclesia.service.impl;

import org.earelin.ecclesia.service.OrganizationRoleService;
import org.earelin.ecclesia.service.dto.OrganizationRoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Organization role service implementation
 */
@Service
@Transactional
public class OrganizationRoleServiceImpl implements OrganizationRoleService {

    @Override
    public void add(OrganizationRoleDTO organizationRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(OrganizationRoleDTO organizationRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public OrganizationRoleDTO get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
