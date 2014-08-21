package com.mygdx.enums;

import org.json.simple.JSONObject;

import com.mygdx.resource.Assets;

public enum EventEnum {
	PROLOGUE_SCRIPT(Assets.prologue_script), PROLOGUE_CHARACTER(Assets.prologue_character), PROLOGUE_BACKGROUND(Assets.prologue_background), BLACKWOOD_SCRIPT(
			Assets.blackwood_script), BLACKWOOD_CHARACTER(Assets.blackwood_character), BLACKWOOD_BACKGROUND(Assets.blackwood_background);
	private JSONObject jsonObject;

	EventEnum(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

}
