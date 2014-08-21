package com.mygdx.resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.graphics.Texture;

public class Characters {

	JSONParser parser = new JSONParser();
	JSONObject object;
	String keyOfVillage;
	String keyOfNPC;
	String keyOfNumber;
	String keyOfSceneNumber;
	String delimiter = "-";
	JSONObject fileName;
	int count;

	// 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Characters(JSONObject fileName) {
		this.fileName = fileName;
	}

	//Key값에 맞는 이미지를 반환함
	public Texture getImage(String key) {
		keyParser(key); // 키값을 나눈다
		JSONObject keyObject = (JSONObject) fileName.get(keyOfNPC + keyOfNumber);
		String dir = (String) keyObject.get(keyOfSceneNumber); //이미지 경로 계산
		Texture image = new Texture(dir); //이미지 경로 주입
		return image;
	}

	// scene 갯수를 받아옴.
	public int getNumberOfScene(String npcAndNumKey) {
		JSONObject keyObject = (JSONObject) fileName.get(npcAndNumKey);
		String str = (String) keyObject.get("number");
		return Integer.parseInt(str);
	}

	//키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfNumber = temp[2];
		keyOfSceneNumber = temp[3];
	}
}