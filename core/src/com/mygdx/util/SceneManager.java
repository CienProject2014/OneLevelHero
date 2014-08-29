package com.mygdx.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Backgrounds;
import com.mygdx.resource.Characters;
import com.mygdx.resource.JSONFile;
import com.mygdx.resource.Scripts;
import com.mygdx.ui.SceneUi;

public class SceneManager {
	private String eventCode;
	private String keyOfVillage;
	private String keyOfNPC;
	private String keyOfSerialNumber;
	private JSONObject jsonFile;
	private JSONArray jsonArray;
	private Scripts scripts;
	private Label script;
	private Characters character;
	private Backgrounds background;
	private Image characterImage;
	private Image backgroundImage;
	private int keyOfSceneNumber;
	private int counter;
	private SceneUi stage;

	public SceneManager(String eventCode) {
		this.eventCode = eventCode;
		parseEventCode(eventCode);
		jsonFile = JSONFile.getJsonFile(keyOfVillage);
		scripts = new Scripts(jsonFile);
		character = new Characters(jsonFile);
		background = new Backgrounds(jsonFile);
		clearSceneNumber();
		counter = getTotalSceneNumber(jsonArray) - 1;
		makeResource();
	}

	public SceneUi getSceneUi() {
		return stage;
	}

	private void makeResource() {
		backgroundImage = new Image(background.getBackground(eventCode, keyOfSceneNumber));
		script = new Label(scripts.getScript(eventCode, keyOfSceneNumber), Assets.skin);
		characterImage = new Image(character.getImage(eventCode, keyOfSceneNumber));
		makeUi();
	}

	private void makeUi() {
		stage = new SceneUi(script, characterImage, backgroundImage);
	}

	private void parseEventCode(String eventCode) {
		String[] temp = eventCode.split("-");
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfSerialNumber = temp[2];
	}

	private void clearSceneNumber() {
		keyOfSceneNumber = 0;
	}

	private int getTotalSceneNumber(JSONArray jsonArray) {
		jsonArray = (JSONArray) jsonFile.get(keyOfNPC + "_" + keyOfSerialNumber);
		counter = jsonArray.size();
		return counter;
	}

	public boolean isNext() {
		if (keyOfSceneNumber < counter) {
			return true;
		} else if (keyOfSceneNumber == counter)
			return false;
		else
			return false;

	}

	public void showNextScene() {
		// 다음 신으로 가기위한 세팅
		keyOfSceneNumber++;

		// 다음씬 로드하고 뿌려주기
		makeResource();
	}
}
