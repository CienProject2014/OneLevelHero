package com.mygdx.resource;

import java.util.ArrayList;

import org.json.simple.JSONObject;

public class JSONFileList {
	static private ArrayList<JSONObject> JSONFileList = new ArrayList<JSONObject>();

	public static ArrayList<JSONObject> getJsonFileList(String villageName) {
		//JSONFileList가 비어있는지 체크
		checkIsEmpty();

		if (villageName.equals("Blackwood")) {
			JSONFileList.add(Assets.blackwood_script);
			JSONFileList.add(Assets.blackwood_character);
			JSONFileList.add(Assets.blackwood_background);
		} else {
			JSONFileList.add(Assets.prologue_script);
			JSONFileList.add(Assets.prologue_character);
			JSONFileList.add(Assets.prologue_background);
		}
		return JSONFileList;
	}

	private static void checkIsEmpty() {
		if (!JSONFileList.isEmpty()) {
			JSONFileList.clear();
		}

	}
}
