package com.mygdx.resource;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

public class Characters {

	JSONParser parser = new JSONParser();
	JSONObject object;
	String keyOfVillage;
	String keyOfNPC;
	String keyOfNumber;
	String delimiter = "-";

	// 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Characters(int filenum) {
		FileHandle file = Gdx.files.internal("data/scene_character.json");
		String text = file.readString();
		Object obj = JSONValue.parse(text);
		object = (JSONObject) obj;
	}

	//Key값에 맞는 이미지를 반환함
	public Texture ImageGetter(String key) {
		keyParser(key);

		JSONObject keyObject = (JSONObject) object.get(keyOfVillage);
		String dir = (String) keyObject.get(keyOfNPC + keyOfNumber);

		Texture image = new Texture(dir);
		return image;
	}

	public int getNum(String key) {
		JSONObject keyObject = (JSONObject) object.get(key);
		String str = (String) keyObject.get("number");
		return Integer.parseInt(str);
	}

	//키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfNumber = temp[2];
	}
}