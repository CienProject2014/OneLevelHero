/*
 * String은 Texture에 들어가지 않는다.
 */
package com.mygdx.resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;

public class Scripts {

	JSONParser parser = new JSONParser();
	String[] keyString;
	String delimiter = "-";
	String fileName;

	public Scripts() {
		this("script_json");
	}

	// 스크립트 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Scripts(String fileName) {
		this.fileName = fileName;
	}

	// Key값에 맞는 스크립트를 반환함
	public String getScript(String key) {
		keyParser(key);
		JSONArray array = (JSONArray) Assets.script_json.get(keyString[0]);
		JSONObject sc = (JSONObject) array.get(0);
		String script = "";
		if (keyString.length == 2) {
			script = (String) sc.get(keyString[1]);
		} else if (keyString.length == 3) {
			script = (String) sc.get(keyString[1] + keyString[2]);
		} else {
			Gdx.app.log("에러", "keyString length가 적절하지 않음!");
		}
		return script;
	}

	// 키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		keyString = temp;
	}
}
