package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.GameObjectEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.surroundings.Building;
import com.mygdx.popup.GameObjectPopup;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class BuildingStage extends BaseOverlapStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private NodeAssets worldNodeAssets;
	@Autowired
	private EventAssets eventAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private ListenerFactory listenerFactory;

	private List<CompositeItem> npcButtonList;
	private List<CompositeItem> gameObjectList;
	private Building buildingInfo;
	private GameObjectPopup gameObjectPopup;

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		makeScene();

		if (buildingInfo.getBuildingNpc() != null) {
			setNpcList();
		}
		if (buildingInfo.getGameObject() != null) {
			setItemList();
		}

		return this;
	}

	private void makeScene() {
		buildingInfo = worldNodeAssets
				.getVillage(positionManager.getCurrentNodeName()).getBuilding()
				.get(positionManager.getCurrentSubNodeName());
		sceneLoader.loadScene(buildingInfo.getSceneName());
		cameraManager.stretchToDevice(this);
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
					eventManager.setCurrentEventNpc(npcName);
					eventManager.setCurrentEventNumber(2); // FIXME
					screenFactory.show(ScreenEnum.GREETING);
				}
			});
			npcButtonList.add(npcButton);
		}
	}

	private void setItemList() {
		gameObjectList = new ArrayList<CompositeItem>();
		for (final String objectName : buildingInfo.getGameObject()) {
			final GameObject gameObject = eventAssets.getGameObject(objectName);
			final CompositeItem gameObjectButton = sceneLoader.getRoot()
					.getCompositeById(objectName);
			gameObjectButton.setVisible(true);
			setGameObjectVisibility(gameObjectButton,
					gameObject.getObjectType());
			setGameObjectFunction(gameObjectButton, objectName);
			gameObjectButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					gameObjectPopup = new GameObjectPopup();
					gameObjectPopup.setAtlasUiAssets(atlasUiAssets);
					gameObjectPopup.setListenerFactory(listenerFactory);
					gameObjectPopup.setGameObject(gameObject);
					gameObjectPopup.initialize();
					addActor(gameObjectPopup);
					gameObjectPopup.setVisible(true);
				}
			});
			gameObjectList.add(gameObjectButton);
		}
	}
	private void setGameObjectFunction(CompositeItem gameObjectButton,
			String objectName) {
		if (objectName.equals("save")) {
			LabelItem labelItem = sceneLoader.getRoot().getLabelById(
					"save_label");
			labelItem.setText("저장하기");
			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(),
					Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setTouchable(Touchable.disabled);
		} else if (objectName.equals("rest")) {
			LabelItem labelItem = sceneLoader.getRoot().getLabelById(
					"rest_label");
			labelItem.setText("휴식하기");
			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(),
					Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setTouchable(Touchable.disabled);
		}

	}

	private void setGameObjectVisibility(CompositeItem objectButton,
			GameObjectEnum gameObjectEnum) {
		switch (gameObjectEnum) {
			case NORMAL :
				objectButton.setLayerVisibilty(
						GameObjectEnum.PRESSED.toString(), false);
				objectButton.setLayerVisibilty(
						GameObjectEnum.NORMAL.toString(), true);
				objectButton.setTouchable(Touchable.enabled);
				break;
			case PRESSED :
				objectButton.setLayerVisibilty(
						GameObjectEnum.PRESSED.toString(), true);
				objectButton.setLayerVisibilty(
						GameObjectEnum.NORMAL.toString(), false);
				objectButton.setTouchable(Touchable.disabled);
				break;
			default :
				Gdx.app.log("BuildingStage", "NULL GameObjectEnum Type");
				break;
		}
	}
}
