package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.earelin.ecclesia.entity.Group;
import org.earelin.ecclesia.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repository.GroupRepository;
import org.earelin.ecclesia.service.dto.GroupDTO;
import org.earelin.ecclesia.service.exception.GroupNotFoundException;

/**
 *
 */
@Service
public class GroupServiceImpl implements GroupService {
    
    private final GroupRepository dao;
    
    @Autowired
    public GroupServiceImpl(GroupRepository dao) {
        this.dao = dao;
    }

    @Override
    public void add(GroupDTO group) {
        Date now = new Date();
        group.setCreated(now);
        group.setUpdated(now);
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(GroupDTO group) {
        Date now = new Date();
        group.setUpdated(now);
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(long id) {
        Group group = dao.get(id);
        
        if (group == null) {
            throw new GroupNotFoundException(id);
        }
        
        dao.remove(group);
    }

    @Override
    public GroupDTO get(long id) {
        Group group = dao.get(id);
        
        if (group == null) {
            throw new GroupNotFoundException(id);
        }
        
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<GroupDTO> list() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<GroupDTO> list(int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
