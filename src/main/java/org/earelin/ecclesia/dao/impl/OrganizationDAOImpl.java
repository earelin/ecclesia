package org.earelin.ecclesia.dao.impl;

import org.earelin.ecclesia.dao.OrganizationDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class OrganizationDAOImpl implements OrganizationDAO {

    private SessionFactory sessionFactory;
    
    @Autowired
    public OrganizationDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
