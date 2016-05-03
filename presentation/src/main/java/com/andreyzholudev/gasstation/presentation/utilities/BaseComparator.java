package com.andreyzholudev.gasstation.presentation.utilities;

import com.andreyzholudev.gasstation.dataaccess.entities.BaseEntity;

import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Created by Andrei on 29.03.2016.
 */
public abstract class BaseComparator {
    protected int columnsNum;
    protected int[] sortingColumns;
    protected int[] directions;

    public BaseComparator(int columnsNum, int[] sortingColumns, int[] directions) {
        if (sortingColumns.length != columnsNum || directions.length != columnsNum  ||
                columnsNum < 1) {
            throw new InvalidParameterException();
        }
        this.columnsNum = columnsNum;
        this.sortingColumns = sortingColumns;
        this.directions = directions;
    }

    @Override
    public boolean equals(Object obj) {
        return (obj.getClass() == this.getClass() &&
                columnsNum == ((BaseComparator)obj).columnsNum &&
                Arrays.equals(sortingColumns, ((BaseComparator)obj).sortingColumns) &&
                Arrays.equals(directions, ((BaseComparator)obj).directions));
    }

    public int compare(BaseEntity o1, BaseEntity o2) {
        return compare(0, o1, o2);
    }

    public int compare(int comparingColumn, BaseEntity o1, BaseEntity o2) {
        if (comparingColumn == columnsNum) {
            return 0;
        }
        Comparable value1 = getFieldValue(sortingColumns[comparingColumn], o1);
        Comparable value2 = getFieldValue(sortingColumns[comparingColumn], o2);
        if (value1 == null && value2 == null) { return 0; }
        if (value1 == null && value2 != null) { return -1; }
        if (value1 != null && value2 == null) { return 1; }
        int result = value1.compareTo(value2);
        if (result == 0) {
            return compare(comparingColumn + 1, o1, o2);
        } else {
            return result * directions[comparingColumn];
        }
    }

    protected abstract Comparable getFieldValue(int fieldNum, BaseEntity lot);
}
