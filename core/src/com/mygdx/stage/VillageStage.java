package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.model.Building;
import com.mygdx.model.Village;
import com.mygdx.state.Assets;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

@Component
@Scope(value = "prototype")
public class VillageStage extends Overlap2DStage {
	@Autowired
	private Assets assets;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private EventManager eventManager;

	private Village villageInfo;
	public TextButton shiftButton;
	private float viewportWidth;
	private float viewportHeight;
	private Camera camera;
	private BackgroundDirection backgroundDirection;
	private OrthographicCamera cam;
	private List<CompositeItem> buildingButtonList;

	public enum BackgroundDirection {
		UP, DOWN, MOVE_UP, MOVE_DOWN;
	}

	public Stage makeStage() {
		setVillage();
		return this;
	}

	// 마을 정보에 맞게 스테이지 형성
	private void setVillage() {
		Gdx.app.debug("VillageStage", "assets.windowWidth : "
				+ assets.windowWidth + " assets.windowHeight : "
				+ assets.windowHeight);
		cam = new OrthographicCamera(assets.windowWidth, assets.windowHeight);
		cam.position
				.set(assets.windowWidth / 2, assets.windowHeight * 0.25f, 0);
		Gdx.app.debug("VillageStage", "cam.position : " + assets.windowWidth
				/ 2 + ", " + assets.windowHeight * 0.25f);

		getViewport().setCamera(cam);
		camera = getViewport().getCamera();

		float ratio = 0.782f; //FIXME
		viewportWidth = assets.windowWidth;
		viewportHeight = viewportWidth * ratio;
		Gdx.app.debug("VillageStage", "viewportWidth : " + viewportWidth
				+ ", viewportHeight : " + viewportHeight);

		initSceneLoader();
		sceneLoader.loadScene("VillageScene");

		// 아직까진 블랙 우드밖에 없으므로 직접 B를 넣어주자
		villageInfo = assets.villageMap.get(WorldNodeEnum.BLACKWOOD.toString());

		backgroundDirection = BackgroundDirection.DOWN;

		addActor(sceneLoader.getRoot());
		setBuildingButton();
		shiftButton = new TextButton("전환", assets.skin);
		shiftButton.center();

		shiftButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				if (backgroundDirection.equals(BackgroundDirection.DOWN)) {
					backgroundDirection = BackgroundDirection.MOVE_UP;
				} else if (backgroundDirection.equals(BackgroundDirection.UP)) {
					backgroundDirection = BackgroundDirection.MOVE_DOWN;
				}

				event.getListenerActor().setVisible(false);
			}
		});

		addActor(shiftButton);
	}

	@Override
	public void draw() {
		moveCam();
		super.draw();
	}

	private void moveCam() {
		int movingSpeed = 6;
		checkCameraPosition();
		if (backgroundDirection.equals(BackgroundDirection.MOVE_UP)) {
			camera.translate(0, movingSpeed, 0);
			shiftButton.moveBy(0, movingSpeed);
		} else if (backgroundDirection.equals(BackgroundDirection.MOVE_DOWN)) {
			camera.translate(0, -movingSpeed, 0);
			shiftButton.moveBy(0, -movingSpeed);
		} else {

		}
	}

	private void checkCameraPosition() {
		if (camera.position.y > (viewportHeight - assets.windowHeight / 2)) {
			camera.position.y = viewportHeight - assets.windowHeight / 2;
			backgroundDirection = BackgroundDirection.UP;

			shiftButton.setVisible(true);

		} else if (camera.position.y < assets.windowHeight * 0.25f) {
			camera.position.y = assets.windowHeight * 0.25f;
			backgroundDirection = BackgroundDirection.DOWN;

			shiftButton.setVisible(true);
		}
	}

	private void setBuildingButton() {
		buildingButtonList = new ArrayList<>();
		for (Entry<String, Building> building : villageInfo.getBuilding()
				.entrySet()) {
			CompositeItem buildingButton = sceneLoader.getRoot()
					.getCompositeById(building.getValue().getBuildingPath());
			buildingButton.setTouchable(Touchable.enabled);
			buildingButtonList.add(buildingButton);
		}
	}
}
