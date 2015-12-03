package com.mygdx.screen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.enums.StageEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.model.event.EventScene;
import com.mygdx.model.event.NPC;

public class LogScreen extends BaseScreen {
	@Autowired
	protected EventManager eventManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private BattleManager battleManager;

	// Already libgdx using interface!
	private Input input = Gdx.input;
	private final String YONGSA_NAME = "yongsa";
	private Stage eventStage;
	private Stage selectEventStage;
	private List<EventScene> logScenes;

	@Override
	public void render(float delta) {
		super.render(delta);

		eventStage.draw();
		if (!battleManager.isInBattle()) {
			selectEventStage.draw();
		}
	}

	@Override
	public void show() {
		if (battleManager.isInBattle()) {
			eventManager.setCurrentNpc(battleManager.getCurrentMonster().getFacePath());
		} else {
			positionManager.setCurrentEventPositionType(EventPosition.LOG);
			eventManager.setCurrentNpc(YONGSA_NAME);
			selectEventStage = stageFactory.makeStage(StageEnum.CHOICE_EVENT);
		}

		final NPC npc = eventManager.getCurrentNpc();
		// for shuffle
		List<EventScene> shuffleList = new ArrayList<EventScene>(logScenes);
		Collections.shuffle(shuffleList);
		eventStage = stageFactory.makeStage(StageEnum.CHAT_EVENT);

		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		if (!battleManager.isInBattle()) {
			multiplexer.addProcessor(i++, selectEventStage);
			multiplexer.addProcessor(i++, eventStage);
		} else {
			multiplexer.addProcessor(i++, eventStage);
		}

		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);

		eventStage.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				movingManager.goCurrentLocatePosition();
				return true;
			}
		});
	}
}
