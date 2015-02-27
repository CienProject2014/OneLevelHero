package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.CameraManager;
import com.mygdx.manager.CameraManager.CameraPosition;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.NpcManager;
import com.mygdx.manager.PlaceManager;
import com.mygdx.model.Building;
import com.mygdx.state.Assets;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.actor.CompositeItem;

public class BuildingStage extends Overlap2DStage {
	@Autowired
	private Assets assets;
	@Autowired
	private PositionInfo positionInfo;
	@Autowired
	private NpcManager npcManager;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private CameraManager cameraManager;
	private List<CompositeItem> npcButtonList;
	private Building buildingInfo;
	private TextButton exitButton;

	public Stage makeStage() {
		makeScene();
		setNpcList();
		setExitButton();
		return this;
	}

	private void setExitButton() {
		exitButton = new TextButton("나가기", assets.skin);
		exitButton.center();
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.VILLAGE);
				event.getListenerActor().setVisible(false);
			}
		});

		addActor(exitButton);

	}

	private void makeScene() {
		buildingInfo = assets.villageMap.get(positionInfo.getCurrentNode())
				.getBuilding().get(positionInfo.getCurrentBuilding());
		initSceneLoader();
		sceneLoader.loadScene(buildingInfo.getSceneName());
		cameraManager.setCameraSize(this, CameraPosition.BELOW_GAME_UI);
		addActor(sceneLoader.getRoot());
	}

	private void setNpcList() {
		npcButtonList = new ArrayList<CompositeItem>();
		for (final String npcName : buildingInfo.getBuildingNpc()) {
			CompositeItem npcButton = sceneLoader.getRoot().getCompositeById(
					npcName);
			npcButton.setTouchable(Touchable.enabled);
			npcButton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {

					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					eventManager.setEventInfo(assets.npcMap.get(npcName), true);
					screenFactory.show(ScreenEnum.GREETING);
				}

			});
			npcButtonList.add(npcButton);
		}
	}

	public PositionInfo getPositionInfo() {
		return positionInfo;
	}

	public void setPositionInfo(PositionInfo positionInfo) {
		this.positionInfo = positionInfo;
	}

	public NpcManager getNpcManager() {
		return npcManager;
	}

	public void setNpcManager(NpcManager npcManager) {
		this.npcManager = npcManager;
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public ScreenFactory getScreenFactory() {
		return screenFactory;
	}

	public void setScreenFactory(ScreenFactory screenFactory) {
		this.screenFactory = screenFactory;
	}

	public PlaceManager getPlaceManager() {
		return placeManager;
	}

	public void setPlaceManager(PlaceManager placeManager) {
		this.placeManager = placeManager;
	}

	public Building getBuildingInfo() {
		return buildingInfo;
	}

	public void setBuildingInfo(Building buildingInfo) {
		this.buildingInfo = buildingInfo;
	}

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public CameraManager getCameraManager() {
		return cameraManager;
	}

	public void setCameraManager(CameraManager cameraManager) {
		this.cameraManager = cameraManager;
	}

}
