/*
 * String은 Texture에 들어가지 않는다.
 */
package com.mygdx.resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Scripts {

	JSONParser parser = new JSONParser();
	String key1;
	String key2;
	String key3;
	JSONArray array;
	String delimiter = "-";
	JSONObject fileName;

	// 스크립트 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Scripts(JSONObject fileName) {
		this.fileName = fileName;
	}

	// Key값에 맞는 스크립트를 반환함
	public String getScript(String key) {
		keyParser(key);

		array = (JSONArray) fileName.get(key1);
		JSONObject sc = (JSONObject) array.get(0);
		String script = (String) sc.get(key2 + key3);
		return script;
	}

	// 키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		key1 = temp[0];
		key2 = temp[1];
		key3 = temp[2];
	}

}
