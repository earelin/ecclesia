package org.earelin.ecclesia.repository;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import org.earelin.ecclesia.criteria.FilteringCriteria;
import org.earelin.ecclesia.criteria.OrderingCriteria;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generic dao implementation
 * @param <E>
 */
public abstract class GenericRepositoryDaoImpl<E> implements GenericRepository<E> {
    
    @Autowired
    private SessionFactory sessionFactory;
     
    private final Class<E> daoType;

    public GenericRepositoryDaoImpl() {        
        daoType = (Class<E>) ((ParameterizedType) getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    protected Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void add(E entity) {
        currentSession().save(entity);
    }

    @Override
    public void update(E entity) {
        currentSession().update(entity);
    }

    @Override
    public void remove(E entity) {
        currentSession().delete(entity);
    }

    @Override
    public E get(long id) {
        return (E) currentSession().get(daoType, id);
    }

    @Override
    public List<E> findAll() {
        return (List<E>) currentSession().createCriteria(daoType).list();
    }

    @Override
    public List<E> findAll(int limit, int offset) {
        return (List<E>) currentSession().createCriteria(daoType)
                .setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }

    @Override
    public List<E> findAll(FilteringCriteria filtering, OrderingCriteria ordering) {
        Criteria criteria = currentSession().createCriteria(daoType);
        
        if (filtering != null) {
            criteria.add(CriteriaToHibernateConversor.filteringConvert(filtering));
        }
        
        if (ordering != null) {
            criteria.addOrder(CriteriaToHibernateConversor.orderingConvert(ordering));
        }
        
        return (List<E>) criteria.list();
    }

    @Override
    public List<E> findAll(FilteringCriteria filtering, OrderingCriteria ordering, int limit, int offset) {
        Criteria criteria = currentSession().createCriteria(daoType);
        
        if (filtering != null) {
            criteria.add(CriteriaToHibernateConversor.filteringConvert(filtering));
        }
        
        if (ordering != null) {
            criteria.addOrder(CriteriaToHibernateConversor.orderingConvert(ordering));
        }
        
        return (List<E>) criteria.setMaxResults(limit)
                .setFirstResult(offset)
                .list();
    }
    
}
