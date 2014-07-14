package com.mygdx.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.resource.Images;

public class Scene {
	String scene;
	Scripts script;
	Images print;
	Texture ttext, image;
	
	public Scene(SpriteBatch batch) {
		script = new Scripts(1);
		print = new Images(batch);
	}
	
	public void load(String scene) {
		this.scene = scene;
		String text = script.ScriptGetter(scene);
		ttext = new Texture(text);
		image = new Texture(Gdx.files.internal("prologue/scene2.jpg"));
	}

	public void print() {
		// TODO Auto-generated method stub
		
	}
	
	public void show() {
		print.show(texture, pos);
	}

}
