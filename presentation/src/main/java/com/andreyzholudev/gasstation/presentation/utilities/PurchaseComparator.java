package com.andreyzholudev.gasstation.presentation.utilities;

import com.andreyzholudev.gasstation.dataaccess.entities.BaseEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.PurchaseEntity;

import java.util.Comparator;

/**
 * Created by Andrei on 29.03.2016.
 */
public class PurchaseComparator extends BaseComparator implements Comparator<BaseEntity> {
    public PurchaseComparator(int columnsNum, int[] sortingColumns, int[] directions) {
        super(columnsNum, sortingColumns, directions);
    }

    protected Comparable getFieldValue(int fieldNum, BaseEntity entity) {
        PurchaseEntity purchase = (PurchaseEntity)entity;
        switch (fieldNum) {
            case 0:
                return purchase.getId();
            case 1:
                return purchase.getAmount();
            case 2:
                return purchase.getPaid();
            case 3:
                return purchase.getFuel().toString();
            case 4:
                return purchase.getDay().toString();
            case 5:
                return purchase.getTime();
            case 6:
                return purchase.getCashier().toString();
            case 7:
                if (purchase.getClient() == null) return null;
                return purchase.getClient().toString();

        }
        return null;
    }
}
