package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.resource.Scene;
import com.mygdx.stagemanager.VillageStage;

public class VillageScreen implements Screen {
	
	OneLevelHero game;
	ImageButton optionButton;
	ImageButton minimapButton;
	ImageButton inventoryButton;
	TextButton statusButton1;
	TextButton statusButton2;
	TextButton statusButton3;
	Image background;
	Scene scene;
	
	VillageStage vs1;
	VillageStage vs2;
	
	int key = 2;
	
	public boolean state;
	
	public VillageScreen(OneLevelHero game){
		this.game = game;
	}
	
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(state == true){
			vs2.unfocusAll();
			Gdx.input.setInputProcessor(vs1);
			vs1.draw();
		}
		if(state == false){
			vs1.unfocusAll();
			Gdx.input.setInputProcessor(vs2);
			vs2.draw();
		}
			
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		vs1 = new VillageStage("Blackwood-0");
		Gdx.input.setInputProcessor(vs1);
		vs2 = new VillageStage("Blackwood-1");
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
