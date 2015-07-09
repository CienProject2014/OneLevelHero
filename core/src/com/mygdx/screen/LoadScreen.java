package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.currentState.CurrentState;
import com.mygdx.enums.SaveVersion;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventQueueManager;
import com.mygdx.manager.LoadManager;

public class LoadScreen extends BaseScreen {
	@Autowired
	protected CurrentState currentState;
	@Autowired
	private LoadManager loadManager;
	@Autowired
	private EventQueueManager eventQueueManager;
	private Stage stage;
	private TextButton newstartButton;
	private TextButton backButton;

	@Override
	public void render(float delta) {
		super.render(delta);
		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		Table table = new Table(assets.skin);
		backButton = new TextButton("Back", assets.skin);
		newstartButton = new TextButton("NewStart", assets.skin);
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
				eventQueueManager.runEventQueue();
				Gdx.app.log("LoadScreen", "runEventQueue");
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

	public EventQueueManager getEventQueueManager() {
		return eventQueueManager;
	}

	public void setEventQueueManager(EventQueueManager eventQueueManager) {
		this.eventQueueManager = eventQueueManager;
	}

}
