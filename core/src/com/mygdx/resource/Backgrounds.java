package com.mygdx.resource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.graphics.Texture;

public class Backgrounds {

	JSONParser parser = new JSONParser();
	JSONObject object;
	String keyOfVillage;
	String keyOfNPC;
	String keyOfNumber;
	String delimiter = "-";
	JSONObject fileName;
	JSONArray jsonArray;

	// Backgrounds 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Backgrounds(JSONObject fileName) {
		this.fileName = fileName;
	}

	public Texture getBackground(String eventCode) {
		keyParser(eventCode);

		return parseJSONImage();
	}

	// Key값에 맞는 bgexture를 반환함
	public Texture getBackground(String eventCode, int keyOfSceneNumber) {
		keyParser(eventCode);
		return parseJSONImage(keyOfSceneNumber);
	}

	// 과정 (1) 키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void keyParser(String scene) {
		String[] temp = scene.split(delimiter);
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfNumber = temp[2];
	}

	//과정 (2) JSON에있는 이미지를 받아온다.
	private Texture parseJSONImage(int keyOfSceneNumber) {
		jsonArray = (JSONArray) fileName.get(keyOfNPC + keyOfNumber);
		JSONObject sceneObject = (JSONObject) jsonArray.get(keyOfSceneNumber);
		String dir = (String) sceneObject.get("background"); //배경 경로 받아오기
		Texture image = new Texture(dir); //경로 넣어주기
		return image;
	}

	private Texture parseJSONImage() {
		return parseJSONImage(0);
	}

}
