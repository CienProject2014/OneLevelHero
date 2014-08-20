package com.mygdx.resource;

/*
 * Scene클래스는 GameScreen에서 벗어나 EventScreen에서 대화(이벤트)할 때 사용함. 
 * 사용방법: 
 * 1. Screen에서 setStage로 stage변수 전달.
 * 2. load("Prologue-scene-1")처럼 문자열 전달. 1은 첫번째를 의미
 * 3. start()로 첫 장면 호출
 * 4. 터치 혹은 이벤트 발생시 next()로 다음 신
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.enums.ScriptsEnum;

public class EventScene {
	// 신 진행 관련 변수
	int num; // load한 Event(예 Prologue)의 scene 갯수
	public boolean isEnd;

	// 스크립트, 케릭터 레이아웃 관리 테이블
	Table table;

	// 스크립트
	Scripts scripts;
	Label script;
	String text;

	// 케릭터
	Characters characterLoader;
	Texture characterTexture;
	Image character;

	// 배경
	Backgrounds bgloader;
	Texture bgtexture;
	SpriteBatch batch;
	Stage stage;
	String scene;

	public static final double TIME_TO_FADE = 60;
	private double timeAcc = 0;
	private float alpha = 0;

	public EventScene(Table table, SpriteBatch batch) {

		isEnd = false;

		this.batch = batch;

		scripts = new Scripts(ScriptsEnum.SCRIPT_JSON);
		bgloader = new Backgrounds(1);
		characterLoader = new Characters(1);

		this.table = table;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	// scene은 Prologue-scene-1과 같은 형식(Prologue와 숫자 바뀜)
	public void load(String scene) {

		this.scene = scene;
		// scene 갯수를 받아옴. scene_character.json에 임시로 저장.
		num = characterLoader.getNum(scene.split("-")[0]);

		// 배경 json 불러옴
		bgtexture = bgloader.getBackground(scene);

		// 텍스트 파싱
		text = scripts.getScript(scene);
		script = new Label(text, Assets.skin);

		// String str = "Prologue"+"-"+"scene"+"-"+"1";
		Assets.loadSize(stage);

		// 케릭터 불러옴
		characterTexture = characterLoader.ImageGetter(scene);
		character = new Image(characterTexture);

		// size설정

		script.setFontScale(Assets.realWidth / 1280);
		script.setWrap(true);
		script.setWidth(Assets.realWidth * 0.8f);

		character.setSize(Assets.realWidth * 0.2f, Assets.realHeight * 0.2f);
		character.setPosition(0.2f * Assets.realWidth, 0.7f * Assets.realHeight);

	}

	// 처음 시작
	public void start() {
		table.bottom().left(); // table 전체를 화면 아래로 쪽으로
		table.add(character);
		table.add(script).width(script.getWidth());
	}

	// 신(scene)을 넘기기 위한 함수
	public void next() {

		clear();

		// table 전체를 화면 아래로 쪽으로
		table.bottom();

		String[] temp = scene.split("-");
		int now = Integer.parseInt(temp[2]); // next를 호출하는 상황은 첫 화면에서 +1된 상황이므로

		// 다음 신으로------------------------------
		if (isNext(now))
			now++;
		else
			isEnd = true;
		String scenenum = String.valueOf(now);
		scene = temp[0] + "-" + temp[1] + "-" + scenenum;
		// ----------------------------------------

		load(scene);

		table.add(character);
		table.add(script).width(script.getWidth());
		alpha = 0;
		timeAcc = 0;
	}

	public boolean isNext(int i) {
		if (i < num)
			return true;
		else if (i == num)
			return false;
		else
			return false;
	}

	public boolean isEnd() {
		return isEnd;
	}

	// 배경 그림
	public void show(float delta) {

		alpha += (timeAcc / TIME_TO_FADE);
		if (alpha >= 1) {
			batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
		}

		else {
			timeAcc += delta;
			batch.setColor(1.0f, 1.0f, 1.0f, alpha);
		}
		batch.draw(bgtexture, 0, 0, Assets.realWidth, Assets.realHeight);

	}

	public void clear() {
		table.clear();
	}

}
