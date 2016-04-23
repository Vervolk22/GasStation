package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.FuelEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AndreyZholudev on 4/19/2016.
 */
public class FuelDAO extends DAOImpl<FuelEntity> {
    protected static SessionFactory factory;

    @Override
    protected Class getEntityClass() {
        return FuelEntity.class;
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
