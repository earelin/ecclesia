package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.service.dto.GroupDTO;

/**
 * 
 */
public interface GroupService {
    public void add(GroupDTO group);
    public void update(GroupDTO group);
    public void remove(long id);
    public GroupDTO get(long id);
    public List<GroupDTO> list();
    public List<GroupDTO> list(int limit, int offset);
}
