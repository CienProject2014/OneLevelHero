package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.controller.SaveVersion;
import com.mygdx.currentState.CurrentState;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.LoadManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SaveManager;

public class LoadScreen extends BaseScreen {
	@Autowired
	protected CurrentState currentState;
	@Autowired
	private LoadManager loadManager;
	@Autowired
	private SaveManager saveManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private MovingManager movingManager;

	private Stage stage;
	private TextButton newstartButton;
	private TextButton backButton;
	private TextButton loadButton;
	private final String PROLOGUE = "prologue";

	@Override
	public void render(float delta) {
		super.render(delta);

		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Table table = new Table(uiComponentAssets.getSkin());
		backButton = new TextButton("Back", uiComponentAssets.getSkin());
		newstartButton = new TextButton("NewStart", uiComponentAssets.getSkin());
		loadButton = new TextButton("loadButton", uiComponentAssets.getSkin());
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
		newstartButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				currentState.setSaveVersion(SaveVersion.NEW);
				eventManager.setCurrentEventNpc(PROLOGUE);
				loadManager.loadNewGame();
			}
		});
		loadButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				saveManager.load();
				currentState.setSaveVersion(SaveVersion.SAVE1);
				movingManager.goCurrentPosition();
			}
		});

		table.setFillParent(true);
		table.add(newstartButton).expand();
		table.row();
		table.add(backButton).bottom();
		table.add(loadButton);
		stage.addActor(table);
		// stage.addActor(backButton);
	}
}
