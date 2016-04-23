package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.domain.Group;
import org.earelin.ecclesia.domain.Organization;

/**
 * Group repository
 */
public interface GroupRepository extends GenericRepository<Group> {
    List<Group> findByOrganization(Organization organization);
    List<Group> findByOrganization(Organization organization, int limit, int offset);
}
