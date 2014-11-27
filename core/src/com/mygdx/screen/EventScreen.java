package com.mygdx.screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.CurrentManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.RewardInfo;
import com.mygdx.model.EventInfo;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;
import com.mygdx.model.Reward;
import com.mygdx.stage.SelectButtonStage;

public class EventScreen implements Screen {

	// It needs interface layer, for test!
	private EventManager eventManager = EventManager.getInstance();
	private StageFactory stageFactory = StageFactory.getInstance();
	private RewardInfo rewardInfo = CurrentManager.getInstance()
			.getRewardInfo();

	// Already libgdx using interface!
	private GL20 gl = Gdx.gl;
	private Input input = Gdx.input;

	private Stage eventStage;
	private Stage selectButtonStage;
	private EventInfo eventInfo;

	private List<EventScene> greetingScenes;
	private Iterator<EventScene> iterator;

	public EventScreen() {

	}

	private Iterator<EventScene> getEventSceneIterator(NPC npc, int eventNo) {

		return npc.getEvent(eventNo).getEventSceneIterator();

	}

	@Override
	public void render(float delta) {
		gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		eventStage.draw();
		if (eventInfo.isGreeting())
			selectButtonStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		selectButtonStage = new SelectButtonStage();
		eventInfo = eventManager.getEventInfo();

		NPC npc = eventInfo.getNpc();

		iterator = getEventSceneIterator(npc, eventInfo.getEventNumber());

		//RewardState를 ING로 변경
		/*
		if (!eventInfo.isGreeting())
			rewardInfo.getEventRewardQueue().offer(
					eventInfo.getNpc().getEvent(eventInfo.getEventNumber())
							.getReward());
			*/

		if (eventInfo.isGreeting()) {
			greetingScenes = npc.getGreeting().getEventScenes();
		} else {
			//리워드를 eventRewardQueue에 추가
			Reward reward = npc.getEvent(eventInfo.getEventNumber())
					.getReward();
			if (reward != null)
				if (reward.getRewardState() == RewardStateEnum.NOT_CLEARED)
					rewardInfo.addEventReward(reward);
		}

		if (eventInfo.isGreeting()) {
			// for shuffle
			List<EventScene> shuffleList = new ArrayList<EventScene>(
					greetingScenes);
			Collections.shuffle(shuffleList);
			eventStage = stageFactory.makeStage(shuffleList.get(0));
		} else {
			eventStage = stageFactory.makeStage(iterator.next());
		}

		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.
		if (eventInfo.isGreeting()) {
			multiplexer.addProcessor(0, selectButtonStage);
			multiplexer.addProcessor(1, eventStage);
		} else {
			multiplexer.addProcessor(0, eventStage);
		}
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);

		eventStage.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (eventInfo.isGreeting()) {
					new ScreenController(ScreenEnum.VILLAGE);

				} else {
					if (iterator.hasNext()) {
						eventStage = stageFactory.makeStage(iterator.next());
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
