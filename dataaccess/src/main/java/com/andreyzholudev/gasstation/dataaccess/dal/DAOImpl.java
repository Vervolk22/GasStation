package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.BaseEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Andrei on 28.03.2016.
 */
@Repository
public class DAOImpl<E extends BaseEntity> implements DAO<E> {
    protected static SessionFactory factory;

    @Override
    @Transactional
    public E read(int id) {
        try(Session session = factory.openSession()) {
            return (E) session.get(getEntityClass(), id);
        }
    }

    @Override
    @Transactional
    public List<E> read() {
        try(Session session = factory.openSession()) {
            return session.createCriteria(getEntityClass()).list();
            //Query query = session.createQuery("from PurchaseEntity");

            //List<E> list = query.list();
            //
            // return list;
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
    public void create(E entity) {
        try(Session session = factory.openSession()) {
            session.persist(entity);
            session.flush();
        }
    }

    @Override
    @Transactional
    public void update(E entity) {
        try(Session session = factory.openSession()) {
            session.update(entity);
            session.flush();
        }
    }

    @Override
    @Transactional
    public void delete(int id) {
        try(Session session = factory.openSession()) {
            E entity = (E) session.get(getEntityClass(), id);
            session.delete(entity);
            session.flush();
        }
    }

    @Override
    @Transactional
    public void delete(E entity) {
        try(Session session = factory.openSession()) {
            session.delete(entity);
            session.flush();
        }
    }

    protected Class<E> getEntityClass() {
        throw new RuntimeException("Method DAOImpl.getEntityClass was not " +
                "overriden in one of the child classes.");
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }

    protected Session getCurrentSession() {
        return factory.getCurrentSession();
    }
}
