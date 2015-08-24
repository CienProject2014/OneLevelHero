package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;
import com.mygdx.stage.LoadPopupStage;

public class LoadScreen extends BaseScreen {
	protected static boolean isTouched;
	private Stage loadStage, loadPopupStage;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		loadStage.draw();
		if (LoadScreen.isTouched) {
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
		if (LoadPopupStage.isTouched) {
			multiplexer.addProcessor(0, loadPopupStage);
			multiplexer.addProcessor(1, loadStage);
		} else {
			multiplexer.addProcessor(0, loadStage);
			multiplexer.addProcessor(1, loadPopupStage);
		}
		Gdx.input.setInputProcessor(multiplexer);

	}

}
