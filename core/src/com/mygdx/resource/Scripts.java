/*
 * String은 Texture에 들어가지 않는다.
 */
package com.mygdx.resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Scripts {
	String keyOfVillage;
	String keyOfNPC;
	String keyOfSerialNumber;

	String delimiter = "-";
	JSONArray jsonArray;
	JSONObject fileName;

	// 스크립트 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정함
	public Scripts(JSONObject fileName) {
		this.fileName = fileName;
	}

	// eventCode값에 맞는 스크립트를 반환함
	public String getScript(String eventCode, int keyOfSceneNumber) {
		parseEventCode(eventCode);
		return parseJSONScript(keyOfSceneNumber);
	}

	// 과정(1) 키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	private void parseEventCode(String key) {
		String[] temp = key.split(delimiter);
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfSerialNumber = temp[2];
	}

	// 과정(2) jsonObject를 받아와서 리턴한다.
	private String parseJSONScript(int keyOfSceneNumber) {
		JSONArray jsonArray = (JSONArray) fileName.get(keyOfNPC + keyOfSerialNumber);
		JSONObject sceneObject = (JSONObject) jsonArray.get(keyOfSceneNumber);
		String script = (String) sceneObject.get("script");
		return script;
	}

}
