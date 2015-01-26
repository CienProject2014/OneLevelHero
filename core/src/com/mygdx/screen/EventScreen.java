package com.mygdx.screen;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.model.EventScene;

@Component
@Scope(value = "prototype")
public class EventScreen implements Screen {
	@Autowired
	private StageFactory stageFactory;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private RewardManager rewardManager;

	// Already libgdx using interface!
	private GL20 gl = Gdx.gl;
	private Input input = Gdx.input;

	private Stage eventStage;

	private InputMultiplexer multiplexer;
	private Iterator<EventScene> iterator;

	public EventScreen() {

	}

	@Override
	public void render(float delta) {
		gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		eventStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		iterator = eventManager.getEventIterator();
		eventStage = stageFactory.makeEventStage(iterator.next());

		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, eventStage);

		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);

		eventStage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				if (iterator.hasNext()) {
					eventStage = stageFactory.makeEventStage(iterator.next());
				} else {
					rewardManager.doReward(); //보상이 있을경우 보상실행
					eventManager.endEvent(); //해당 이벤트 상태를 종료처리
					screenFactory.show(ScreenEnum.VILLAGE);
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
