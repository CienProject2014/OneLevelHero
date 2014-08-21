package com.mygdx.event;

/*
 * Scene클래스는 GameScreen에서 벗어나 EventScreen에서 대화(이벤트)할 때 사용함. 
 * 사용방법: 
 * 1. Screen에서 setStage로 stage변수 전달.
 * 2. load("Prologue-scene-1")처럼 문자열 전달. 1은 첫번째를 의미
 * 3. start()로 첫 장면 호출
 * 4. 터치 혹은 이벤트 발생시 next()로 다음 신
 */

import java.util.ArrayList;

import org.json.simple.JSONObject;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Backgrounds;
import com.mygdx.resource.Characters;
import com.mygdx.resource.JSONFileList;
import com.mygdx.resource.Scripts;
import com.mygdx.util.ScreenManager;

public class ChatScene {
	// 신 진행 관련 변수
	OneLevelHero game = ScreenManager.getGame();
	String villageName;
	private int counter; // load한 Event(예 Prologue)의 scene 갯수

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public void setEnd(boolean end) {
		this.end = end;
	}

	private boolean end;

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

	//파싱되는 키값
	String keyOfVillage;
	String keyOfNPC;
	String keyOfSerialNumber;
	String keyOfSceneNumber;

	//JsonFileList
	ArrayList<JSONObject> jsonFileList;
	String sceneNum;

	public static final double TIME_TO_FADE = 60;
	private double timeAcc = 0;
	private float alpha = 0;

	public ChatScene(Table table, SpriteBatch batch) {
		this.batch = batch;
		villageName = game.eventManager.getEventVillageName();

		//villageName을 받아와 동적으로 jsonFile할당 (0번 = script, 1번 = character, 2번 = background)
		jsonFileList = JSONFileList.getJsonFileList(villageName);
		scripts = new Scripts(jsonFileList.get(0));
		character = new Characters(jsonFileList.get(1));
		background = new Backgrounds(jsonFileList.get(2));
		this.table = table;
	}

	// (1) eventCode는 Prologue-scene-1-1과 같은 형식(Prologue와 숫자 바뀜)
	public void load(String eventCode) {
		this.eventCode = eventCode;
		//이벤트 코드 파싱
		parseEventCode(eventCode);

		// scene 갯수를 받아옴. character에서 임시로 NPC+Serial을 통해 가져옴 
		counter = character.getNumberOfScene(keyOfNPC + keyOfSerialNumber);

		// Background json 불러옴
		backgroundTexture = background.getBackground(eventCode);

		// 스크립트 파싱
		text = scripts.getScript(eventCode);

		script = new Label(text, Assets.skin);

		Assets.loadSize(stage);

		// 케릭터 불러옴
		characterTexture = character.getImage(eventCode);
		characterImage = new Image(characterTexture);

		// size설정
		script.setFontScale(Assets.realWidth / 1280);
		script.setWrap(true);
		script.setWidth(Assets.realWidth * 0.8f);

		characterImage.setSize(Assets.realWidth * 0.2f, Assets.realHeight * 0.2f);
		characterImage.setPosition(0.2f * Assets.realWidth, 0.7f * Assets.realHeight);

	}

	// (2) 처음 시작
	public void start() {
		table.bottom().left(); // table 전체를 화면 아래로 쪽으로
		table.add(characterImage);
		table.add(script).width(script.getWidth());
	}

	public void parseEventCode(String eventCode) {
		String[] temp = eventCode.split("-");
		keyOfVillage = temp[0];
		keyOfNPC = temp[1];
		keyOfSerialNumber = temp[2];
		keyOfSceneNumber = temp[3];
	}

	// (3) 신(scene)을 넘기기 위한 함수, load() 이후 실행된다.
	public void next() {
		clear();
		// table 전체를 화면 아래로 쪽으로
		table.bottom();

		parseEventCode(eventCode);
		int now = Integer.parseInt(keyOfSceneNumber); // next를 호출하는 상황은 첫 화면에서 +1된 상황이므로

		// 다음 신으로------------------------------
		if (isNext(now))
			now++;
		else
			setEnd(true);
		keyOfSceneNumber = String.valueOf(now);
		eventCode = keyOfVillage + "-" + keyOfNPC + "-" + keyOfSerialNumber + "-" + keyOfSceneNumber;
		// ----------------------------------------

		load(eventCode);

		table.add(characterImage);
		table.add(script).width(script.getWidth());
		alpha = 0;
		timeAcc = 0;
	}

	public boolean isNext(int i) {
		if (i < counter)
			return true;
		else if (i == counter)
			return false;
		else
			return false;
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

	public boolean isEnd() {
		return end;
	}

}
