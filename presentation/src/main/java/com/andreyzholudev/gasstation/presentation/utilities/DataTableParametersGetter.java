package com.andreyzholudev.gasstation.presentation.utilities;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by AndreyZholudev on 3/24/2016.
 */
public class DataTableParametersGetter {
    private HttpServletRequest request;

    public DataTableParametersGetter(HttpServletRequest request) {
        this.request = request;
    }

    public int getSortingColumnsNumber() {
        return Integer.parseInt(request.getParameter("iSortingCols"));
    }

    public int[] getSortingColumns() {
        int columnsNumber = Integer.parseInt(request.getParameter("iSortingCols"));
        int[] sortingColumns = new int[columnsNumber];
        for (int i = 0; i < columnsNumber; i++) {
            sortingColumns[i] = Integer.parseInt(request.getParameter("iSortCol_" + i));
        }
        return sortingColumns;
    }

    public int[] getDirections() {
        int columnsNumber = Integer.parseInt(request.getParameter("iSortingCols"));
        int[] directions = new int[columnsNumber];
        for (int i = 0; i < columnsNumber; i++) {
            directions[i] = request.getParameter("sSortDir_" + i).equals("asc") ? 1 : -1;
        }
        return directions;
    }

    public int getStartNum() {
        return Integer.parseInt(request.getParameter("iDisplayStart"));
    }

    public int getNumRecordsToDisplay() {
        return Integer.parseInt(request.getParameter("iDisplayLength"));
    }

    public String getEchoParameter() {
        return request.getParameter("sEcho");
    }
}
