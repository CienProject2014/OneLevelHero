package com.mygdx.enums;

public enum EventTypeEnum {
	GREETING("greeting"), BATTLE("battle"), CHAT("chat"), SELECT_EVENT("select_event"), CREDIT("credit"), SELECT_COMPONENT(
			"select_component"), MOVE_FIELD("move_field"), MOVE_NODE("move_node"), NEXT_SECTION("next_section"), BATTLE_CONTROL(
			"battle_control"), MUSIC("music"), BATTLE_END("battle_end"), PASS_TIME("pass_time"), COLLECT_EVENT(
			"collect_event"), MOVE_SUB_NODE("move_sub_node"), MOVE_SUB_NODE_BY_TIME("move_sub_node_by_time"), DONT_GO_BUILDING(
			"dont_go_building"), GAME_OVER("game_over"), LEAVE_PARTY("leave_party"), SET_TIME("set_time"), JOIN_PARTY(
			"join_party"), MOVE_SUB_NODE_AFTER_DAY_FROM("move_sub_node_after_day_from"), MOVE_SUB_NODE_BY_TIME_NEXTDAY(
			"move_sub_node_by_time_nextday"), MOVE_SUB_NODE_BY_TIME_FROM("move_sub_node_by_time_from"), CLICK_ARROW(
			"click_arrow"), MOVE_NODE_BY_TIME("move_node_by_time"), GET_EXP("get_exp"), GET_ITEM("get_item"), SELECT_CHAT(
			"select_chat");
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
