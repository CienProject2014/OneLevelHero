package com.mygdx.screen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.model.event.EventScene;

public class ChoiceOptionScreen extends BaseScreen {
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private EventManager eventManager;

	// Already libgdx using interface!
	private Input input = Gdx.input;
	private Stage chatEventStage;
	private Stage choiceOptionStage;
	private InputMultiplexer multiplexer;

	@Override
	public void render(float delta) {
		super.render(delta);
		chatEventStage.draw();
		choiceOptionStage.draw();;
	}

	@Override
	public void show() {
		chatEventStage = stageFactory.makeStage(StageEnum.CHAT_EVENT);
		choiceOptionStage = stageFactory.makeStage(StageEnum.CHOICE_OPTION);
		setMultiprocessor();
	}

	public void show(EventScene eventScene) {
		this.show();
	}

	public void show(EventScene eventScene, List<String> eventList) {
		this.show();
	}

	private void setMultiprocessor() {
		multiplexer = new InputMultiplexer();
		int i = 0;

		multiplexer.addProcessor(i++, choiceOptionStage);
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);
	}
}
