package com.mygdx.resource;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.graphics.Texture;

public class Backgrounds {

	JSONParser parser = new JSONParser();
	JSONObject object;
	String keyOfVillage;
	String keyOfNPC;
	String keyOfNumber;
	String keyOfSceneNumber;
	String delimiter = "-";
	JSONObject fileName;

	// Backgrounds 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Backgrounds(JSONObject fileName) {
		this.fileName = fileName;
	}

	// Key값에 맞는 bgexture를 반환함
	public Texture getBackground(String scene) {
		keyParser(scene);
		JSONObject sceneObject = (JSONObject) fileName.get(keyOfNPC + keyOfNumber);
		String dir = (String) sceneObject.get(keyOfSceneNumber); //배경 경로 받아오기
		Texture image = new Texture(dir); //경로 넣어주기
		return image;
	}

	// 키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void keyParser(String scene) {
		String[] temp = scene.split(delimiter);
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfNumber = temp[2];
		keyOfSceneNumber = temp[3];
	}

}
