package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.criteria.FilteringCriteria;
import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;
import org.springframework.stereotype.Repository;

/**
 * Group data access object
 */
@Repository
public class GroupRepositoryDao extends GenericRepositoryDaoImpl<Group>
        implements GroupRepository {

    @Override
    public List<Group> findByOrganization(Organization organization) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Group> findByOrganization(Organization organization, int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Group> findByOrganization(Organization organization,
            FilteringCriteria filtering, OrderingCriteria ordering) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Group> findByOrganization(Organization organization,
            FilteringCriteria filtering, OrderingCriteria ordering, int limit, int offset) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
