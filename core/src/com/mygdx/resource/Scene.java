package com.mygdx.resource;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class Scene {
	JSONParser parser = new JSONParser();
	JSONObject object;
	String key1;
	String key2;
	String delimiter = "-";
	
	
	SpriteBatch batch;
	Table table;
	String scene;
	Scripts script;
	Texture img;
	
	// batch는 이미지 출력을 위해, table은 이미지와 텍스트 레이아웃을 위해
	public Scene(SpriteBatch batch, Table table) {
		FileHandle file = Gdx.files.internal("data/image.json");
		String text = file.readString();
		Object obj = JSONValue.parse(text);
		object = (JSONObject) obj;
		
		script = new Scripts(1);
		this.table = table;
	}
	
	// scene은 Prologue-scene1과 같은 형식(Prologue만 바뀜)
	public void load(String scene) {
		this.scene = scene;
		
		// 텍스트 파싱
		String text = script.ScriptGetter(scene);
		//String str = "Prologue"+"-"+"scene"+"1";
		
		// 이미지 불러옴
		img = new Texture("prologue/scene2.jpg");
	}

	// 신(scene)을 넘기기 위한 함수
	public void print() {
		
	}
	
	// batch.begin과 end상에 넣어서 실행할 함수
	public void show() {
		batch.draw(img, 0, 0);
		
	}
	
	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		key1 = temp[0];
		key2 = temp[1];
	}

}
