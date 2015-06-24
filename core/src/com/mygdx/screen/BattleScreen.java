package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class BattleScreen extends RootScreen {
	private Stage gameUiStage, characterUiStage, monsterStage, battleStage;

	public BattleScreen() {}

	@Override
	public void render(float delta) {
		super.render(delta);

		monsterStage.act(); // 몬스터 스테이지에 움직이는 요소가 있을 경우
							// 예를 들어, 움직이는 몬스터
		monsterStage.draw();

		// 유저의 스테이터스를 실시간으로 업데이트 한다.
		characterUiStage.act(delta);
		characterUiStage.draw();

		gameUiStage.act();
		gameUiStage.draw();

		battleStage.act(); // 버튼 애니메이션을 위함
		battleStage.draw();
	}

	@Override
	public void resize(int width, int height) {}

	@Override
	public void show() {
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		characterUiStage = stageFactory.makeStage(StageEnum.CHARACTER_UI);
		monsterStage = stageFactory.makeStage(StageEnum.MONSTER);
		battleStage = stageFactory.makeBattleStage();

		setInputProcessor();
		musicManager.setBattleMusicAndPlay();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		// CharacterUiStage 를 Static 하게 사용하면 addProcessor도 한 번만 하면 되지 않을까?
		multiplexer.addProcessor(0, gameUiStage);
		multiplexer.addProcessor(1, characterUiStage);
		multiplexer.addProcessor(2, monsterStage);
		multiplexer.addProcessor(3, battleStage);
		Gdx.input.setInputProcessor(multiplexer);
	}
}
