package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.ui.SceneUi;
import com.mygdx.ui.SelectButtonUi;
import com.mygdx.util.EventManager;
import com.mygdx.util.SceneManager;

public class EventScreen implements Screen {

	private SceneManager scene;
	private SceneUi stage;
	private Stage buttonStage;

	private String event;

	public EventScreen() {

	}

	public EventScreen(String event) {
		this.event = event;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.draw();
		if (EventManager.getInstance().getEventType() == EventTypeEnum.SELECT) {
			buttonStage.draw();
		}

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		scene = new SceneManager(EventManager.getInstance().getEventCode());
		buttonStage = new SelectButtonUi();
		stage = scene.getSceneUi();

		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(0, buttonStage);
		multiplexer.addProcessor(1, stage);
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);

		stage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (scene.isNext()) {
					scene.showNextScene();
					stage = scene.getSceneUi();
				} else {

					// NOT JUST VILLAGESCREEN BUT PREVIOUS SCREEN
					new ScreenController(ScreenEnum.VILLAGE);

				}
				return true;
			}
		});

	}

	@Override
	public void hide() {
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

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}
}
