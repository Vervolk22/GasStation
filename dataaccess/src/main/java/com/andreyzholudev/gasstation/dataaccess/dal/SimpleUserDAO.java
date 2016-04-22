package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.SimpleUserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by AndreyZholudev on 3/18/2016.
 */
@Repository
public class SimpleUserDAO extends DAOImpl<SimpleUserEntity> {
    protected static SessionFactory factory;

    @Override
    protected Class getEntityClass() {
        return SimpleUserEntity.class;
    }

    @Transactional
    public SimpleUserEntity readByUsername(String username) {
        //Session session = getCurrentSession();
        try(Session session = factory.openSession()) {
            return (SimpleUserEntity)session.createCriteria(SimpleUserEntity.class).
                    add(Restrictions.eq("username", username)).uniqueResult();
        }
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}