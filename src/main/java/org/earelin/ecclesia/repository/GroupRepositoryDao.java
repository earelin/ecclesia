package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;
import org.springframework.stereotype.Repository;
import org.earelin.ecclesia.repository.GroupRepository;

/**
 * Group data access object
 */
@Repository
public class GroupRepositoryDao extends GenericDaoImpl<Group>
        implements GroupRepository {

    @Override
    public List<Group> findByOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Group> findByOrganization(Organization organization, int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
