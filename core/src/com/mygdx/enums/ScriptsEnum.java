package com.mygdx.enums;

import org.json.simple.JSONObject;

import com.mygdx.resource.Assets;

public enum ScriptsEnum {

	SCRIPT_JSON(Assets.prologue_json), CREDIT_LIST(Assets.credit_list);

	private JSONObject jsonObject;

	ScriptsEnum(JSONObject jsonObject) {
		this.jsonObject = jsonObject;
	}

	public JSONObject getJsonObject() {
		return jsonObject;
	}

}
