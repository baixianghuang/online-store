package com.store.exceptions;

public class ShopOperationException extends RuntimeException {
    /**
     * Not many differences than RuntimeException. The goal here is to indicate
     * that this RuntimeException is related to shop related operations.
     * Note that we use RuntimeException to support transactional operations
     */
    public ShopOperationException(String msg) {
        super(msg);
    }
}
