package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.BaseEntity;
import org.hibernate.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andrei on 28.03.2016.
 */
@Repository
public class DAOImpl implements DAO {
    protected static SessionFactory factory;

    @Override
    @Transactional
    public BaseEntity read(int id) {
        try(Session session = factory.openSession()) {
            return (BaseEntity) session.get(getEntityClass(), id);
        }
    }

    @Override
    @Transactional
    public List<BaseEntity> read(int start, int count, int orderBy, int orderType) {
        try(Session session = factory.openSession()) {
            Criteria criteria = session.createCriteria(getEntityClass());
            criteria.setFirstResult(start);
            criteria.setMaxResults(count);
            switch(orderType) {
                case 0:
                    criteria.addOrder(Order.asc(getFieldNameToOrder(orderBy)));
                    break;
                case 1:
                    criteria.addOrder(Order.desc(getFieldNameToOrder(orderBy)));
                    break;
            }
            return criteria.list();
        }
    }

    @Override
    @Transactional
    public List<BaseEntity> read() {
        try(Session session = factory.openSession()) {
            //return session.createCriteria(getEntityClass()).list();
            Query query = session.createQuery("from PurchaseEntity");

            List<BaseEntity> list = query.list();
            return list;
        }
    }

    @Override
    @Transactional
    public int readCount() {
        try(Session session = factory.openSession()) {
            return session.createCriteria(getEntityClass()).list().size();
        }
    }

    @Override
    @Transactional
    public void create(BaseEntity entity) {
        try(Session session = factory.openSession()) {
            session.save(entity);
        }
    }

    @Override
    public void update(BaseEntity entity) {
        try(Session session = factory.openSession()) {
            session.update(entity);
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try(Session session = factory.openSession()) {
            BaseEntity entity = (BaseEntity) session.get(getEntityClass(), id);
            session.delete(entity);
        }
    }

    @Override
    @Transactional
    public void delete(BaseEntity entity) {
        try(Session session = factory.openSession()) {
            session.delete(entity);
        }
    }

    protected Class<BaseEntity> getEntityClass() {
        throw new RuntimeException("Method DAOImpl.getEntityClass was not " +
                "overriden in one of the child classes.");
    }

    protected String getFieldNameToOrder(int orderBy) {
        throw new RuntimeException("Method DAOImpl.getFieldNameToOrder " +
        "was not overriden in one of the child classes.");
    }

    protected String getTypeNameForOrder() {
        throw new RuntimeException("Method DAOImpl.getFieldNameToOrder " +
                "was not overriden in one of the child classes.");
    }

    protected SessionFactory getSessionFactory() {
        return factory;
    }
    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }

    protected Session getCurrentSession() {
        return factory.getCurrentSession();
    }
}
