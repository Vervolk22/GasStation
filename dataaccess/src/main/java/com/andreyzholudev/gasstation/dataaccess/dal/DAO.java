package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.BaseEntity;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Andrei on 28.03.2016.
 */
public interface DAO<E extends BaseEntity> {
    void setSessionFactory(SessionFactory factory);
    E read(int id);
    List<E> read();
    int readCount();
    void create(E entity);
    void update(E entity);
    void delete(E entity);
    void delete(int id);
}
