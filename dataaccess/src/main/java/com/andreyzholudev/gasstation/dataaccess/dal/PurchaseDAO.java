package com.andreyzholudev.gasstation.dataaccess.dal;

import com.andreyzholudev.gasstation.dataaccess.entities.PurchaseEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by Andrei on 28.03.2016.
 */
@Repository
public class PurchaseDAO extends DAOImpl {
    @Override
    protected Class getEntityClass() {
        return PurchaseEntity.class;
    }

    @Override
    protected String getFieldNameToOrder(int orderBy) {
        switch(orderBy) {
            case 0:
                return "id";
            case 1:
                return "amount";
            case 2:
                return "paid";
            case 3:
                return "fuel";
            case 4:
                return "date";
            case 5:
                return "time";
            case 6:
                return "cashier";
            case 7:
                return "client";
        }
        return null;
    }
}
