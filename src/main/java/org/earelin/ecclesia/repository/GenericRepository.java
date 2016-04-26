package org.earelin.ecclesia.repository;

import java.util.List;
import org.earelin.ecclesia.criteria.FilteringCriteria;
import org.earelin.ecclesia.criteria.OrderingCriteria;

/**
 * Generic dao
 */
public interface GenericRepository<E> {
    void add(E entity) ;
    void update(E entity) ;
    void remove(E entity);
    E get(long id);
    List<E> findAll();
    List<E> findAll(int limit, int offset);
    List<E> findAll(FilteringCriteria filtering, OrderingCriteria ordering);
    List<E> findAll(FilteringCriteria filtering, OrderingCriteria ordering, int limit, int offset);
}
