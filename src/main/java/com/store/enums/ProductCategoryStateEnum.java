package com.store.enums;

public enum ProductCategoryStateEnum {
	SUCCESS(1, "Operation succeed"), INNER_ERROR(-1001, "Operation failed"), EMPTY_LIST(-1002, "The number of add is less than 1");

	private int state;

	private String stateInfo;

	private ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ProductCategoryStateEnum stateOf(int index) {
		for (ProductCategoryStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
