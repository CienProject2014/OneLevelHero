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
import com.mygdx.manager.LoadManager;
import com.mygdx.stage.LoadStage;

public class LoadScreen extends BaseScreen {
	@Autowired
	protected CurrentInfo currentInfo;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private LoadManager loadManager;
	private Stage stage, loadStage;
	private TextButton loadButton;
	private TextButton newStartButton;
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
		loadButton = new TextButton("Load", uiComponentAssets.getSkin());
		newStartButton = new TextButton("NewStart", uiComponentAssets.getSkin());
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
		loadButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				LoadStage.isTouched = true;
			}
		});
		newStartButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				eventManager.setCurrentEventNpc("prologue");
				loadManager.loadNewGame();
			}
		});

		table.setFillParent(true);
		table.add(loadButton).expand();
		table.row();
		table.add(newStartButton).expand();
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
