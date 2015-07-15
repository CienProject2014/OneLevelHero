package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.controller.SaveVersion;
import com.mygdx.currentState.CurrentState;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.LoadManager;

public class LoadScreen extends BaseScreen {
	protected CurrentState currentState;
	private LoadManager loadManager;
	private UiComponentAssets uiComponentAssets;
	private EventAssets eventAssets;

	private Stage stage;
	private TextButton newstartButton;
	private TextButton backButton;
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
		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.MENU);
			}
		});
		newstartButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				currentState.setVersion(SaveVersion.NEW);
				loadManager.loadNewGame();
				eventManager.setCurrentEventNpc(PROLOGUE);
				screenFactory.show(ScreenEnum.EVENT);
			}
		});

		table.setFillParent(true);
		table.add(newstartButton).expand();
		table.row();
		table.add(backButton).bottom();
		stage.addActor(table);
		// stage.addActor(backButton);
	}

	public CurrentState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(CurrentState currentState) {
		this.currentState = currentState;
	}

	public LoadManager getLoadManager() {
		return loadManager;
	}

	public void setLoadManager(LoadManager loadManager) {
		this.loadManager = loadManager;
	}

	public EventAssets getEventAssets() {
		return eventAssets;
	}

	public void setEventAssets(EventAssets eventAssets) {
		this.eventAssets = eventAssets;
	}

	public UiComponentAssets getUiComponentAssets() {
		return uiComponentAssets;
	}

	public void setUiComponentAssets(UiComponentAssets uiComponentAssets) {
		this.uiComponentAssets = uiComponentAssets;
	}
}
