package com.mygdx.screen;

import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.Factory.StageFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.EventManager.EventInfo;
import com.mygdx.model.EventScene;
import com.mygdx.stage.SelectButtonStage;

public class EventScreen implements Screen {
	private Stage eventStage;
	private Stage selectButtonStage;
	private EventInfo eventInfo;

	public EventScreen() {
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		eventStage.draw();
		if (EventManager.getInstance().getEventInfo().isGreeting())
			selectButtonStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {

		selectButtonStage = new SelectButtonStage();
		eventInfo = EventManager.getInstance().getEventInfo();
		final Iterator<EventScene> iterator = eventInfo.getNpc().getEvent()
				.get(eventInfo.getEventNumber()).getEventScene().iterator();
		final List<EventScene> greetingScene = eventInfo.getNpc().getGreeting()
				.getEventScene();
		if (eventInfo.isGreeting()) {
			eventStage = StageFactory.getInstance().makeStage(
					greetingScene.get((int) (Math.random() * greetingScene
							.size())));

		} else {
			eventStage = StageFactory.getInstance().makeStage(iterator.next());
		}

		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.
		if (EventManager.getInstance().getEventInfo().isGreeting()){
		multiplexer.addProcessor(0, selectButtonStage);
		multiplexer.addProcessor(1, eventStage);
		}
		else{
			multiplexer.addProcessor(0, eventStage);
		}
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);

		eventStage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (eventInfo.isGreeting()) {
					new ScreenController(ScreenEnum.VILLAGE);

				} else {
					if (iterator.hasNext()) {
						eventStage = StageFactory.getInstance().makeStage(
								iterator.next());
					} else {
						new ScreenController(ScreenEnum.VILLAGE);
					}
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

}
