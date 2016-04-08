package org.earelin.ecclesia.service.impl;

import org.earelin.ecclesia.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.earelin.ecclesia.repository.GroupRepository;

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
    
}
