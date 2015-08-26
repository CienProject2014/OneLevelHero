package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class LoadScreen extends BaseScreen {
	private Stage loadStage, loadPopupStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		loadStage.draw();
		if (showLoadStage) {
			loadPopupStage.draw();
		}
	}

	@Override
	public void show() {
		loadStage = stageFactory.makeStage(StageEnum.LOAD);
		loadPopupStage = stageFactory.makeStage(StageEnum.LOAD_POPUP);
		setInputProcessor();
	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		int i = 0;
		if (showLoadStage) {
			multiplexer.addProcessor(i++, loadPopupStage);
			multiplexer.addProcessor(i++, loadStage);
		} else {
			multiplexer.addProcessor(i++, loadStage);
			multiplexer.addProcessor(i++, loadPopupStage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
