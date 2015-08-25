package com.mygdx.screen;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.enums.StageEnum;

public class LoadingBarScreen extends BaseScreen {

	Stage loadingBarStage;

	@Override
	public void show() {
		loadingBarStage = stageFactory.makeStage(StageEnum.LOADING_BAR);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void render(float delta) {
		loadingBarStage.act();
		loadingBarStage.draw();
	}

	@Override
	public void hide() {
		// Dispose the loading assets as we no longer need them

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
		// TODO Auto-generated method stub
		loadingBarStage.dispose();
	}
}
