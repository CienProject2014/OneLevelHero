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
	String delimiter = "-";

	// Backgrounds 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Backgrounds(int filenum) {

	}

	// Key값에 맞는 bgexture를 반환함
	public Texture getBackground(String key) {
		keyParser(key);
		JSONObject sceneObject = (JSONObject) Assets.scene_background.get(keyOfVillage);
		//JSONObject sc = (JSONObject) sceneObject.get(1);
		String dir = (String) sceneObject.get(keyOfNPC + keyOfNumber);

		Texture image = new Texture(dir);

		return image;
	}

	// 키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfNumber = temp[2];
	}

}
