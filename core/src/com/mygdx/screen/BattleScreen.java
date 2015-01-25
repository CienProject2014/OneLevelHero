package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.manager.PlatformResourceManager;
import com.mygdx.stage.BattleStage;
import com.mygdx.stage.CharacterUiStage;
import com.mygdx.stage.GameUiStage;
import com.mygdx.stage.MonsterStage;

@Component
@Scope(value = "prototype")
public class BattleScreen implements Screen {
	@Autowired
	private StageFactory stageFactory;
	@Autowired
	private GameUiStage uiStage;
	@Autowired
	private CharacterUiStage characStage;
	@Autowired
	private MonsterStage monsterStage; // monster Table 을 포함하는 Stage
	@Autowired
	private BattleStage battleStage; // battle ui Table 을 포함하는 Stage

	public BattleScreen() {

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		monsterStage.act(); // 몬스터 스테이지에 움직이는 요소가 있을 경우
							// 예를 들어, 움직이는 몬스터
		monsterStage.draw();

		battleStage.act(); // 버튼 애니메이션을 위함
		battleStage.draw();

		// 유저의 스테이터스를 실시간으로 업데이트 한다.
		characStage.act(delta);
		characStage.draw();

		uiStage.act();
		uiStage.draw();

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		uiStage = new GameUiStage();
		characStage = new CharacterUiStage();
		monsterStage = (MonsterStage) stageFactory.makeStage(StageEnum.MONSTER);
		PlatformResourceManager rm = new PlatformResourceManager();
		rm.initPlatformerResources();
		battleStage = (BattleStage) stageFactory.makeBattleStage(rm);

		setInputProcessor();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		// CharacterUiStage 를 Static 하게 사용하면 addProcessor도 한 번만 하면 되지 않을까?
		multiplexer.addProcessor(0, uiStage);
		multiplexer.addProcessor(1, characStage);
		multiplexer.addProcessor(2, monsterStage);
		multiplexer.addProcessor(3, battleStage);
		Gdx.input.setInputProcessor(multiplexer);
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
