package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.service.dto.GroupDTO;

/**
 * 
 */
public interface GroupService {
    void add(GroupDTO group);
    void update(GroupDTO group);
    void remove(long id);
    GroupDTO get(long id);
    List<GroupDTO> list();
    List<GroupDTO> list(int limit, int offset);
}
