package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class DungeonEntranceStage extends Overlap2DStage {
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private ScreenFactory screenFactory;
	private CompositeItem entranceButton, saveButton, restButton,
			worldMapButton;

	public Stage makeStage() {
		makeScene();
		setButton();
		return this;
	}

	private void makeScene() {
		initSceneLoader();
		sceneLoader.loadScene("blackwood_forest_scene");
		addActor(sceneLoader.getRoot());
	}

	private void setButton() {
		entranceButton = sceneLoader.getRoot().getCompositeById(
				"entrance_button");
		saveButton = sceneLoader.getRoot().getCompositeById("save_button");
		restButton = sceneLoader.getRoot().getCompositeById("rest_button");
		worldMapButton = sceneLoader.getRoot().getCompositeById(
				"worldmap_button");

		entranceButton.setTouchable(Touchable.enabled);
		entranceButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("DungeonEntranceStage", "던전으로 들어가자!");
			}
		});

		saveButton.setTouchable(Touchable.enabled);
		saveButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("DungeonEntranceStage", "게임이 저장되었다...");
			}
		});

		restButton.setTouchable(Touchable.enabled);
		restButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("DungeonEntranceStage", "잘 쉬었도다...");
			}
		});

		worldMapButton.setTouchable(Touchable.enabled);
		worldMapButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.WORLD_MAP);
			}
		});
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

}
