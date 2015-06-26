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
import com.mygdx.manager.CameraManager;
import com.mygdx.manager.CameraManager.CameraPosition;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonEntranceStage extends OneLevel2DStage {
	@Autowired
	private PositionInfo positionInfo; //나중에 쓸거임 지우지 마셈
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private CameraManager cameraManager;
	private CompositeItem entranceButton, saveButton, restButton,
			worldMapButton;

	public Stage makeStage() {
		makeScene();
		setButton();
		return this;
	}

	private void makeScene() {
		initScene("blackwood_forest_entrance_scene");
		//우선은 blackwood_forest_entrance_scene으로 통일하자
		sceneLoader.loadScene("blackwood_forest_entrance_scene");
		cameraManager.setCameraSize(this, CameraPosition.BELOW_GAME_UI);
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
				Gdx.app.debug("DungeonEntranceStage", "던전으로 들어가자!");
				screenFactory.show(ScreenEnum.DUNGEON);
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
				Gdx.app.debug("DungeonEntranceStage", "게임이 저장되었다...");
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
				Gdx.app.debug("DungeonEntranceStage", "잘 쉬었도다...");
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

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public void setCameraManager(CameraManager cameraManager) {
		this.cameraManager = cameraManager;
	}

}
