package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.assets.WorldNodeAssets;
import com.mygdx.enums.PlaceEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.CameraManager.CameraStateEnum;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.WorldMapManager;
import com.mygdx.model.Building;
import com.mygdx.model.Connection;
import com.mygdx.model.Village;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class VillageStage extends BaseOverlapStage {
	@Autowired
	private WorldNodeAssets worldNodeAssets;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private WorldMapManager worldMapManager;
	private Village villageInfo;
	public TextButton shiftButton;
	private final int movingSpeed = 10;

	public Stage makeStage() {

		initSceneLoader(StaticAssets.rm);

		cameraManager.stretchToDevice(this);
		setVillage();

		return this;
	}

	private void setArrow() {
		List<CompositeItem> arrowList = new ArrayList<CompositeItem>();
		String currentNode = positionManager.getCurrentNode();
		Map<String, Connection> connectionMap = worldMapAssets
				.getWorldNodeInfo(currentNode).getConnection();
		for (final Entry<String, Connection> connection : connectionMap
				.entrySet()) {
			final CompositeItem arrow = sceneLoader.getRoot().getCompositeById(
					connection.getValue().getArrowName());
			if (arrow != null) {
				arrow.setVisible(true);
				arrow.setTouchable(Touchable.enabled);
				arrow.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						worldMapManager.selectDestinationNode(connection
								.getKey());
						screenFactory.show(ScreenEnum.MOVING);
					}
				});
				arrowList.add(arrow);
			}
		}
	}

	// 마을 정보에 맞게 스테이지 형성
	private void setVillage() {
		Gdx.app.debug("VillageStage",
				String.valueOf(positionManager.getCurrentNode()));
		villageInfo = worldNodeAssets.getVillage("blackwood");
		sceneLoader.loadScene("blackwood_scene");
		setArrow();
		setBuildingButton();
		addActor(sceneLoader.getRoot());
		final CompositeItem shiftbutton_up = sceneLoader.getRoot()
				.getCompositeById("camera_up");
		final CompositeItem shiftbutton_down = sceneLoader.getRoot()
				.getCompositeById("camera_down");
		shiftbutton_up.setTouchable(Touchable.enabled);
		shiftbutton_down.setTouchable(Touchable.enabled);
		shiftbutton_up.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				setCameraState(CameraStateEnum.MOVE_UP);
			}
		});

		shiftbutton_down.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				setCameraState(CameraStateEnum.MOVE_DOWN);
			}

		});

	}

	@Override
	public void act() {
		super.act();
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
					positionManager.setCurrentBuilding(building.getKey());
					positionManager.setCurrentPlace(PlaceEnum.BUILDING);
					screenFactory.show(ScreenEnum.BUILDING);
				}
			});
		}
	}
}
