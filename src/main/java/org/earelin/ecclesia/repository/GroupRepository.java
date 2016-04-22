/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;

/**
 *
 * @author xcarriba
 */
public interface GroupRepository {
    public Group get(long id);
    public void add(Group group);
    public void update(Group group);
    public void remove(Group group);   
    public List<Group> list(Organization organization);
    public List<Group> list(Organization organization, int limit, int offset);
}
