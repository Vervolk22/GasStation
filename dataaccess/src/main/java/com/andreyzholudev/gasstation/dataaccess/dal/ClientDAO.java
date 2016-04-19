package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.ClientEntity;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by AndreyZholudev on 4/19/2016.
 */
public class ClientDAO extends DAOImpl<ClientEntity> {
    protected static SessionFactory factory;

    @Override
    protected Class getEntityClass() {
        return ClientEntity.class;
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
