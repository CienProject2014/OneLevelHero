package com.mygdx.enums;

public enum EventTypeEnum {
	START_BATTLE("start_battle"), CLOSE_GAME_OBJECT_EVENT("close_game_object_event"), CHAT("chat"), CHOICE_OPTION(
			"choice_option"), SELECT_EVENT("select_event"), CREDIT("credit"), MOVE_FIELD("move_field"), MOVE_NODE(
			"move_node"), MOVE_DUNGEON_ROOM("move_dungeon_room"), NEXT_SECTION("next_section"), BATTLE_COMMAND(
			"battle_command"), PLAY_MUSIC("play_music"), SAVE("save"), END_BATTLE("end_battle"), PASS_TIME("pass_time"), COLLECT_EVENT(
			"collect_event"), MOVE_NODE_BEFORE_ABSOLUTE_TIME("move_node_before_section_time"), MOVE_NODE_AFTER_SECTION_TIME(
			"move_node_after_section_time"), MOVE_SUB_NODE("move_sub_node"), MOVE_SUB_NODE_BEFORE_ABSOLUTE_TIME(
			"move_sub_node_before_absolute_time"), MOVE_SUB_NODE_AFTER_ABSOLUTE_TIME(
			"move_sub_node_after_absolute_time"), MOVE_SUB_NODE_BY_TIME("move_sub_node_by_time"), REST_IN_NODE(
			"rest_in_node"), GAME_OVER("game_over"), LEAVE_PARTY("leave_party"), SET_TIME("set_time"), GET_BUFF(
			"get_buff"), JOIN_PARTY("join_party"), MOVE_SUB_NODE_AFTER_DAY_FROM("move_sub_node_after_day_from"), MOVE_SUB_NODE_BY_TIME_NEXTDAY(
			"move_sub_node_by_time_nextday"), MOVE_SUB_NODE_BY_TIME_FROM("move_sub_node_by_time_from"), CLICK_ARROW(
			"click_arrow"), MOVE_NODE_BY_TIME("move_node_by_time"), GET_EXP("get_exp"), GET_ITEM("get_item"), OPEN_NPC_EVENT(
			"open_npc_event"), SET_NPC_TARGET_TIME("set_npc_target_time"), CLOSE_NPC_EVENT("close_npc_event"), QUEST_GET_ITEM(
			"quest_get_item"), SELECT_CHAT("select_chat"), QUIT_PARTY("quit_party"), STOP_GO_SUB_NODE(
			"stop_go_sub_node"), GO_SUB_NODE("go_sub_node"), MOVE_DUNGEON_ROOM_IN_TARGET_TIME(
			"move_dungeon_room_in_target_time"), MOVE_SUB_NODE_IN_TARGET_TIME("move_sub_node_in_target_time"), MOVE_DUNGEON_ROOM_AFTER_ABSOLUTE_TIME(
			"move_dungeon_room_after_absolute_time"), MOVE_NODE_IN_TARGET_TIME("move_node_in_target_time"), MOVE_DUNGEON_ROOM_BEFORE_ABSOLUTE_TIME(
			"move_dungeon_room_before_absolute_time"), ALWAYS_OPEN_NPC_EVENT("always_open_npc_event"), MOVE_SUB_NODE_BEFORE_SECTION_TIME(
			"move_sub_node_before_section_time");
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
