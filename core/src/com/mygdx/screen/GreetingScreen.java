package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;

public class GreetingScreen extends BaseScreen {
	@Autowired
	protected EventManager eventManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private PositionManager positionManager;

	// Already libgdx using interface!
	private Input input = Gdx.input;

	private Stage greetingStage;
	private Stage choiceEventStage;

	public GreetingScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		greetingStage.draw();
		choiceEventStage.draw();
	}

	@Override
	public void show() {
		greetingStage = stageFactory.makeStage(StageEnum.GREETING);
		choiceEventStage = stageFactory.makeStage(StageEnum.CHOICE_NPCE_EVENT);
		// for shuffle

		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(i++, choiceEventStage);
		multiplexer.addProcessor(i++, greetingStage);

		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);

		greetingStage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				positionManager.setCurrentEventPositionType(EventPosition.NONE);
				movingManager.goCurrentLocatePosition();
				return true;
			}
		});
	}
}
