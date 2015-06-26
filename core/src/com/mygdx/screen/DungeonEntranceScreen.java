package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;

/**
 * DungeonEntranceStage와 GameUiStage를 addActor()해서 보여주는 Screen. 던전입구의 경우
 * multiplexer를 이용하여 2개의 화면을 교차로 보여준다.
 * 
 * @author Velmont
 * 
 */
public class DungeonEntranceScreen implements Screen {
	@Autowired
	private StageFactory stageFactory;
	private Stage dungeonEntranceStage;
	private Stage gameUiStage;

	@Override
	public void render(float delta) {

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		dungeonEntranceStage.draw();
		dungeonEntranceStage.getCamera().update();
		gameUiStage.draw();
		// 카메라를 지속적으로 업데이트 해준다.

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		dungeonEntranceStage = stageFactory
				.makeStage(StageEnum.DUNGEON_ENTRANCE);
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);

		// 여러 스테이지에 인풋 프로세서를 동시에 할당한다
		InputMultiplexer multiplexer = new InputMultiplexer();
		// 만약 버튼이 겹칠 경우 인덱스가 먼저인 쪽(숫자가 작은 쪽)에 우선권이 간다 무조건 유아이가 위에 있어야 하므로 유아이에
		// 우선권을 준다.

		multiplexer.addProcessor(0, gameUiStage);
		multiplexer.addProcessor(1, dungeonEntranceStage);
		// 멀티 플렉서에 인풋 프로세서를 할당하게 되면 멀티 플렉서 안에 든 모든 스테이지의 인풋을 처리할 수 있다.
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void hide() {
		gameUiStage.dispose();
		dungeonEntranceStage.dispose();
		dispose();
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
	}

	public StageFactory getStageFactory() {
		return stageFactory;
	}

	public void setStageFactory(StageFactory stageFactory) {
		this.stageFactory = stageFactory;
	}

}
