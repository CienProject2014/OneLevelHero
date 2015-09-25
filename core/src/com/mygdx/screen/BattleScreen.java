package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class BattleScreen extends BaseScreen {
	private Stage gameUiStage, characterUiStage, monsterStage, battleStage, skillStage, itemStage;
	public static boolean showSkillStage = false;
	public static boolean showItemStage = false;

	public BattleScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		monsterStage.draw();
		characterUiStage.draw();
		battleStage.draw();
		gameUiStage.draw();

		if (showSkillStage) {
			skillStage.draw();
		}
		if (showItemStage) {
			itemStage.draw();
		}
		monsterStage.act(delta);
		characterUiStage.act(delta);
		gameUiStage.act();
		battleStage.act(delta);
		skillStage.act();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void show() {
		gameUiStage = stageFactory.makeStage(StageEnum.GAME_UI);
		characterUiStage = stageFactory.makeStage(StageEnum.CHARACTER_UI);
		monsterStage = stageFactory.makeStage(StageEnum.MONSTER);
		battleStage = stageFactory.makeStage(StageEnum.BATTLE);
		skillStage = stageFactory.makeStage(StageEnum.SKILL);
		loadPopupStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		itemStage = stageFactory.makeStage(StageEnum.ITEM);
		setInputProcessor();
		// musicManager.setBattleMusicAndPlay();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		if (showSkillStage) {
			multiplexer.addProcessor(i++, skillStage);
		} else if (showItemStage) {
			multiplexer.addProcessor(i++, itemStage);
		} else {
			multiplexer.addProcessor(i++, gameUiStage);
			multiplexer.addProcessor(i++, characterUiStage);
			multiplexer.addProcessor(i++, monsterStage);
			multiplexer.addProcessor(i++, battleStage);
			multiplexer.addProcessor(i++, skillStage);
		}
		if (showLoadStage) {
			multiplexer.addProcessor(0, loadPopupStage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
