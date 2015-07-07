package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.SaveVersion;
import com.mygdx.currentState.CurrentState;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.TouchListener;
import com.mygdx.manager.LoadManager;

public class LoadScreen extends BaseScreen {
	@Autowired
	protected CurrentState currentState;
	@Autowired
	private LoadManager loadManager;

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
		backButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.MENU);
			}
		}));
		newstartButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				currentState.setVersion(SaveVersion.NEW);
				loadManager.loadNewGame();
				// 프롤로그 정보 주입
				eventManager.setEventInfo(assets.npcMap.get("prologue"), 0,
						false);
				screenFactory.show(ScreenEnum.EVENT);
			}
		}));

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
}
