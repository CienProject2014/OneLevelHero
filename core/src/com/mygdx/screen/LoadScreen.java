package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.enums.StageEnum;
import com.mygdx.stage.LoadStage;

public class LoadScreen extends BaseScreen {
	@Autowired
	protected CurrentInfo currentInfo;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	private Stage stage, loadStage;
	private TextButton startButton;
	private TextButton backButton;

	@Override
	public void render(float delta) {
		super.render(delta);
		setInputProcessor();
		stage.draw();
		if (LoadStage.isTouched) {
			loadStage.draw();
		}
	}

	@Override
	public void show() {
		stage = new Stage();
		Table table = new Table(uiComponentAssets.getSkin());
		backButton = new TextButton("Back", uiComponentAssets.getSkin());
		startButton = new TextButton("Start", uiComponentAssets.getSkin());
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				screenFactory.show(ScreenEnum.MENU);
			}
		});
		startButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				/*
				 * currentInfo.setSaveVersion(SaveVersion.NEW);
				 * eventManager.setCurrentEventNpc(PROLOGUE);
				 * loadManager.loadNewGame();
				 */
				LoadStage.isTouched = true;
			}
		});

		table.setFillParent(true);
		table.add(startButton).expand();
		table.row();
		table.add(backButton).bottom();
		stage.addActor(table);
		loadStage = stageFactory.makeStage(StageEnum.LOAD);
		setInputProcessor();

	}

	private void setInputProcessor() {
		InputMultiplexer multiplexer = new InputMultiplexer();
		if (LoadStage.isTouched) {
			multiplexer.addProcessor(0, loadStage);
			multiplexer.addProcessor(1, stage);
		} else {
			multiplexer.addProcessor(0, stage);
			multiplexer.addProcessor(1, loadStage);
		}
		Gdx.input.setInputProcessor(multiplexer);

	}

}
