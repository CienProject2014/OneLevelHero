package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.VillageDirectionEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.ArrowButtonListener;
import com.mygdx.listener.BuildingButtonListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.CameraManager.CameraStateEnum;
import com.mygdx.manager.FieldManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.surroundings.Building;
import com.mygdx.model.surroundings.NodeConnection;
import com.mygdx.model.surroundings.Village;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class VillageStage extends BaseOverlapStage {
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private PositionManager positionManager;
	private Village villageInfo;
	public TextButton shiftButton;

	public Stage makeStage() {
		setVillage();
		cameraManager.stretchToDevice(this, positionManager.getVillageDirection());
		/* setVillage(); */
		return this;
	}

	private void setArrow() {
		List<CompositeItem> arrowList = new ArrayList<CompositeItem>();
		String currentNode = positionManager.getCurrentNodeName();
		Map<String, NodeConnection> connectionMap = worldMapAssets.getWorldNodeInfo(currentNode).getNodeConnection();
		for (final Entry<String, NodeConnection> connection : connectionMap.entrySet()) {
			final CompositeItem arrow = sceneLoader.getRoot().getCompositeById(connection.getValue().getArrowName());
			if (arrow != null) {
				arrow.setVisible(true);
				arrow.setTouchable(Touchable.enabled);
				ArrowButtonListener arrowButtonListener = listenerFactory.getArrowButtonListener();
				arrowButtonListener.setConnection(connection);
				arrow.addListener(arrowButtonListener);
				arrowList.add(arrow);
			}
		}
	}

	private void setVillageScene(PositionManager positionManager, NodeAssets nodeAssets) {
		if (nodeAssets.getVillageByName(positionManager.getCurrentNodeName()) != null) {
			villageInfo = nodeAssets.getVillageByName(positionManager.getCurrentNodeName());
			assetsManager.initScene(villageInfo.getSceneName());
			initSceneLoader(assetsManager.rm);
			sceneLoader.loadScene(villageInfo.getSceneName());
		} else {
			villageInfo = nodeAssets.getVillageByName("blackwood");
			assetsManager.initScene(villageInfo.getSceneName());
			initSceneLoader(assetsManager.rm);
			sceneLoader.loadScene(villageInfo.getSceneName());
		}
	}

	// 마을 정보에 맞게 스테이지 형성
	private void setVillage() {
		Gdx.app.log("VillageStage", String.valueOf(positionManager.getCurrentNodeName()));
		setVillageScene(positionManager, nodeAssets);
		setArrow();
		if (positionManager.getBeforePositionType().equals(PositionEnum.FIELD)) {
			setArrowDirection();
		}
		setBuildingButton();
		addActor(sceneLoader.getRoot());
		VillageDirectionEnum villageDirection = villageInfo.getVillageDirection();
		switch (villageDirection) {
			case UP_DOWN :
				final CompositeItem shiftbutton_up = sceneLoader.getRoot().getCompositeById("camera_up");
				final CompositeItem shiftbutton_down = sceneLoader.getRoot().getCompositeById("camera_down");

				shiftbutton_up.setTouchable(Touchable.enabled);
				shiftbutton_down.setTouchable(Touchable.enabled);

				shiftbutton_up.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						positionManager.setVillageDirection(VillageDirectionEnum.UP);
						setCameraState(CameraStateEnum.MOVE_UP);
						timeManager.plusMinute(15);
						cameraManager.setMoveFlag(2);
					}
				});

				shiftbutton_down.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						positionManager.setVillageDirection(VillageDirectionEnum.DOWN);
						setCameraState(CameraStateEnum.MOVE_DOWN);
						timeManager.plusMinute(15);
						cameraManager.setMoveFlag(2);
					}
				});
				cameraManager.setDirection(1);

				break;
			case LEFT_RIGHT :
				final CompositeItem shiftbutton_left = sceneLoader.getRoot().getCompositeById("camera_left");
				final CompositeItem shiftbutton_right = sceneLoader.getRoot().getCompositeById("camera_right");

				shiftbutton_left.setTouchable(Touchable.enabled);
				shiftbutton_right.setTouchable(Touchable.enabled);

				shiftbutton_left.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						positionManager.setVillageDirection(VillageDirectionEnum.LEFT);
						setCameraState(CameraStateEnum.MOVE_LEFT);
						cameraManager.setMoveFlag(5);
						timeManager.plusMinute(15);

					}
				});

				shiftbutton_right.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						positionManager.setVillageDirection(VillageDirectionEnum.RIGHT);
						setCameraState(CameraStateEnum.MOVE_RIGHT);
						cameraManager.setMoveFlag(5);
						timeManager.plusMinute(15);

					}
				});
				cameraManager.setDirection(2);

				break;
			case CENTER :
				break;
			default :
				Gdx.app.log("VillageStage", "VillageDirectionEnum정보 오류");
		}
		setNullCase(villageInfo);
	}
	private void setNullCase(Village villageInfo) {
		if (positionManager.getVillageDirection() == null) {
			if (villageInfo.getVillageDirection().equals(VillageDirectionEnum.UP_DOWN)) {
				positionManager.setVillageDirection(VillageDirectionEnum.DOWN);
			} else if (villageInfo.getVillageDirection().equals(VillageDirectionEnum.LEFT_RIGHT)) {
				positionManager.setVillageDirection(VillageDirectionEnum.LEFT);
			} else {
				positionManager.setVillageDirection(VillageDirectionEnum.CENTER);
			}
		}
	}

	private void setArrowDirection() {
		for (Entry<String, VillageDirectionEnum> arrowInfo : villageInfo.getArrowDirection().entrySet()) {
			if (arrowInfo.getKey().equals(fieldManager.getArrowName())) {
				positionManager.setVillageDirection(arrowInfo.getValue());
			}
		}
	}

	private void controlButton() {
		if (cameraManager.getMoveFlag() == 0) {
			sceneLoader.getRoot().getCompositeById("camera_down").setVisible(true);
			sceneLoader.getRoot().getCompositeById("camera_up").setVisible(false);
		} else if (cameraManager.getMoveFlag() == 1) {
			sceneLoader.getRoot().getCompositeById("camera_down").setVisible(false);
			sceneLoader.getRoot().getCompositeById("camera_up").setVisible(true);
		} else if (cameraManager.getMoveFlag() == 2) {
			sceneLoader.getRoot().getCompositeById("camera_down").setVisible(false);
			sceneLoader.getRoot().getCompositeById("camera_up").setVisible(false);

		} else if (cameraManager.getMoveFlag() == 3) {
			sceneLoader.getRoot().getCompositeById("camera_left").setVisible(true);
			sceneLoader.getRoot().getCompositeById("camera_right").setVisible(false);
		} else if (cameraManager.getMoveFlag() == 4) {
			sceneLoader.getRoot().getCompositeById("camera_left").setVisible(false);
			sceneLoader.getRoot().getCompositeById("camera_right").setVisible(true);
		} else if (cameraManager.getMoveFlag() == 5) {
			sceneLoader.getRoot().getCompositeById("camera_left").setVisible(false);
			sceneLoader.getRoot().getCompositeById("camera_right").setVisible(false);
		}
	}

	@Override
	public void act() {
		super.act();
		if (villageInfo.getVillageDirection() != VillageDirectionEnum.CENTER) {
			controlButton();
		}
	}

	private void setBuildingButton() {
		if (villageInfo.getBuilding() != null) {
			for (final Entry<String, Building> building : villageInfo.getBuilding().entrySet()) {
				CompositeItem buildingButton = sceneLoader.getRoot().getCompositeById(building.getKey());
				buildingButton.setTouchable(Touchable.enabled);
				BuildingButtonListener buildingButtonListener = listenerFactory.getBuildingButtonListener();
				buildingButtonListener.setBuildingName(building.getKey());
				buildingButtonListener.setBuildingInfo(building.getValue());
				buildingButton.addListener(buildingButtonListener);
			}
		}
	}
}
