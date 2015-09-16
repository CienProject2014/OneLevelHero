package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.VillageDirectionEnum;
import com.mygdx.stage.BaseOneLevelStage;
import com.mygdx.stage.BaseOverlapStage;

public class CameraManager {
	private OrthographicCamera cam;
	private int moveFlag;
	private int direction;
	private ShakingCamera shakingCam;
	static final float BASE_CAMERA_POSITION_X = StaticAssets.BASE_WINDOW_WIDTH / 2f;
	static final float BASE_CAMERA_POSITION_Y = StaticAssets.BASE_WINDOW_HEIGHT / 2f;

	public enum CameraStateEnum {
		MOVE_LEFT, MOVE_RIGHT, MOVE_UP, MOVE_DOWN, STATIC;
	}

	public enum CameraPosition {
		ABOVE_GAME_UI, BELOW_GAME_UI;
	}

	public CameraManager() {
		cam = new OrthographicCamera(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);
		cam.position.set(BASE_CAMERA_POSITION_X, BASE_CAMERA_POSITION_Y, 0);
		shakingCam = new ShakingCamera(this);	
	}

	public void stretchToDevice(BaseOverlapStage stage) {
		stage.getOrthographicCamera().position.set(StaticAssets.BASE_WINDOW_WIDTH / 2f,
				StaticAssets.BASE_WINDOW_HEIGHT / 2f, 0);
		stage.getViewport().setCamera(stage.getOrthographicCamera());
	}

	public void stretchToDevice(BaseOneLevelStage stage) {
		stage.getViewport().setCamera(cam);
	}
	
	public void shaking(){
		shakingCam.startShaking();
	}
	
	public void act(float delta){
		//ShakingCam.act(delta, cam);
		shakingCam.act(delta);
	}

	public void stretchToDevice(BaseOverlapStage stage, VillageDirectionEnum villageDirectionEnum) {
		switch (villageDirectionEnum) {
			case LEFT :
				stage.getOrthographicCamera().position.set(StaticAssets.BASE_WINDOW_WIDTH / 2f,
						StaticAssets.BASE_WINDOW_HEIGHT / 2f, 0);
				stage.getViewport().setCamera(stage.getOrthographicCamera());
				break;
			case RIGHT :
				stage.getOrthographicCamera().position.set(StaticAssets.BASE_WINDOW_WIDTH * 3f / 2f,
						StaticAssets.BASE_WINDOW_HEIGHT / 2f, 0);
				stage.getViewport().setCamera(stage.getOrthographicCamera());
				break;
			case UP :
				stage.getOrthographicCamera().position.set(StaticAssets.BASE_WINDOW_WIDTH / 2f,
						StaticAssets.BASE_WINDOW_HEIGHT * 3f / 2f, 0);
				stage.getViewport().setCamera(stage.getOrthographicCamera());
				break;
			case DOWN :
				stage.getOrthographicCamera().position.set(StaticAssets.BASE_WINDOW_WIDTH / 2f,
						StaticAssets.BASE_WINDOW_HEIGHT / 2f, 0);
				stage.getViewport().setCamera(stage.getOrthographicCamera());
				break;
			default :
				Gdx.app.log("CameraManager", "VillageDirection 정보 오류");
				break;
		}
	}

	private void restrictCameraDelta(Vector3 cameraPosition, int deltaX, int deltaY) {

		if (cameraPosition.y > BASE_CAMERA_POSITION_Y + deltaY - BaseOverlapStage.MOVING_SPEED && direction == 1) {
			cameraPosition.y = BASE_CAMERA_POSITION_Y + deltaY;
			if (cameraPosition.y == 1620) {
				setMoveFlag(0);
			}
		} else if (cameraPosition.y < BASE_CAMERA_POSITION_Y + BaseOverlapStage.MOVING_SPEED && direction == 1) {
			cameraPosition.y = BASE_CAMERA_POSITION_Y;
			if (cameraPosition.y == 540) {
				setMoveFlag(1);
			}
		}

		if (cameraPosition.x > BASE_CAMERA_POSITION_X + deltaX - BaseOverlapStage.MOVING_SPEED && direction == 2) {
			cameraPosition.x = BASE_CAMERA_POSITION_X + deltaX;
			if (cameraPosition.x == 2880) {
				setMoveFlag(3);
			}
			// 오른쪽에서 멈춤
		} else if (cameraPosition.x < BASE_CAMERA_POSITION_X + BaseOverlapStage.MOVING_SPEED && direction == 2) {
			cameraPosition.x = BASE_CAMERA_POSITION_X;
			if (cameraPosition.x == 960) {
				setMoveFlag(4);
			}
			// 왼쪽에서 멈춤
		}
	}

	public void moveCamera(BaseOverlapStage stage) {
		switch (stage.getCameraState()) {
			case MOVE_UP :
				Vector3 delta = new Vector3(0, BaseOverlapStage.MOVING_SPEED, 0);
				stage.getCamera().translate(delta);
				break;
			case MOVE_DOWN :
				Vector3 delta1 = new Vector3(0, -BaseOverlapStage.MOVING_SPEED, 0);
				stage.getCamera().translate(delta1);
				break;
			case MOVE_LEFT :
				Vector3 delta2 = new Vector3(-BaseOverlapStage.MOVING_SPEED, 0, 0);
				stage.getCamera().translate(delta2);
				break;
			case MOVE_RIGHT :
				Vector3 delta3 = new Vector3(BaseOverlapStage.MOVING_SPEED, 0, 0);
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
	
	public OrthographicCamera getCamera(){
		return cam;
	}
}

class ShakingCamera{
	private boolean shaking = false;
	private float shakingTimer = 0;
	private float localx = 0;
	private float localy = 0;
	private float shakingTime = 0.5f;
	private float shakingPower = 1;
	
	private CameraManager manager;
	
	public ShakingCamera(CameraManager cam){
		setCameraManager(cam);
	}
	
	public void setCameraManager(CameraManager cam){
		manager = cam;
	}
	
	public void startShaking(){
		shaking = true;
		shakingTimer = 0;
		translateCam(10, 0);
	}
	
	public void act(float delta){
		if(shaking){
			shakingTimer += delta;
			if(shakingTimer < shakingTime){
				stayShaking(shakingTimer);
			}else{
				endShaking();
			}
		}
	}
	
	public void setShakingPower(float power){
		if(power<0){
			shakingPower = -power;
		}else{
			shakingPower = power;
		}
		
	}
	
	public void setShakingTime(float second){
		shakingTime = second;
	}
	
	public float getShakingPower(){
		return shakingPower;
	}
	
	public float getShakingTime(){
		return shakingTime;
	}
	
	public boolean isShaking(){
		return shaking;
	}
	
	private void stayShaking(float timer){
		float x = ((((timer/0.03f)%2)*40) - 20) * shakingPower;
		float y = (((((timer+0.015f)/0.03f)%2)*40) - 20) * shakingPower;
		if((x<0 && 0<manager.getCamera().position.x) || (0<x && manager.getCamera().position.x<0)){
			initCamPosition();
			translateCam(x, 0);
		}
		if((y<0 && 0<manager.getCamera().position.y) || (0<y && manager.getCamera().position.y<0)){
			initCamPosition();
			translateCam(0, y);
		}
	}
	
	private void endShaking(){
		initCamPosition();
		shaking = false;
	}
	
	private void translateCam(float x, float y){
		localx += x;
		localy += y;
		manager.getCamera().translate(x, y);
	}
	
	private void initCamPosition(){
		translateCam(-localx, -localy);
	}
}
