package com.mygdx.enums;

public enum EventTypeEnum {
	GREETING("greeting"), BATTLE("battle"), CHAT("chat"), SELECT_EVENT("select_event"), CREDIT("credit"), SELECT_COMPONENT(
			"select_component"), MOVE_FIELD("move_field"), MOVE_NODE("move_node"), MOVE_SUB_NODE("move_sub_node"), NEXT_SECTION(
			"next_section"), BATTLE_CONTROL("battle_control"), MUSIC("music"), BATTLE_END("battle_end"), PASS_TIME(
			"pass_time"), DONT_GO_BUILDING("dont_go_building"), GAME_OVER("game_over"), CLICK_ARROW("click_arrow");
	private String code;

	EventTypeEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static EventTypeEnum findEventTypeEnum(String code) {
		for (EventTypeEnum eventTypeEnum : EventTypeEnum.values())
			if (eventTypeEnum.toString().equals(code))
				return eventTypeEnum;
		return null;
	}
}
