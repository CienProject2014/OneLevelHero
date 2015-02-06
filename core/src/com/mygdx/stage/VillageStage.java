package com.mygdx.stage;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.PlaceEnum;
import com.mygdx.enums.ScreenEnum;
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
	@Autowired
	private PositionInfo positionInfo;

	private Village villageInfo;
	public TextButton shiftButton;
	private float viewportWidth;
	private float viewportHeight;
	private Camera camera;
	private BackgroundDirection backgroundDirection;
	private OrthographicCamera cam;

	public enum BackgroundDirection {
		UP, DOWN, MOVE_UP, MOVE_DOWN;
	}

	public Stage makeStage() {
		setVillage();
		return this;
	}

	// 마을 정보에 맞게 스테이지 형성
	private void setVillage() {
		cam = new OrthographicCamera(assets.windowWidth, assets.windowHeight);
		cam.position
				.set(assets.windowWidth / 2, assets.windowHeight * 0.25f, 0);

		getViewport().setCamera(cam);
		camera = getViewport().getCamera();

		initSceneLoader();
		villageInfo = assets.villageMap.get(positionInfo.getCurrentNode());
		sceneLoader.loadScene(villageInfo.getSceneName());

		// 아직까진 블랙 우드밖에 없으므로 직접 B를 넣어주자

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
		float ratio = 0.782f; //FIXME
		viewportWidth = assets.windowWidth;
		viewportHeight = viewportWidth * ratio;

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
		for (final Entry<String, Building> building : villageInfo.getBuilding()
				.entrySet()) {
			CompositeItem buildingButton = sceneLoader.getRoot()
					.getCompositeById(building.getValue().getBuildingPath());
			buildingButton.setTouchable(Touchable.enabled);
			buildingButton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					positionInfo.setCurrentBuilding(building.getKey());
					positionInfo.setCurrentPlace(PlaceEnum.BUILDING);
					screenFactory.show(ScreenEnum.BUILDING);
				}
			});
		}
	}
}
