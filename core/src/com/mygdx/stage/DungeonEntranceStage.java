package com.mygdx.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.enums.ScreenEnum;
<<<<<<< HEAD
import com.mygdx.listener.TouchListener;
import com.mygdx.listener.TouchListenerWithLog;
import com.mygdx.manager.CameraManager.CameraPosition;
=======
>>>>>>> Developer
import com.mygdx.state.StaticAssets;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonEntranceStage extends BaseOverlapStage {
	private CompositeItem entranceButton, saveButton, restButton,
			worldMapButton;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);

		makeScene();
		setButton();

		return this;
	}

	private void makeScene() {
		// 우선은 blackwood_forest_entrance_scene으로 통일하자
		sceneLoader.loadScene("blackwood_forest_entrance_scene");
		// cameraManager.setCameraSize(this, CameraPosition.BELOW_GAME_UI);
		addActor(sceneLoader.getRoot());
	}

	private void setButton() {
		entranceButton = sceneLoader.getRoot().getCompositeById(
				"entrance_button");
		saveButton = sceneLoader.getRoot().getCompositeById("save_button");
		restButton = sceneLoader.getRoot().getCompositeById("rest_button");
		worldMapButton = sceneLoader.getRoot().getCompositeById(
				"worldmap_button");

		sceneLoader.getRoot().getLabelById("entrance_label")
				.setTouchable(Touchable.disabled);

		entranceButton.addListener(new TouchListenerWithLog(
				"DungeonEntranceStage", "던전으로 들어가자!", new Runnable() {
					@Override
					public void run() {
						screenFactory.show(ScreenEnum.DUNGEON);
					}
				}));

		saveButton.addListener(new TouchListenerWithLog("DungeonEntranceStage",
				"게임이 저장되었다...", new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
					}
				}));

		restButton.addListener(new TouchListenerWithLog("DungeonEntranceStage",
				"잘 쉬었도다...", new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
					}
				}));

		worldMapButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.WORLD_MAP);
			}
		}));
	}
}
