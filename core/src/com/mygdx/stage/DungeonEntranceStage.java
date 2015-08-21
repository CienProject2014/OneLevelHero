package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonEntranceStage extends BaseOverlapStage {
	private CompositeItem entranceButton, saveButton, restButton;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		cameraManager.stretchToDevice(this);

		makeScene();
		setButton();

		return this;
	}

	private void makeScene() {
		// 우선은 blackwood_forest_entrance_scene으로 통일하자
		sceneLoader.loadScene("mawang_castle_scene");
		// cameraManager.setCameraSize(this, CameraPosition.BELOW_GAME_UI);
		addActor(sceneLoader.getRoot());
	}

	private void setButton() {
		entranceButton = sceneLoader.getRoot().getCompositeById("enter");
		saveButton = sceneLoader.getRoot().getCompositeById("save");
		restButton = sceneLoader.getRoot().getCompositeById("rest");
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
				Gdx.app.log("현재 위치", String.valueOf(positionManager.getCurrentNodeName()));
				screenFactory.show(ScreenEnum.DUNGEON);
			}
		});

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

	}
}
