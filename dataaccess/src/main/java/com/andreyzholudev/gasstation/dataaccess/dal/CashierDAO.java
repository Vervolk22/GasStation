package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.CashierEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Andrei on 23.04.2016.
 */
public class CashierDAO extends DAOImpl<CashierEntity> {
    protected static SessionFactory factory;

    @Override
    protected Class getEntityClass() {
        return CashierEntity.class;
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
