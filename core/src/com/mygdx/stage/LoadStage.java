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
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.LoadManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.SaveManager;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.ImageItem;

public class LoadStage extends BaseOverlapStage {
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private SaveManager saveManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private LoadManager loadManager;
	@Autowired
	private CurrentInfo currentInfo;
	@Autowired
	private MovingManager movingManager;
	public static boolean isTouched = false;
	private static final String SCENE_NAME = "save_scene";
	private Camera cam;
	private ImageItem background;
	private ImageItem save;
	private CompositeItem newload;
	private CompositeItem closeButton;

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
		background = sceneLoader.getRoot().getImageById("background");
		save = sceneLoader.getRoot().getImageById("save");
		newload = sceneLoader.getRoot().getCompositeById("new_button");
		closeButton = sceneLoader.getRoot().getCompositeById("close_button");
	}

	private void setAddListener() {
		background.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				isTouched = false;
			}
		});
		save.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				currentInfo.setSaveVersion(SaveVersion.SAVE1);
				saveManager.load();
				isTouched = false;
				WorldNodeEnum.NodeType nodeType = positionManager.getCurrentNodeType();
				movingManager.goCurrentNode(nodeType);
			}
		});
		newload.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				eventManager.setCurrentEventNpc("prologue");
				loadManager.loadNewGame();
			}
		});
		closeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				isTouched = false;
			}
		});
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}
