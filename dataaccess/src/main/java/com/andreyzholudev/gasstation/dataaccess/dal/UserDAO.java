package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.User;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by AndreyZholudev on 3/18/2016.
 */
public class UserDAO extends AbstractDAO {
    public User getUser(String username) {
        Session session = factory.getCurrentSession();
        String queryString = "from User as model where model.username = ?";
        Query queryObject = session.createQuery(queryString);
        queryObject.setParameter(0, username);
        List<User> list = queryObject.list();
        Hibernate.initialize(list.get(0).getAuthorities());
        return list.get(0);
    }
}
