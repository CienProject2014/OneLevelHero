package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.RewardManager;

public class EventScreen extends BaseScreen {
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private EventManager eventManager;

	// Already libgdx using interface!
	private Input input = Gdx.input;
	private Stage eventStage;
	private Stage selectStage;
	private InputMultiplexer multiplexer;

	@Override
	public void render(float delta) {
		super.render(delta);
		eventStage.draw();
		Table.drawDebug(eventStage);
		drawSelectStage();
	}

	@Override
	public void show() {
		eventStage = eventManager.getElementEvent();
		setMultiprocessor();
	}

	private void drawSelectStage() {
		switch (eventManager.getCurrentElementEvent().getEventType()) {
			case SELECT_COMPONENT :
				selectStage.draw();
				break;
			case SELECT_EVENT :
				selectStage.draw();
				break;
			default :
				break;
		}
	}

	private void setMultiprocessor() {
		multiplexer = new InputMultiplexer();
		int i = 0;
		switch (eventManager.getCurrentElementEvent().getEventType()) {
			case SELECT_COMPONENT :
				selectStage = stageFactory.makeStage(StageEnum.SELECT_COMPONENT);
				multiplexer.addProcessor(i++, selectStage);
				multiplexer.addProcessor(i++, eventStage);
				break;
			default :
				multiplexer.addProcessor(i++, eventStage);
				break;
		}
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);
	}
}
