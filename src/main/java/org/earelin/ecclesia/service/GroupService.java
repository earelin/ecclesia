package org.earelin.ecclesia.service;

import org.earelin.ecclesia.service.dto.GroupDto;

/**
 * 
 */
public interface GroupService {
    void add(GroupDto group);
    void update(GroupDto group);
    void remove(long id);
    GroupDto get(long id);
    boolean exists(long id);
}
