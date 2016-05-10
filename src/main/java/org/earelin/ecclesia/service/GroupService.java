package org.earelin.ecclesia.service;

import java.util.List;
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
    List<GroupDto> findAll();
    List<GroupDto> findAll(int limit, int offset);
}
