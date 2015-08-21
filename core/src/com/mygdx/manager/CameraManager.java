package com.mygdx.manager;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.assets.StaticAssets;
import com.mygdx.stage.BaseOneLevelStage;
import com.mygdx.stage.BaseOverlapStage;

public class CameraManager {
	private OrthographicCamera cam;
	private int moveFlag;
	private int direction;
	static final float BASE_CAMERA_POSITION_X = StaticAssets.BASE_WINDOW_WIDTH
			/ 2f;
	static final float BASE_CAMERA_POSITION_Y = StaticAssets.BASE_WINDOW_HEIGHT
			/ 2f;

	public enum CameraStateEnum {
		MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN, STATIC;
	}

	public enum CameraPosition {
		ABOVE_GAME_UI, BELOW_GAME_UI;
	}

	public CameraManager() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH,
				StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(BASE_CAMERA_POSITION_X, BASE_CAMERA_POSITION_Y, 0);
	}

	public void stretchToDevice(BaseOverlapStage stage) {
		stage.getOrthographicCamera().position.set(
				StaticAssets.BASE_WINDOW_WIDTH / 2f,
				StaticAssets.BASE_WINDOW_HEIGHT / 2f, 0);
		stage.getViewport().setCamera(stage.getOrthographicCamera());
	}

	public void stretchToDevice(BaseOneLevelStage stage) {
		stage.getViewport().setCamera(cam);
	}

	private void restrictCameraDelta(Vector3 cameraPosition, int deltaX,
			int deltaY) {

		if (cameraPosition.y > BASE_CAMERA_POSITION_Y + deltaY
				- BaseOverlapStage.MOVING_SPEED && direction == 1) {
			cameraPosition.y = BASE_CAMERA_POSITION_Y + deltaY;
			if (cameraPosition.y == 1620) {
				setMoveFlag(0);
			}
			// System.out.println(cameraPosition.y);
			// 위쪽버튼
			System.out.println(moveFlag);
		} else
			if (cameraPosition.y < BASE_CAMERA_POSITION_Y
					+ BaseOverlapStage.MOVING_SPEED && direction == 1) {
			cameraPosition.y = BASE_CAMERA_POSITION_Y;
			if (cameraPosition.y == 540) {
				setMoveFlag(1);
			}
			// System.out.println(moveFlag);// 아래쪽버튼
		}

		if (cameraPosition.x > BASE_CAMERA_POSITION_X + deltaX
				- BaseOverlapStage.MOVING_SPEED && direction == 2) {
			cameraPosition.x = BASE_CAMERA_POSITION_X + deltaX;
			if (cameraPosition.x == 2880) {
				setMoveFlag(3);
			}
			// 오른쪽에서 멈춤
		} else
			if (cameraPosition.x < BASE_CAMERA_POSITION_X
					+ BaseOverlapStage.MOVING_SPEED && direction == 2) {
			cameraPosition.x = BASE_CAMERA_POSITION_X;
			if (cameraPosition.x == 960) {
				setMoveFlag(4);
			}
			// 왼쪽에서 멈춤
		}
		// System.out.println(cameraPosition.x);
		// System.out.println(moveFlag);// 아래쪽버튼
	}

	public void moveCamera(BaseOverlapStage stage) {
		switch (stage.getCameraState()) {
			case MOVE_UP :
				Vector3 delta = new Vector3(0, BaseOverlapStage.MOVING_SPEED,
						0);
				stage.getCamera().translate(delta);
				break;
			case MOVE_DOWN :
				Vector3 delta1 = new Vector3(0, -BaseOverlapStage.MOVING_SPEED,
						0);
				stage.getCamera().translate(delta1);
				break;
			case MOVE_LEFT :
				Vector3 delta2 = new Vector3(-BaseOverlapStage.MOVING_SPEED, 0,
						0);
				stage.getCamera().translate(delta2);
				break;
			case MOVE_RIGHT :
				Vector3 delta3 = new Vector3(BaseOverlapStage.MOVING_SPEED, 0,
						0);
				stage.getCamera().translate(delta3);
				break;
			default :
				break;
		}
		restrictCameraDelta(stage.getCamera().position, 1920, 1080);
	}

	public int getDirection() {
		return direction;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public int getMoveFlag() {
		return moveFlag;
	}

	public void setMoveFlag(int moveFlag) {
		this.moveFlag = moveFlag;
	}
}
