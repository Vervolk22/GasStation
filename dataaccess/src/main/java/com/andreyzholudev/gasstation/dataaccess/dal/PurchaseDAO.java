package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.PurchaseEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrei on 28.03.2016.
 */
@Repository
public class PurchaseDAO extends DAOImpl<PurchaseEntity> {
    protected static SessionFactory factory;

    @Override
    protected Class getEntityClass() {
        return PurchaseEntity.class;
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
