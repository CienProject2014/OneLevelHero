package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventStageManager;
import com.mygdx.model.Event;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;
import com.mygdx.stage.SelectButtonStage;

public class EventScreen implements Screen {
	private Stage eventStage;
	private Stage buttonStage;
	private NPC npc;
	private int eventNumber;

	public EventScreen() {
	}

	public EventScreen(NPC npc, int eventNumber) {
		this.npc = npc;
		this.eventNumber = eventNumber;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		eventStage.draw();
		/*
		if (EventManager.getInstance().getEventType() == EventTypeEnum.SELECT) {
			buttonStage.draw();
		}
		*/

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {

		buttonStage = new SelectButtonStage();
		final Event events = npc.getEvent().get(eventNumber);
		final EventScene eventScene = events.getEventScene().iterator().next();
		eventStage = EventStageManager.getInstance().makeStage(eventScene,
				events.getEventType());

		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(0, buttonStage);
		multiplexer.addProcessor(1, eventStage);
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);

		eventStage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				while (events.getEventScene().iterator().hasNext()) {

					eventStage = EventStageManager.getInstance().makeStage(
							eventScene, events.getEventType());
				}
				new ScreenController(ScreenEnum.VILLAGE);

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
