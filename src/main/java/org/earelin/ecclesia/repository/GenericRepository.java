package org.earelin.ecclesia.repository;

import java.util.List;

/**
 * Generic dao
 */
public interface GenericRepository<E> {
    void add(E entity) ;
    void update(E entity) ;
    void remove(E entity);
    boolean exists(long id);
    E get(long id);
    List<E> findAll();
}
