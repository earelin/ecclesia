package org.earelin.ecclesia.service.impl;

import org.earelin.ecclesia.service.GroupRoleService;
import org.earelin.ecclesia.service.dto.GroupRoleDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Group role service implementation
 */
@Service
@Transactional
public class GroupRoleServiceImpl implements GroupRoleService {

    @Override
    public void add(GroupRoleDTO groupRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(GroupRoleDTO groupRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public GroupRoleDTO get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
