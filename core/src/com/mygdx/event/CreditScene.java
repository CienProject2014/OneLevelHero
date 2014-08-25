package com.mygdx.event;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Backgrounds;
import com.mygdx.resource.Characters;
import com.mygdx.resource.Scene;
import com.mygdx.resource.Scripts;

public class CreditScene implements Scene {
	// 신 진행 관련 변수
	String villageName;
	private int counter; // load한 Event(예 Prologue)의 scene 갯수

	// 스크립트, 케릭터 레이아웃 관리 테이블
	Table table;

	// 스크립트
	Scripts scripts;
	Label script;
	String text;

	// 케릭터
	Characters character;
	Texture characterTexture;
	Image characterImage;

	// 배경
	Backgrounds background;
	Texture backgroundTexture;
	SpriteBatch batch;
	Stage stage;
	String eventCode;

	// 파싱되는 키값
	String keyOfVillage;
	String keyOfNPC;
	String keyOfSerialNumber;
	int keyOfSceneNumber;

	// JsonFile
	JSONObject jsonFile;
	JSONArray jsonArray;
	String sceneNum;

	public static final double TIME_TO_FADE = 60;
	private double timeAcc = 0;
	private float alpha = 0;

	public CreditScene(Table table, SpriteBatch batch, Stage stage, String eventCode) {
		this.batch = batch;
		jsonFile = Assets.credit_list;
		scripts = new Scripts(jsonFile);
		character = new Characters(jsonFile);
		background = new Backgrounds(jsonFile);
		this.table = table;
		this.stage = stage;
		this.eventCode = eventCode;

		//이벤트 코드 파싱
		parseEventCode(eventCode);

		//sceneNumber 초기화
		clearSceneNumber();

		// scene 갯수를 받아옴. 배열값과의 비교를 위해 1을 빼준다.
		counter = getNumberOfScene(jsonArray) - 1;
	}

	// (1) eventCode는 Prologue-scene-1과 같은 형식(Prologue와 숫자 바뀜)
	public void load() {
		clear();
		// Background json 불러옴
		backgroundTexture = background.getBackground(eventCode,
				keyOfSceneNumber);

		// 스크립트 파싱
		text = scripts.getScript(eventCode, keyOfSceneNumber);
		script = new Label(text, Assets.skin);

		// 케릭터 불러옴
		characterTexture = character.getImage(eventCode, keyOfSceneNumber);
		characterImage = new Image(characterTexture);

		// size설정
		script.setFontScale(Assets.realWidth / 1280);
		script.setWrap(true);
		script.setWidth(Assets.realWidth * 0.8f);
		characterImage.setSize(Assets.realWidth * 0.2f, Assets.realHeight * 0.2f);
		characterImage.setPosition(0.2f * Assets.realWidth, 0.7f * Assets.realHeight);

		// 이미지 뿌려주기
		table.bottom().left(); // table 전체를 화면 아래 쪽으로
		table.add(characterImage);
		table.add(script).width(script.getWidth());
	}

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

	private int getNumberOfScene(JSONArray jsonArray) {
		jsonArray = (JSONArray) jsonFile.get(keyOfNPC + keyOfSerialNumber);
		int counter = jsonArray.size();
		return counter;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
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

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public void clear() {
		table.clear();
	}
}
