package com.mygdx.screen;

import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.RewardManager;
import com.mygdx.model.EventScene;

public class EventScreen extends BaseScreen {
	@Autowired
	private RewardManager rewardManager;

	// Already libgdx using interface!
	private Input input = Gdx.input;

	private Stage eventStage;

	private InputMultiplexer multiplexer;
	private Iterator<EventScene> eventSceneIterator;

	@Override
	public void render(float delta) {
		super.render(delta);

		eventStage.draw();
	}

	@Override
	public void show() {
		eventSceneIterator = eventManager.getEventSceneIterator();
		eventStage = stageFactory.makeEventStage(eventSceneIterator.next());

		multiplexer = new InputMultiplexer();
		multiplexer.addProcessor(0, eventStage);

		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);

		eventStage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (eventSceneIterator.hasNext())
					eventStage = stageFactory.makeEventStage(eventSceneIterator
							.next());
				else {
					rewardManager.doReward(); // 보상이 있을경우 보상실행
					eventManager.finishEvent(); // 해당 이벤트 상태를 종료처리
					goPreviousPlace();
				}
				return true;
			}
		});
	}

	private void goPreviousPlace() {
		switch (positionInfo.getCurrentPlace()) {
			case BUILDING:
				screenFactory.show(ScreenEnum.BUILDING);
				break;
			case VILLAGE:
				screenFactory.show(ScreenEnum.VILLAGE);
				break;
			case DUNGEON:
				// screenFactory.show(ScreenEnum.DUNGEON);
				screenFactory.show(ScreenEnum.VILLAGE); // FIXME
				break;
			case FORK:
				// screenFactory.show(ScreenEnum.FORK);
				screenFactory.show(ScreenEnum.VILLAGE); // FIXME
				break;
			default:
				Gdx.app.log("EventScreen",
						"positionInfo.getCurrentPlace() is not valid");
				break;
		}
	}

	public RewardManager getRewardManager() {
		return rewardManager;
	}

	public void setRewardManager(RewardManager rewardManager) {
		this.rewardManager = rewardManager;
	}
}
