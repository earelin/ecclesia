package org.earelin.ecclesia.dao.impl;

import org.earelin.ecclesia.dao.GroupDAO;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Repository
@Transactional
public class GroupDAOImpl implements GroupDAO {
    
    private final SessionFactory sessionFactory;
    
    @Autowired
    public GroupDAOImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
}
