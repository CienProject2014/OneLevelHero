package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class LoadScreen extends BaseScreen {
	public static boolean isPopupTouched;;
	private Stage loadStage, loadPopupStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		loadStage.draw();
		if (isPopupTouched) {
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
		if (isPopupTouched) {
			multiplexer.addProcessor(0, loadPopupStage);
			multiplexer.addProcessor(1, loadStage);
		} else {
			multiplexer.addProcessor(0, loadStage);
			multiplexer.addProcessor(1, loadPopupStage);
		}
		Gdx.input.setInputProcessor(multiplexer);
	}
}
