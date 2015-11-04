package org.earelin.ecclesia.service.impl;

import org.earelin.ecclesia.dao.GroupDAO;
import org.earelin.ecclesia.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class GroupServiceImpl implements GroupService {
    
    private GroupDAO groupDAO;
    
    @Autowired
    public GroupServiceImpl(GroupDAO groupDAO) {
        this.groupDAO = groupDAO;
    }
    
}
