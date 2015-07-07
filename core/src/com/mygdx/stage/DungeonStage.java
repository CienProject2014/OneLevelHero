package com.mygdx.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.listener.TouchListenerWithLog;
import com.mygdx.manager.CameraManager.CameraPosition;
import com.mygdx.state.StaticAssets;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonStage extends OverlapStage {
	private CompositeItem arrowUp, arrowDown, arrowLeft, arrowRight;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);

		makeScene();
		setButton();

		return this;
	}

	private void makeScene() {
		// 우선은 blackwood_forest_dungeon_scene으로 통일하자
		sceneLoader.loadScene("blackwood_forest_dungeon_scene");
		cameraManager.setCameraSize(this, CameraPosition.BELOW_GAME_UI);
		addActor(sceneLoader.getRoot());
	}

	private void setButton() {
		arrowUp = sceneLoader.getRoot().getCompositeById("arrow_up");
		arrowDown = sceneLoader.getRoot().getCompositeById("arrow_down");
		arrowLeft = sceneLoader.getRoot().getCompositeById("arrow_left");
		arrowRight = sceneLoader.getRoot().getCompositeById("arrow_right");

		arrowUp.addListener(new TouchListenerWithLog("DungeonState", "앞으로 이동",
				new Runnable() {
					@Override
					public void run() {
						// TODO
					}
				}));

		arrowDown.addListener(new TouchListenerWithLog("DungeonState", "뒤로 이동",
				new Runnable() {
					@Override
					public void run() {
						// TODO
					}
				}));

		arrowLeft.addListener(new TouchListenerWithLog("DungeonState",
				"왼쪽으로 이동", new Runnable() {
					@Override
					public void run() {
						// TODO
					}
				}));

		arrowRight.addListener(new TouchListenerWithLog("DungeonState",
				"오른쪽으로 이동", new Runnable() {
					@Override
					public void run() {
						// TODO
					}
				}));
	}
}
