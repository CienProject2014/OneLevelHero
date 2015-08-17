package com.mygdx.enums;

public enum JsonEnum {
	HERO_JSON("hero_json"), VILLAGE_JSON("village_json"), NPC_JSON("npc_json"), WORLDMAP_JSON(
			"worldmap_json"), CREDIT_LIST("credit_list"), SKIN("skin"), DUNGEON_JSON(
			"dungeon_json"), ATLAS_UI_PATH("atlas_ui_path"), BACKGROUND_FILE_PATH(
			"background_file_path"), CHARACTER_FILE_PATH("character_file_path"), WEAPON_JSON(
			"weapon_json"), ACCESSORY_JSON("accessory_json"), SHIELD_JSON(
			"shield_json"), CLOTHES_JSON("clothes_json"), CONSUMABLES_JSON(
			"consumables_json"), ETC_ITEM_JSON("etc_item_json"), GAME_OBJECT_JSON(
			"game_object_json"), JSON_FILE_PATH("json_file_path"), MONSTER_JSON(
			"monster_json"), NULL_JSON("null_json"), MONSTER_FILE_PATH(
			"monster_file_path"), MUSIC_FILE_PATH("music_file_path"), WORLD_NODE_MUSIC_LIST(
			"world_node_music_list"), BATTLE_MUSIC_LIST("battle_music_list"), EVENT_MUSIC_LIST(
			"event_music_list"), MOVING_MUSIC_LIST("moving_music_list"), ANIMATION_SHEET_FILE_PATH(
			"animation_sheet_file_path"), SKILL_JSON("skill_json"), STORY_JSON(
			"story_json"), BATTLE_UI_FILE_PATH("battle_ui_file_path"), UI_CONSTANTS(
			"ui_constants"), SCENE_CONSTANTS_JSON("scene_constants_json"), MONSTER_FIELD_JSON(
			"monster_field_json");

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
