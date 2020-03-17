package com.store.enums;

public enum ShopStateEnum {
    CHECK(0, "Under censor"), OFFLINE(-1, "Illegal shop"),
    SUCCESS(1, "Success operation"), PASS(2, "Authentication pass"),
    INNER_ERROR(-1001, "Inner error"), NULL_SHOPID(-1002, "ShopId is null"),
    NULL_SHOP(-1003, "Shop is null");
    private int state;
    private String stateInfo;

    private ShopStateEnum(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public static ShopStateEnum stateOf(int state) {
        for (ShopStateEnum stateEnum : values()) {
            if (stateEnum.getState() == state) {
                return stateEnum;
            }
        }
        return null;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }
}
