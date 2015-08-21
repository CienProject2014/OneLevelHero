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
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.enums.VillageDirectionEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.ArrowButtonListener;
import com.mygdx.listener.BuildingButtonListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.CameraManager.CameraStateEnum;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.model.surroundings.Building;
import com.mygdx.model.surroundings.NodeConnection;
import com.mygdx.model.surroundings.Village;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class VillageStage extends BaseOverlapStage {
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private AssetsManager assetsManager;
	private Village villageInfo;
	public TextButton shiftButton;

	public Stage makeStage() {
		setVillage();
		cameraManager.stretchToDevice(this);
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
		setBuildingButton();
		addActor(sceneLoader.getRoot());
		VillageDirectionEnum villageDirection = villageInfo.getVillageDirection();
		switch (villageDirection) {
		case UP_DOWN:
			final CompositeItem shiftbutton_up = sceneLoader.getRoot().getCompositeById("camera_up");
			final CompositeItem shiftbutton_down = sceneLoader.getRoot().getCompositeById("camera_down");

			shiftbutton_up.setTouchable(Touchable.enabled);
			shiftbutton_down.setTouchable(Touchable.enabled);

			shiftbutton_up.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					setCameraState(CameraStateEnum.MOVE_UP);

					cameraManager.setMoveFlag(2);
				}
			});

			shiftbutton_down.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					setCameraState(CameraStateEnum.MOVE_DOWN);

					cameraManager.setMoveFlag(2);
				}
			});
			cameraManager.setDirection(1);

			break;
		case LEFT_RIGHT:
			final CompositeItem shiftbutton_left = sceneLoader.getRoot().getCompositeById("camera_left");
			final CompositeItem shiftbutton_right = sceneLoader.getRoot().getCompositeById("camera_right");

			shiftbutton_left.setTouchable(Touchable.enabled);
			shiftbutton_right.setTouchable(Touchable.enabled);

			shiftbutton_left.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {

					setCameraState(CameraStateEnum.MOVE_LEFT);
					cameraManager.setMoveFlag(5);
				}
			});

			shiftbutton_right.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					setCameraState(CameraStateEnum.MOVE_RIGHT);
					cameraManager.setMoveFlag(5);
				}
			});
			cameraManager.setDirection(2);

			break;
		case CENTER:
			break;
		default:
			Gdx.app.log("VillageStage", "VillageDirectionEnum정보 오류");
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
				CompositeItem buildingButton = sceneLoader.getRoot()
						.getCompositeById(building.getValue().getBuildingPath());
				buildingButton.setTouchable(Touchable.enabled);
				BuildingButtonListener buildingButtonListener = listenerFactory.getBuildingButtonListener();
				buildingButtonListener.setBuildingName(building.getKey());
				buildingButton.addListener(buildingButtonListener);
			}
		}
	}
}
