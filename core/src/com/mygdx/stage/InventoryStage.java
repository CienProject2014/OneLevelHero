package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.StorySectionManager;

public class InventoryStage extends BaseOverlapStage {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventCheckManager eventCheckManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;

	private Camera cam;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("EventStage");

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("inventory_scene");
		addActor(sceneLoader.getRoot());
		setCamera();
		setLabel();

		return this;
	}

	private void setLabel() {
	}

	private void setCamera() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH,
				StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
		getViewport().setCamera(cam);
	}
}
