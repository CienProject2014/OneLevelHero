package com.mygdx.enums;

import org.json.simple.JSONObject;

import com.mygdx.resource.Assets;

public enum EventEnum {
	PROLOGUE(Assets.prologue_json), BLACKWOOD(Assets.blackwood_json);

	private JSONObject jsonObject;

	EventEnum(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

}
