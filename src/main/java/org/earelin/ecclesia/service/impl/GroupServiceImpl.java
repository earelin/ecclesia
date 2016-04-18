package org.earelin.ecclesia.service.impl;

import java.util.Date;
import java.util.List;
import org.dozer.Mapper;
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
    private final Mapper mapper;
    
    @Autowired
    public GroupServiceImpl(GroupRepository dao, Mapper mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    @Override
    public void add(GroupDTO group) {
        Date now = new Date();
        group.setCreated(now);
        group.setUpdated(now);
        
        Group groupEntity = mapper.map(group, Group.class);
        dao.add(groupEntity);
        
        group.setId(groupEntity.getId());
    }

    @Override
    public void update(GroupDTO groupDTO) {
        Date now = new Date();
        groupDTO.setUpdated(now);
        
        Group group = dao.get(groupDTO.getId());        
        if (group == null) {
            throw new GroupNotFoundException(groupDTO.getId());
        }        
        mapper.map(groupDTO, group);
        
        dao.update(group);
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
        
        return mapper.map(group, GroupDTO.class);
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
