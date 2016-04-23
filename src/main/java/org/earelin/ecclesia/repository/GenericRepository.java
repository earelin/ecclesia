package org.earelin.ecclesia.repository;

import java.util.List;

/**
 * Generic dao
 */
public interface GenericRepository<E> {
    public void add(E entity) ;
    public void update(E entity) ;
    public void remove(E entity);
    public E get(long id);
    public List<E> findAll();
}
