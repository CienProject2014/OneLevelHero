package com.mygdx.resource;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Characters {

	JSONParser parser = new JSONParser();
	JSONObject object;
	String keyOfVillage;
	String keyOfNPC;
	String keyOfNumber;
	String delimiter = "-";
	JSONObject fileName;
	JSONArray jsonArray;
	Texture image;
	int count;
	HashMap<String, Object> resourceFileList = Assets.resourceFileList;

	// 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Characters(JSONObject fileName) {
		this.fileName = fileName;
	}

	// Key값에 맞는 이미지를 반환함
	public Texture getImage(String key, int keyOfSceneNumber) {
		keyParser(key); // 키값을 나눈다

		//JSON에서 불러온 이미지를 리턴한다
		return parseJSONImage(keyOfSceneNumber);
	}

	// 과정 (1) 키값을 받아서 파싱을 한다("-"를 기준으로 나눔)
	private void keyParser(String key) {
		String[] temp = key.split(delimiter);
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfNumber = temp[2];
	}

	// 과정 (2) JSON에서 불러온 이미지를 리턴
	private Texture parseJSONImage(int keyOfSceneNumber) {
		JSONArray jsonArray = (JSONArray) fileName.get(keyOfNPC + keyOfNumber);
		JSONObject sceneObject = (JSONObject) jsonArray.get(keyOfSceneNumber);
		String imageName = (String) sceneObject.get("character"); //이미지 이름 추출

		if (resourceFileList.containsKey(imageName))
			image = (Texture) resourceFileList.get(imageName); //이미지 경로 주입
		else
			Gdx.app.log("error", "imageName not found - character");

		return image;
	}
}