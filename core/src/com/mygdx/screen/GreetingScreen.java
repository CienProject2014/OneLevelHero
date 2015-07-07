package com.mygdx.screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.StageEnum;
import com.mygdx.listener.TouchListener;
import com.mygdx.model.EventScene;
import com.mygdx.model.NPC;

public class GreetingScreen extends BaseScreen {
	@Autowired
	protected EventInfo eventInfo;

	// Already libgdx using interface!
	private Input input = Gdx.input;

	private Stage eventStage;
	private Stage selectButtonStage;
	private List<EventScene> greetingScenes;

	public GreetingScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		eventStage.draw();
		selectButtonStage.draw();
	}

	@Override
	public void show() {
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

		eventStage.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				goPreviousPlace();
			}
		}));
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

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}
}
