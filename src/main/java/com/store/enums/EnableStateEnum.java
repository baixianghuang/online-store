package com.store.enums;

public enum EnableStateEnum {
	UNAVAILABLE(0, "不可用"), AVAILABLE(1, "可用"), CHECK(2, "审核中");
	private int state;
	private String stateInfo;

	private EnableStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	// 根据传入的state值返回相应的状态值
	public static EnableStateEnum stateOf(int index) {
		for (EnableStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
