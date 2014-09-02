package com.mygdx.event;

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
	private EventKey eventKey;

	public SceneManager(EventKey eventKey) {
		this.eventKey = eventKey;
		jsonFile = JSONFile.getJsonFile(this.eventKey.getKeyOfVillage());
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
		backgroundImage = new Image(background.getBackground(eventKey, keyOfSceneNumber));
		script = new Label(scripts.getScript(eventKey, keyOfSceneNumber), Assets.skin);
		characterImage = new Image(character.getImage(eventKey, keyOfSceneNumber));
		makeUi();
	}

	private void makeUi() {
		stage = new SceneUi(script, characterImage, backgroundImage);
	}

	private void clearSceneNumber() {
		keyOfSceneNumber = 0;
	}

	private int getTotalSceneNumber(JSONArray jsonArray) {
		jsonArray = (JSONArray) jsonFile.get(eventKey.getKeyOfNpc() + "_" + eventKey.getKeyOfSerialNumber());
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
