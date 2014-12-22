package com.mygdx.model;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.badlogic.gdx.Gdx;

public class JsonFile {
	private String JsonFilePath;
	private JSONObject jsonFile;

	public String getJsonFilePath() {
		return JsonFilePath;
	}

	public void setJsonFilePath(String jsonFilePath) {
		JsonFilePath = jsonFilePath;
	}

	public JSONObject getJsonFile() {
		jsonFile = (JSONObject) JSONValue.parse(Gdx.files
				.internal(JsonFilePath).readString());
		return jsonFile;
	}

	public void setJsonFile(JSONObject jsonFile) {
		this.jsonFile = jsonFile;
	}
}
