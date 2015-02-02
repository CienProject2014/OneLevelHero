package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.model.Village;
import com.mygdx.state.Assets;
import com.uwsoft.editor.renderer.Overlap2DStage;

@Component
@Scope(value = "prototype")
public class VillageStage extends Overlap2DStage {
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

	public enum BackgroundDirection {
		UP, DOWN, MOVE_UP, MOVE_DOWN;
	}

	public Stage makeStage() {
		setVillage();
		return this;
	}

	// 마을 정보에 맞게 스테이지 형성
	private void setVillage() {
		initSceneLoader();
		sceneLoader.loadScene("VillageScene");

		// 아직까진 블랙 우드밖에 없으므로 직접 B를 넣어주자
		villageInfo = Assets.villageMap.get(WorldNodeEnum.BLACKWOOD.toString());
		backgroundDirection = BackgroundDirection.UP;

		float ratio = 0.782f; //FIXME
		viewportWidth = Assets.windowWidth;
		viewportHeight = viewportWidth * ratio;
		OrthographicCamera cam = new OrthographicCamera(Assets.windowWidth,
				Assets.windowHeight);
		cam.position
				.set(Assets.windowWidth / 2, Assets.windowHeight * 0.25f, 0);

		addActor(sceneLoader.getRoot());
		getViewport().setCamera(cam);
		camera = getViewport().getCamera();
		// 전환 버튼 기능은 빌리지 스크린에서 구현
		//shiftButton.center();
		/*
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
		*/
	}

	@Override
	public void draw() {
		moveCam();
		super.draw();
	}

	private void moveCam() {
		int movingSpeed = 6;
		checkBound();
		if (backgroundDirection.equals(BackgroundDirection.MOVE_UP)) {
			camera.translate(0, movingSpeed, 0);
			//shiftButton.moveBy(0, movingSpeed);

		} else if (backgroundDirection.equals(BackgroundDirection.MOVE_DOWN)) {
			camera.translate(0, -movingSpeed, 0);
			//shiftButton.moveBy(0, -movingSpeed);
		} else {

		}
	}

	private void checkBound() {
		if (camera.position.y > (viewportHeight - Assets.windowHeight / 2)) {
			camera.position.y = viewportHeight - Assets.windowHeight / 2;
			backgroundDirection = BackgroundDirection.UP;
			//shiftButton = new TextButton("아래로", Assets.skin);
			//shiftButton.setVisible(true);

		} else if (camera.position.y < Assets.windowHeight * 0.25f) {
			camera.position.y = Assets.windowHeight * 0.25f;
			backgroundDirection = BackgroundDirection.DOWN;
			//shiftButton = new TextButton("위로", Assets.skin);
			//shiftButton.setVisible(true);
		}
	}
}
