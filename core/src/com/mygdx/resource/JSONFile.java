package com.mygdx.resource;

import org.json.simple.JSONObject;

public class JSONFile {

	static JSONObject jsonFile;

	public static JSONObject getJsonFile(String villageName) {
		//JSONFileList가 비어있는지 체크

		if (villageName.equals("Blackwood")) {
			jsonFile = Assets.blackwood_json;
		} else {
			jsonFile = Assets.prologue_json;
		}
		return jsonFile;
	}
}
