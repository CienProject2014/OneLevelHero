package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.StaticAssets;
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.enums.SaveVersion;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.SaveManager;
import com.mygdx.screen.BuildingScreen;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class SaveStage extends BaseOverlapStage {
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private SaveManager saveManager;
	@Autowired
	private CurrentInfo currentInfo;

	private static final String SCENE_NAME = "save_scene";
	private Camera cam;
	private CompositeItem closeButton;
	private CompositeItem background;
	private CompositeItem save01;
	private CompositeItem save02;
	private CompositeItem save03;
	private CompositeItem newbutton;

	public Stage makeStage() {
		assetsManager.initScene(SCENE_NAME);
		initSceneLoader(assetsManager.rm);
		sceneLoader.loadScene(SCENE_NAME);
		addActor(sceneLoader.getRoot());
		setCamera();
		setCompositeItem();
		setAddListener();
		return this;
	}

	private void setCompositeItem() {
		background = sceneLoader.getRoot().getCompositeById("background");
		save01 = sceneLoader.getRoot().getCompositeById("save01");
		save02 = sceneLoader.getRoot().getCompositeById("save02");
		save03 = sceneLoader.getRoot().getCompositeById("save03");
		closeButton = sceneLoader.getRoot().getCompositeById("close_button");
		newbutton = sceneLoader.getRoot().getCompositeById("new_button");
	}

	private void setAddListener() {
		background.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BuildingScreen.isInSave = false;
			}
		});
		save01.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				currentInfo.setSaveVersion(SaveVersion.SAVE1);
				saveManager.save();
				BuildingScreen.isInSave = false;
			}
		});
		save02.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				currentInfo.setSaveVersion(SaveVersion.SAVE2);
				saveManager.save();
				BuildingScreen.isInSave = false;
			}
		});
		save03.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				currentInfo.setSaveVersion(SaveVersion.SAVE3);
				saveManager.save();
				BuildingScreen.isInSave = false;
			}
		});
		closeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BuildingScreen.isInSave = false;
			}
		});

		newbutton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				BuildingScreen.isInSave = false;
				saveManager.setNewGame();
			}
		});
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}
