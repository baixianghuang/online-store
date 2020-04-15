package com.store.util;

public class PageCalculator {
    /**
     * Convert pageIndex into rowIndex (row is used in database while page is used in Service and frontend)
     * @param pageIndex
     * @param pageSize
     * @return
     */
    public static int calculateRowIndex(int pageIndex, int pageSize) {
        return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
    }
}
