package com.andreyzholudev.gasstation.dataaccess.dal;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

/**
 * Created by AndreyZholudev on 3/22/2016.
 */
@Repository
public abstract class AbstractDAO {
    protected static SessionFactory factory;

    public SessionFactory getSessionFactory() {
        return factory;
    }
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
