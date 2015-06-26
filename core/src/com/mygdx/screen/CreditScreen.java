package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;

/**
 * 기여자 목록을 뿌려주는 Screen 클래스
 * 
 * @author Velmont
 *
 */
public class CreditScreen implements Screen {
	@Autowired
	private ScreenFactory screenFactory;
	private SpriteBatch batch;
	private TextButton backButton;
	private Table table;
	private Stage stage;
	private String event;

	public CreditScreen(String event) {
		this.event = event;
	}

	public CreditScreen() {

	}

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		// scene.show(delta); // 배경 출력
		batch.end();

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		batch = new SpriteBatch();
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		showEventScene();

		Gdx.input.setInputProcessor(stage);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// if (scene.isNext()) {
				// scene.showNextScene();
				// } else {
				// // back to previous screen
				// that invoke this event screen

				// NOT JUST VILLAGESCREEN BUT PREVIOUS SCREEN
				screenFactory.show(ScreenEnum.MENU);

				// }
				return true;
			}
		});
	}

	private void showEventScene() {
		/*
		 * EventManager.getInstance().setEventCode("Crd-scene-1");
		 * EventManager.getInstance().setEventType(EventTypeEnum.CREDIT);
		 * 
		 * if (EventManager.getInstance().getEventType() ==
		 * EventTypeEnum.CREDIT) scene = new CreditScene(stage, batch,
		 * EventManager.getInstance() .getEventCode()); else
		 * Gdx.app.log("Error", "Scene 주입 에러"); // 파싱을 하기 위한 로드 scene.load();
		 */
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

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

}
