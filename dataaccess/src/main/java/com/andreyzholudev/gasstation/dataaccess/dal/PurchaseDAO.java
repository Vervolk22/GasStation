package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.PurchaseEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrei on 28.03.2016.
 */
@Repository
public class PurchaseDAO extends DAOImpl<PurchaseEntity> {
    @Override
    protected Class getEntityClass() {
        return PurchaseEntity.class;
    }

}
