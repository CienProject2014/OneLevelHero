/*
 * String은 Texture에 들어가지 않는다.
 */
package com.mygdx.resource;

import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Scripts {
	private String keyOfNPC;
	private String keyOfSerialNumber;
	private String script;
	private JSONArray scriptArray;
	private Random random;
	private String delimiter = "-";
	private JSONObject fileName;

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
		keyOfNPC = temp[1];
		keyOfSerialNumber = temp[2];
	}

	// 과정(2) jsonObject를 받아와서 리턴한다.
	private String parseJSONScript(int keyOfSceneNumber) {
		JSONArray jsonArray = (JSONArray) fileName.get(keyOfNPC + "_" + keyOfSerialNumber);
		JSONObject sceneObject = (JSONObject) jsonArray.get(keyOfSceneNumber);
		if (keyOfSerialNumber.equals("0")) {
			scriptArray = (JSONArray) sceneObject.get("script");
			random = new Random(System.currentTimeMillis());
			int randomNumber = (int) (random.nextInt(scriptArray.size()));
			script = (String) scriptArray.get(randomNumber);

		} else {
			script = (String) sceneObject.get("script");
		}

		return script;
	}
}
