package org.earelin.ecclesia.service;

import java.util.List;
import org.earelin.ecclesia.entity.Group;

/**
 * 
 */
public interface GroupService {
    public void add(Group group);
    public void update(Group group);
    public void remove(long id);
    public Group get(long id);
    public List<Group> list();
    public List<Group> list(int limit, int offset);
}
