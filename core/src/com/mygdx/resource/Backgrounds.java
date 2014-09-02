package com.mygdx.resource;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.event.EventKey;

public class Backgrounds {

	JSONParser parser = new JSONParser();
	JSONObject object;
	String keyOfVillage;
	String keyOfNPC;
	String keyOfNumber;
	String delimiter = "-";
	JSONObject fileName;
	JSONArray jsonArray;
	Texture image;
	HashMap<String, Object> resourceFileList = Assets.resourceFileList;
	private EventKey eventKey;

	// Backgrounds 클래스를 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Backgrounds(JSONObject fileName) {
		this.fileName = fileName;
	}

	public Texture getBackground(EventKey eventKey) {
		this.eventKey = eventKey;
		return parseJSONImage();
	}

	// Key값에 맞는 bgexture를 반환함
	public Texture getBackground(EventKey eventKey, int keyOfSceneNumber) {
		this.eventKey = eventKey;
		return parseJSONImage(keyOfSceneNumber);
	}

	// 과정 (2) JSON에있는 이미지를 받아온다.
	private Texture parseJSONImage(int keyOfSceneNumber) {
		jsonArray = (JSONArray) fileName.get(eventKey.getKeyOfNpc() + "_" + eventKey.getKeyOfSerialNumber());
		JSONObject sceneObject = (JSONObject) jsonArray.get(keyOfSceneNumber);
		String imageName = (String) sceneObject.get("background");
		if (resourceFileList.containsKey(imageName))
			image = (Texture) resourceFileList.get(imageName); // 이미지 경로 주입
		else
			Gdx.app.log("error", "imageName not found - background : " + String.valueOf(imageName));

		return image;
	}

	private Texture parseJSONImage() {
		return parseJSONImage(0);
	}

}
