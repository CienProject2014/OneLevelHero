package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.RewardManager;
import com.mygdx.model.EventPack;

public class EventScreen extends RootScreen {
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private EventInfo eventInfo;

	// Already libgdx using interface!
	private Input input = Gdx.input;

	private Stage eventStage;
	private Stage selectButtonStage;

	private InputMultiplexer multiplexer;

	@Override
	public void render(float delta) {
		super.render(delta);
		eventStage.draw();
		EventPack currentEventPack = eventInfo.getEventQueue().peek();
		if (currentEventPack.getEventType() == EventTypeEnum.SELECT_COMPONENT
				|| currentEventPack.getEventType() == EventTypeEnum.SELECT_EVENT) {
			selectButtonStage.draw();
		}
	}

	@Override
	public void show() {
		eventStage = eventManager.getEvent();
		EventPack currentEventPack = eventInfo.getEventQueue().peek();
		multiplexer = new InputMultiplexer();
		if (currentEventPack.getEventType() == EventTypeEnum.SELECT_COMPONENT
				|| currentEventPack.getEventType() == EventTypeEnum.SELECT_EVENT) {
			selectButtonStage = stageFactory.makeStage(StageEnum.SELECT_BUTTON);
			multiplexer.addProcessor(0, selectButtonStage);
			multiplexer.addProcessor(1, eventStage);
		} else {
			multiplexer.addProcessor(0, eventStage);
		}

		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);
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

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}
}
