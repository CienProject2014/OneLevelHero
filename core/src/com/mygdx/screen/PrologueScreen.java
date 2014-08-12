/*
 * Texture 생성자 안에 파일의 경로를 적을때 Gdx.files.internal 안붙이고
 * 그냥 파일 이름을 String으로 써주면 됨
 */
package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.LoadLauncher;
import com.mygdx.resource.PrologueScene;
import com.mygdx.resource.SaveVersion;
import com.mygdx.resource.Scripts;

public class PrologueScreen implements Screen {
	OneLevelHero game;
	Stage stage;
	Texture img;
	Image[] image;
	Scripts script;
	Label textlabel;
	TextureRegion region;
	Texture texture;
	SpriteBatch batch;
	PrologueScene scene;
	Table table;

	public PrologueScreen(OneLevelHero game) {
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		scene.show(delta); // 배경 출력
		batch.end();

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		stage = new Stage();
		batch = new SpriteBatch();
		table = new Table();
		table.setFillParent(true);

		scene = new PrologueScene(table, batch);
		scene.loadstage(stage);
		scene.load("Prologue-scene-1");
		scene.start();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				scene.next();

				if (scene.isEnd) {
					game.loadLauncher = new LoadLauncher(game); //각종 설정들을 올린다.
					game.loadLauncher.jsonSetting();
					game.currentManager.setVersion(SaveVersion.NEW);
					game.setScreen(new VillageScreen(game, "Blackwood"));
				}

				return true;
			}
		});

		stage.addActor(table);

	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
