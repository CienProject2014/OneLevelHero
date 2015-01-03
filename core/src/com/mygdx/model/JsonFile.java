package com.mygdx.model;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.badlogic.gdx.Gdx;

public class JsonFile implements AssetsFile<JSONObject> {
	private String filePath;
	private JSONObject file;

	public JSONObject getFile() {
		file = (JSONObject) JSONValue.parse(Gdx.files.internal(filePath)
				.readString());
		return file;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setFile(JSONObject file) {
		this.file = file;
	}

}
