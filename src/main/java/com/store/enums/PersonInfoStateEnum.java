package com.store.enums;

public enum PersonInfoStateEnum {

	SUCCESS(0, "创建成功"), INNER_ERROR(-1001, "操作失败"),
	NULL_PERSON_INFO(-1002, "用户信息为空"), ALLOW(1, "允许"), NOT_ALLOW(-1, "不允许");

	private int state;

	private String stateInfo;

	private PersonInfoStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static PersonInfoStateEnum stateOf(int index) {
		for (PersonInfoStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}

}