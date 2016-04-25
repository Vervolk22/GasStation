package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.BranchEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.DayEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.EverydayPriceInfoEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Andrei on 23.04.2016.
 */
public class EveryDayPriceInfoDAO extends DAOImpl<EverydayPriceInfoEntity> {
    protected static SessionFactory factory;

    @Override
    protected Class getEntityClass() {
        return EverydayPriceInfoEntity.class;
    }

    public List<EverydayPriceInfoEntity> read(DayEntity dayEntity, BranchEntity branchEntity) {
        try(Session session = factory.openSession()) {
            return session.createCriteria(EverydayPriceInfoEntity.class)
                    .add(Restrictions.eq("dayEntity.id", dayEntity.getId()))
                    .add(Restrictions.eq("branchEntity.id", branchEntity.getId())).list();
        }
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory) {
        this.factory = factory;
    }
}
