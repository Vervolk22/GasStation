package com.andreyzholudev.gasstation.presentation.utilities;

import com.andreyzholudev.gasstation.dataaccess.entities.BaseEntity;
import com.andreyzholudev.gasstation.dataaccess.entities.PurchaseEntity;

import java.security.InvalidParameterException;
import java.util.Arrays;
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
                return purchase.getTime();
            case 4:
                return purchase.getCashierId();
            case 5:
                return purchase.getClientId();
            case 6:
                return purchase.getDayId();
            case 7:
                return purchase.getFuelId();
        }
        return null;
    }
}
