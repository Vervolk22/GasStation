package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.SimpleUserEntity;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Created by AndreyZholudev on 3/18/2016.
 */
@Repository
public class SimpleUserDAO extends DAOImpl<SimpleUserEntity> {
    @Override
    protected Class getEntityClass() {
        return SimpleUserEntity.class;
    }

    public SimpleUserEntity readByUsername(String username) {
        Session session = getCurrentSession();
        return (SimpleUserEntity)getCurrentSession().createCriteria(SimpleUserEntity.class).
                add(Restrictions.eq("username", username)).uniqueResult();
    }
}