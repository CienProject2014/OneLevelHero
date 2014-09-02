package com.mygdx.resource;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.event.EventKey;
import com.mygdx.unit.Hero;
import com.mygdx.util.CurrentManager;

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
	Button[] eventButton;
	int count;
	HashMap<String, Object> resourceFileList = Assets.resourceFileList;
	private EventKey eventKey;

	public Characters() {

	}

	// 생성해 줄 때 어떤 파일을 읽어올지 지정할 예정
	public Characters(JSONObject fileName) {
		this.fileName = fileName;
	}

	// Key값에 맞는 이미지를 반환함
	public Texture getImage(EventKey eventKey, int keyOfSceneNumber) {
		this.eventKey = eventKey;
		//JSON에서 불러온 이미지를 리턴한다
		return parseJSONImage(keyOfSceneNumber);
	}

	public Texture getImage(EventKey eventKey) {
		return getImage(eventKey, 1);
	}

	public Image[] getBattleMemberImage() {

		ArrayList<Hero> battleMemberList = CurrentManager.getInstance().party.getBattleMemberList();

		Image[] memberImage = new Image[battleMemberList.size()];
		for (int i = 0; i < battleMemberList.size(); i++) {

			memberImage[i] = (Image) resourceFileList.get(battleMemberList.get(i).getUnitName());
		}
		return memberImage;
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
		JSONArray jsonArray = (JSONArray) fileName.get(eventKey.getKeyOfNpc() + "_" + eventKey.getKeyOfSerialNumber());
		JSONObject sceneObject = (JSONObject) jsonArray.get(keyOfSceneNumber);
		String imageName = (String) sceneObject.get("character"); //이미지 이름 추출

		if (resourceFileList.containsKey(imageName))
			image = (Texture) resourceFileList.get(imageName); //이미지 경로 주입
		else
			Gdx.app.log("error", "imageName not found - character :" + String.valueOf(imageName));

		return image;
	}
}