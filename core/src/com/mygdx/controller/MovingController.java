package com.mygdx.controller;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public class MovingController {

	JSONParser parser = new JSONParser();
	JSONObject object;

	public MovingController(int filenum) {
		FileHandle file = Gdx.files.internal("data/worldmap.json");
		String text = file.readString();
		Object obj = JSONValue.parse(text);
		object = (JSONObject) obj;
	}

	public String keyParser(String key) {

		return key;

	}

	public String checkdirection() {

		if (key == haveright) {
			return "right";
		}
		if (key == haveleft) {
			return "left";
		}
		if (key == havetwin) {
			return "twin";
		}
	}

}
