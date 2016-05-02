package com.andreyzholudev.gasstation.dataaccess.dal;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Andrei on 02.05.2016.
 */
public class UserAuthorityDAO {
    protected static SessionFactory factory;

    public void createByQuery(int id1, int id2) {
        try (Session session = factory.openSession()) {
            Query query = session.createSQLQuery("insert into user_authority (user_id, authority_id) values(:a, :b)");
            query.setParameter("a", id1);
            query.setParameter("b", id2);
            query.executeUpdate();
        }
    }

    public void deleteByQuery(int id) {
        try (Session session = factory.openSession()) {
            Query query = session.createSQLQuery("DELETE from user_authority WHERE id = :a");
            query.setParameter("a", id);
            query.executeUpdate();
        }
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
