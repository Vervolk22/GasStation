package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.BranchEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Andrei on 25.04.2016.
 */
public class BranchDAO extends DAOImpl<BranchEntity> {
    protected static SessionFactory factory;

    @Override
    protected Class getEntityClass() {
        return BranchEntity.class;
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
