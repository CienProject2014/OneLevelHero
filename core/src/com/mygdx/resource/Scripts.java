/*
 * String은 Texture에 들어가지 않는다.
 */
package com.mygdx.resource;

import org.json.simple.JSONObject;

public class Scripts {
	String keyOfVillage;
	String keyOfNPC;
	String keyOfSerialNumber;
	String keyOfSceneNumber;

	String delimiter = "-";
	JSONObject fileName;

	// 스크립트 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Scripts(JSONObject fileName) {
		this.fileName = fileName;
	}

	// eventCode값에 맞는 스크립트를 반환함
	public String getScript(String eventCode) {
		parseEventCode(eventCode);
		JSONObject keyObject = (JSONObject) fileName.get(keyOfNPC + keyOfSerialNumber);
		String script = (String) keyObject.get(keyOfSceneNumber);
		return script;
	}

	// 키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void parseEventCode(String key) {
		String[] temp = key.split(delimiter);
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfSerialNumber = temp[2];
		keyOfSceneNumber = temp[3];
	}
}
