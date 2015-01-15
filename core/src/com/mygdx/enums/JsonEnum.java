package com.mygdx.enums;

public enum JsonEnum {
	HERO_JSON("hero_json"), VILLAGE_JSON("village_json"), NPC_JSON("npc_json"), WORLDMAP_JSON(
			"worldmap_json"), CREDIT_LIST("credit_list"), SKIN("skin"), DUNGEON_JSON(
			"dungeon_json"), ATLAS_UI_PATH("atlas_ui_path"), BACKGROUND_FILE_PATH(
			"background_file_path"), CHARACTER_FILE_PATH("character_file_path"), JSON_FILE_PATH(
			"json_file_path"), MONSTER_JSON("monster_json"), NULL_JSON(
			"null_json"), MONSTER_FILE_PATH("monster_file_path");

	private String jsonName;

	JsonEnum(String jsonName) {
		this.jsonName = jsonName;
	}

	@Override
	public String toString() {
		return jsonName;
	}

	public static JsonEnum findJsonEnum(String jsonName) {
		for (JsonEnum jsonEnum : JsonEnum.values()) {
			if (jsonEnum.toString().equals(jsonName))
				return jsonEnum;
		}
		return JsonEnum.NULL_JSON;
	}
}