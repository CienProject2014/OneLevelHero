package com.mygdx.screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;

@Component
@Scope(value = "prototype")
public class GreetingScreen implements Screen {
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private StageFactory stageFactory;
	@Autowired
	private EventManager eventManager;

	// Already libgdx using interface!
	private GL20 gl = Gdx.gl;
	private Input input = Gdx.input;

	private Stage eventStage;
	private Stage selectButtonStage;
	private EventInfo eventInfo;

	private List<EventScene> greetingScenes;

	public GreetingScreen() {
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

	public StageFactory getStageFactory() {
		return stageFactory;
	}

	public void setStageFactory(StageFactory stageFactory) {
		this.stageFactory = stageFactory;
	}

	@Override
	public void render(float delta) {
		gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		selectButtonStage.draw();
		eventStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		eventInfo = eventManager.getEventInfo();
		final NPC npc = eventInfo.getNpc();
		greetingScenes = npc.getGreeting().getEventScenes();

		selectButtonStage = stageFactory.makeStage(StageEnum.SELECT_BUTTON);
		// for shuffle
		List<EventScene> shuffleList = new ArrayList<EventScene>(greetingScenes);
		Collections.shuffle(shuffleList);
		eventStage = stageFactory.makeEventStage(shuffleList.get(0));

		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(0, selectButtonStage);
		multiplexer.addProcessor(1, eventStage);

		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);

		eventStage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				screenFactory.show(ScreenEnum.VILLAGE);

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
