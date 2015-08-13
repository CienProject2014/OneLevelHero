package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class GameObjectScreen extends BaseScreen {

	private Input input = Gdx.input;

	private Stage gameObjectStage;

	@Override
	public void render(float delta) {
		super.render(delta);

		gameObjectStage.draw();
	}

	@Override
	public void show() {
		gameObjectStage = stageFactory.makeStage(StageEnum.GAME_OBJECT);
		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.
		multiplexer.addProcessor(0, gameObjectStage);

		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		input.setInputProcessor(multiplexer);
	}
}
