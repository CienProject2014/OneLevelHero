/*
 * Texture 생성자 안에 파일의 경로를 적을때 Gdx.files.internal 안붙이고
 * 그냥 파일 이름을 String으로 써주면 됨
 */
package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.ChatScene;
import com.mygdx.util.EventManager;

public class PrologueScreen implements Screen {

	private Stage stage;
	private SpriteBatch batch;
	private ChatScene chatScene;

	public PrologueScreen() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		chatScene.show(delta); // 배경 출력
		batch.end();
		stage.draw();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		stage = new ChatScene(batch, EventManager.getInstance().getEventCode());
		batch = new SpriteBatch();
		showEventScene();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (chatScene.isNext()) {
					chatScene.showNextScene();
				} else {
					// NOT JUST VILLAGESCREEN BUT PREVIOUS SCREEN

					EventManager.getInstance().setEventCode(
							"Blackwood-parath-prologue");
					EventManager.getInstance().setEventType(EventTypeEnum.CHAT);
					new ScreenController(ScreenEnum.EVENT);

				}

				return true;
			}
		});

	}

	private void showEventScene() {
		// 인스턴스 생성
		chatScene = new ChatScene(batch, EventManager.getInstance()
				.getEventCode());

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
		chatScene.clear();
	}

}
