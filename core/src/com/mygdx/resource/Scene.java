package com.mygdx.resource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.resource.ImagePrint;

public class Scene {
	String scene;
	Scripts script;
	ImagePrint print;
	Texture txt, image;
	
	public Scene(SpriteBatch batch) {
		script = new Scripts(1);
		print = new ImagePrint(batch);
	}
	
	public void load(String scene) {
		this.scene = scene;
		String text = script.ScriptGetter(scene);
	}

	public void print() {
		// TODO Auto-generated method stub
		
	}
	
	public void show() {
	//	print.show(texture, pos);
	}

}
