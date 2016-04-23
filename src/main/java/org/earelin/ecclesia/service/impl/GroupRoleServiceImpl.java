package org.earelin.ecclesia.service.impl;

import org.dozer.Mapper;
import org.earelin.ecclesia.repository.GroupRoleRepository;
import org.earelin.ecclesia.service.GroupRoleService;
import org.earelin.ecclesia.service.dto.GroupRoleDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Group role service implementation
 */
@Service
@Transactional
public class GroupRoleServiceImpl implements GroupRoleService {
    
    private final GroupRoleRepository repository;
    private final Mapper mapper;

    @Autowired
    public GroupRoleServiceImpl(GroupRoleRepository repository, Mapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void add(GroupRoleDto groupRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void update(GroupRoleDto groupRole) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remove(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional(readOnly = true)
    @Override
    public GroupRoleDto get(long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
