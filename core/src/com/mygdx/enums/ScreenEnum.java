package com.mygdx.enums;

public enum ScreenEnum {
	BUILDING("building"), DUNGEON("dungeon"), DUNGEON_ENTRANCE("dungeon_entrance"), GAME_OBJECT(
			"game_object"), GREETING("greeting"), LOG("log"), LOADING_BAR("loading_bar"), MENU("menu"), VILLAGE(
					"village"), WORLD_MAP("world_map"), CREDIT("credit"), EXTRA("extra"), SAVE("save"), BONUS_POINT(
							"bonus_point"), LOAD("load"), EVENT("event"), FIELD("field"), ENDING("ending"), CG(
									"cg"), BGM("bgm"), COLLECTION("collection"), STATUS("status"), BATTLE(
											"battle"), ENCOUNTER("encounter"), INVENTORY("inventory"), FORK(
													"fork"), GAME_OVER("game_over");

	private String code;

	ScreenEnum(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return code;
	}

	public static ScreenEnum findScreenEnum(String code) {
		for (ScreenEnum screenEnum : ScreenEnum.values()) {
			if (screenEnum.toString().equals(code))
				return screenEnum;
		}
		return null;
	}
}
