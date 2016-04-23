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
    List<GroupDto> list();
    List<GroupDto> list(int limit, int offset);
}
