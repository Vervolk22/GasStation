package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.DayEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Andrei on 23.04.2016.
 */
public class DayDAO extends DAOImpl<DayEntity> {
    protected static SessionFactory factory;

    @Transactional
    public DayEntity readByString(java.sql.Date date) {
        try (Session session = factory.openSession()) {
            return (DayEntity) session.createCriteria(DayEntity.class)
                    .addOrder(Order.desc("date")).setMaxResults(1)
                    .list().get(0);
        }
        /*List<DayEntity> list;
        try (Session session = factory.openSession()) {
            list = session.createCriteria(DayEntity.class).list();
        }
        int t = 5;
        return new DayEntity();*/
    }

    @Override
    protected Class getEntityClass() {
        return DayEntity.class;
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
