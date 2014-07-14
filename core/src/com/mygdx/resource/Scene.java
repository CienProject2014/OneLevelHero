package com.mygdx.resource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Scene {	
	Scripts script;
	Label textlabel;
	String text;
	
	SpriteBatch batch;
	Table table;
	String scene;
	Image character;
	Texture background;
	
	// batch는 이미지 출력을 위해, table은 이미지와 텍스트 레이아웃을 위해
	public Scene(Table table, SpriteBatch batch) {		
		this.batch = batch;
		script = new Scripts(1);
		this.table = table;
	}
	
	// scene은 Prologue-scene1과 같은 형식(Prologue만 바뀜)
	public void load(String scene) {
		this.scene = scene;
		
		// 배경
		background = new Texture("prologue/scene1.jpg");
		
		// 텍스트 파싱
		text = script.ScriptGetter(scene);
		textlabel = new Label(text, Assets.skin);
		//String str = "Prologue"+"-"+"scene"+"1";
		
		// 이미지 불러옴
		character = new Image(new Texture("rabbit2.png"));
	}

	// 신(scene)을 넘기기 위한 함수
	public void print() {
		table.bottom();
		table.add(character);
		table.add(textlabel);
	}

	public void show() {

		batch.draw(background, 0, 0);

	}

}
