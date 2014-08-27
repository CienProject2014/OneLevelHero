package com.mygdx.event;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Backgrounds;
import com.mygdx.resource.JSONFile;
import com.mygdx.resource.Scripts;

public class SelectScene extends Stage implements Scene {

	// 신 진행 관련 변수
	private int counter; // load한 Event(예 Prologue)의 scene 갯수

	// 스크립트
	private Scripts scripts;
	private Label script;

	// 배경
	private Backgrounds background;
	private Texture backgroundTexture;
	private SpriteBatch batch;
	private String eventCode;

	// 파싱되는 키값
	private String keyOfVillage;
	private String keyOfNPC;
	private String keyOfSerialNumber;
	int keyOfSceneNumber;

	// JsonFile
	private JSONObject jsonFile;
	private JSONArray jsonArray;

	// Button
	private TextButtonStyle[] chatStyle;

	// EventTrigger
	EventTrigger eventTrigger;

	public static final double TIME_TO_FADE = 60;
	private double timeAcc = 0;
	private float alpha = 0;

	public SelectScene(SpriteBatch batch, String eventCode) {
		this.eventCode = eventCode;
		this.eventTrigger = EventTrigger.getInstance();
		this.batch = batch;
		// 이벤트 코드 파싱
		parseEventCode(eventCode);

		// villageName을 받아와 동적으로 jsonFile할당 (0번 = script, 1번 = character, 2번 =
		// background)
		jsonFile = JSONFile.getJsonFile(keyOfVillage);
		scripts = new Scripts(jsonFile);
		background = new Backgrounds(jsonFile);

		// sceneNumber 초기화
		clearSceneNumber();

		// scene 갯수를 받아옴. 배열값과의 비교를 위해 1을 빼준다.
		counter = getTotalSceneNumber(jsonArray) - 1;
		load();
	}

	// (1) eventCode는 Prologue-scene-1과 같은 형식(Prologue와 숫자 바뀜)
	public void load() {
		// Background json 불러옴
		backgroundTexture = background.getBackground(eventCode,
				keyOfSceneNumber);

		// 스크립트 파싱

		script = new Label(scripts.getScript(eventCode, keyOfSceneNumber),
				Assets.skin);

		script.setFontScale(Assets.realWidth / 1280);
		script.setWrap(true);
		// size설정
		script.setSize(Assets.realWidth * 0.781f, Assets.realHeight * 0.185f);

		// 위치 설정
		script.setPosition(Assets.realWidth * 0.109375f,
				Assets.realHeight * 0.185f);

		// 뿌려주기
		this.addActor(script);
	}

	/*
	 * public Stage makeButtonStage() { showEventButton(); chatButton[0]
	 * .setSize(Assets.realWidth * 0.5f, Assets.realHeight * 0.5f);
	 * chatButton[0].setPosition(Assets.realWidth * 0.109375f, Assets.realHeight
	 * * 0.74f); return buttonStage; }
	 */
	// (2) 신(scene)을 넘기기 위한 함수, load() 이후 실행된다.
	public void showNextScene() {
		// 다음 신으로 가기위한 세팅
		keyOfSceneNumber++;

		// 다음씬 로드하고 뿌려주기
		load();
		alpha = 0;
		timeAcc = 0;
	}

	// 다음 씬이 존재하는지 체크
	public boolean isNext() {
		if (keyOfSceneNumber < counter) {
			return true;
		} else if (keyOfSceneNumber == counter)
			return false;
		else
			return false;

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
		jsonArray = (JSONArray) jsonFile
				.get(keyOfNPC + "_" + keyOfSerialNumber);
		int counter = jsonArray.size();
		return counter;
	}

	// 배경 그림
	public void show(float delta) {

		alpha += (timeAcc / TIME_TO_FADE);
		if (alpha >= 1) {
			batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		} else {
			timeAcc += delta;
			batch.setColor(1.0f, 1.0f, 1.0f, alpha);
		}
		batch.draw(backgroundTexture, 0, 0, Assets.realWidth, Assets.realHeight);

	}

	public void clear() {
	}
}
