package com.mygdx.resource;

/*
 * Scene클래스는 GameScreen에서 벗어나 EventScreen에서 대화(이벤트)할 때 사용함. 
 */

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Scene {
	// 스크립트, 케릭터 레이아웃 관리 테이블
	Table table;
	
	// 스크립트
	Scripts scriptloader;
	Label script;
	String text;
	
	// 케릭터
	Characters characloader;
	Texture charactexture;
	Image character;
	
	// 배경
	Backgrounds bgloader;
	Texture bgtexture;
	SpriteBatch batch;

	String scene;
	
	// batch는 이미지 출력을 위해, table은 이미지와 텍스트 레이아웃을 위해
	public Scene(Table table, SpriteBatch batch) {		
		this.batch = batch;
		
		scriptloader = new Scripts(1);
		bgloader = new Backgrounds(1);
		characloader = new Characters(1);
		
		this.table = table;
	}
	
	// scene은 Prologue-scene1과 같은 형식(Prologue만 바뀜)
	public void load(String scene) {
		this.scene = scene;
		
		// 배경 불러옴
		bgtexture = bgloader.BackgroundGetter(scene);
		//background = new Texture("prologue/scene1.jpg");
		
		// 텍스트 파싱
		text = scriptloader.ScriptGetter(scene);
		script = new Label(text, Assets.skin);
		//String str = "Prologue"+"-"+"scene"+"-"+"1";
		
		// 케릭터 불러옴
		charactexture = characloader.ImageGetter(scene);
		character = new Image(charactexture);
	}
	
	// 처음 시작
	public void start() {
		table.bottom();	// table 전체를 화면 아래로 쪽으로
		table.add(character);
		table.add(script);
	}

	// 신(scene)을 넘기기 위한 함수
	public void next() {
		clear();	// 이전 scene을 클리어. 
		
		table.bottom();	// table 전체를 화면 아래로 쪽으로
		
		// TODO scene의 마지막이 되면 끝내는 체크 넣어야 함.
		String[] temp = scene.split("-");
		
		int num = Integer.parseInt(temp[2]);
		num++;	// 다음 신으로
		String scenenum = String.valueOf(num);
		scene = temp[0]+"-"+temp[1]+"-"+scenenum;
		
		load(scene);
		
		table.add(character);
		table.add(script);
	}

	// 배경 그림
	public void show() {
		batch.draw(bgtexture, 0, 0);
	}
	
	public void clear() {
		table.clear();
	}

}
