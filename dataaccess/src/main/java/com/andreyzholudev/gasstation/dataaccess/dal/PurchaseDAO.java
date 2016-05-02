package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.PurchaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andrei on 28.03.2016.
 */
@Repository
public class PurchaseDAO extends DAOImpl<PurchaseEntity> {
    protected static SessionFactory factory;

    @Transactional
    public List<PurchaseEntity> readByCashier(int num) {
        try (Session session = factory.openSession()) {
            return session.createCriteria(PurchaseEntity.class)
                .add(Restrictions.eq("cashier.id", num)).list();
        }
        /*try (Session session = factory.openSession()) {
            Query query = session.createSQLQuery("SELECT * FROM cashier where");
            query.setParameter("a", id1);
            query.setParameter("b", id2);
            query.executeUpdate();
        }*/
    }

    @Override
    protected Class getEntityClass() {
        return PurchaseEntity.class;
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
