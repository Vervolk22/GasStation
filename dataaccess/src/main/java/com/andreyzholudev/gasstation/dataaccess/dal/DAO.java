package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.BaseEntity;
import org.hibernate.SessionFactory;

import java.util.List;

/**
 * Created by Andrei on 28.03.2016.
 */
public interface DAO {
    void setSessionFactory(SessionFactory factory);
    BaseEntity read(int id);
    List<BaseEntity> read(int start, int count, int orderBy, int orderType);
    List<BaseEntity> read();
    int readCount();
    void create(BaseEntity entity);
    void update(BaseEntity entity);
    void delete(BaseEntity entity);
    void delete(int id);
}
