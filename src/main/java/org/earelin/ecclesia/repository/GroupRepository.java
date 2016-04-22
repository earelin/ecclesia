package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;

/**
 * Group repository
 */
public interface GroupRepository {
    Group get(long id);
    void add(Group group);
    void update(Group group);
    void remove(Group group);   
    List<Group> list(Organization organization);
    List<Group> list(Organization organization, int limit, int offset);
}
