package com.mygdx.event;

/*
 * Scene클래스는 GameScreen에서 벗어나 EventScreen에서 대화(이벤트)할 때 사용함. 
 * 사용방법: 
 * 1. Screen에서 setStage로 stage변수 전달.
 * 2. load("Prologue-scene-1")처럼 문자열 전달. 1은 첫번째를 의미
 * 3. start()로 첫 장면 호출
 * 4. 터치 혹은 이벤트 발생시 next()로 다음 신
 */

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
import com.mygdx.resource.ResourceFile;
import com.mygdx.resource.Scene;
import com.mygdx.resource.Scripts;

public class ChatScene implements Scene {

	// 신 진행 관련 변수

	private int counter; // load한 Event(예 Prologue)의 scene 갯수	

	// 스크립트, 케릭터 레이아웃 관리 테이블
	private Table table;

	// 스크립트
	private Scripts scripts;
	private Label script;

	// 케릭터
	private Characters character;
	private Image characterImage;

	// 배경
	private Backgrounds background;
	private Texture backgroundTexture;
	private SpriteBatch batch;
	private Stage stage;
	private String eventCode;

	//파싱되는 키값
	private String keyOfVillage;
	private String keyOfNPC;
	private String keyOfSerialNumber;
	int keyOfSceneNumber;

	//JsonFile
	private JSONObject jsonFile;
	private JSONArray jsonArray;

	public static final double TIME_TO_FADE = 60;
	private double timeAcc = 0;
	private float alpha = 0;

	public ChatScene(Table table, SpriteBatch batch, String eventCode,
			Stage stage) {
		this.batch = batch;
		this.eventCode = eventCode;
		this.stage = stage;

		//이벤트 코드 파싱
		parseEventCode(eventCode);

		//villageName을 받아와 동적으로 jsonFile할당 (0번 = script, 1번 = character, 2번 = background)
		jsonFile = ResourceFile.getJsonFile(keyOfVillage);
		scripts = new Scripts(jsonFile);
		character = new Characters(jsonFile);
		background = new Backgrounds(jsonFile);
		this.table = table;

		//sceneNumber 초기화
		clearSceneNumber();

		// scene 갯수를 받아옴. 배열값과의 비교를 위해 1을 빼준다.
		counter = getTotalSceneNumber(jsonArray) - 1;
	}

	// (1) eventCode는 Prologue-scene-1과 같은 형식(Prologue와 숫자 바뀜)
	public void load() {

		clear();
		// Background json 불러옴
		backgroundTexture = background.getBackground(eventCode,
				keyOfSceneNumber);

		// 스크립트 파싱

		script = new Label(scripts.getScript(eventCode, keyOfSceneNumber), Assets.skin);
		

		// 케릭터 불러옴
		characterImage = new Image(character.getImage(eventCode, keyOfSceneNumber));

		// size설정
		script.setFontScale(Assets.realWidth / 1280);
		script.setWrap(true);
		script.setWidth(Assets.realWidth * 0.8f);

		characterImage.setSize(Assets.realWidth * 0.2f,
				Assets.realHeight * 0.2f);
		characterImage.setPosition(0.2f * Assets.realWidth,
				0.7f * Assets.realHeight);

		// 뿌려주기
		table.bottom().left(); // table 전체를 화면 아래 쪽으로
		table.add(characterImage);
		table.add(script).width(script.getWidth());
	}

	// (3) 신(scene)을 넘기기 위한 함수, load() 이후 실행된다.
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
		jsonArray = (JSONArray) jsonFile.get(keyOfNPC + keyOfSerialNumber);
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
		table.clear();
	}

}
