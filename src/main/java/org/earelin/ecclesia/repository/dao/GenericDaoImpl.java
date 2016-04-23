package org.earelin.ecclesia.repository.dao;


import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import org.earelin.ecclesia.repository.GenericRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generic dao implementation
 */
public abstract class GenericDaoImpl<E> implements GenericRepository<E> {
    
    @Autowired
    private SessionFactory sessionFactory;
     
    private final Class<E> daoType;

    public GenericDaoImpl() {        
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
    public boolean exists(long id) {
        E entity = (E) currentSession().get(daoType, id);
        return entity != null;
    }

    @Override
    public List<E> findAll() {
        return currentSession().createCriteria(daoType).list();
    }
    
}
