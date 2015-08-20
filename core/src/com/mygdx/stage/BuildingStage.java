package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.GameObjectEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.TextureManager;
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

		buildingInfo = worldNodeAssets.getVillageByName(positionManager.getCurrentNodeName()).getBuilding()
				.get(positionManager.getCurrentSubNodeName());
		if (buildingInfo.isOverlapScene()) {
			makeScene();
			makeBuildingSceneByOverlap();
			setSceneNpcList();
			setSceneItemList();
		} else {
			makeBuildingScene();
			setNpcList();
			setItemList();
		}
		cameraManager.stretchToDevice(this);
		return this;
	}

	private void makeScene() {
		buildingInfo = worldNodeAssets.getVillageByName(positionManager.getCurrentNodeName()).getBuilding()
				.get(positionManager.getCurrentSubNodeName());
		if (!StaticAssets.rm.searchSceneNames(buildingInfo.getSceneName())) {
			StaticAssets.rm.initScene(buildingInfo.getSceneName());
		}
		initSceneLoader(StaticAssets.rm);
	}

	private void makeBuildingScene() {
		Table backgroundTable = new Table();
		backgroundTable.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		backgroundTable.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		TextureRegionDrawable backgroundImage = new TextureRegionDrawable(
				new TextureRegion(TextureManager.getBackgroundTexture(buildingInfo.getBuildingPath())));
		backgroundTable.setBackground(backgroundImage);
		addActor(backgroundTable);
	}

	private void setItemList() {
		// TODO Auto-generated method stub
	}

	private void setNpcList() {
		// TODO Auto-generated method stub
	}

	private void makeBuildingSceneByOverlap() {
		sceneLoader.loadScene(buildingInfo.getSceneName());
		addActor(sceneLoader.getRoot());

	}

	private void setSceneNpcList() {
		npcButtonList = new ArrayList<CompositeItem>();
		if (buildingInfo.getBuildingNpc() != null) {
			for (final String npcName : buildingInfo.getBuildingNpc()) {
				CompositeItem npcButton = sceneLoader.getRoot().getCompositeById(npcName);
				npcButton.setTouchable(Touchable.enabled);
				npcButton.addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						eventManager.setCurrentEventNpc(npcName);
						eventManager.setCurrentEventNumber(2); // FIXME
						screenFactory.show(ScreenEnum.GREETING);
					}
				});
				npcButtonList.add(npcButton);
			}
		}
	}

	private void setSceneItemList() {
		gameObjectList = new ArrayList<CompositeItem>();

		if (buildingInfo.getGameObject() != null)

		{
			for (final String objectName : buildingInfo.getGameObject()) {
				final GameObject gameObject = eventAssets.getGameObject(objectName);
				final CompositeItem gameObjectButton = sceneLoader.getRoot().getCompositeById(objectName);
				gameObjectButton.setVisible(true);
				setGameObjectVisibility(gameObjectButton, gameObject.getObjectType());
				setGameObjectFunction(gameObjectButton, objectName);
				gameObjectButton.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						eventManager.setCurrentGameObject(gameObject);
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
	}

	private void setGameObjectFunction(CompositeItem gameObjectButton, String objectName) {
		if (objectName.equals("save")) {
			LabelItem labelItem = sceneLoader.getRoot().getLabelById("save_label");
			labelItem.setText("저장하기");
			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setTouchable(Touchable.disabled);
		} else if (objectName.equals("rest")) {
			LabelItem labelItem = sceneLoader.getRoot().getLabelById("rest_label");
			labelItem.setText("휴식하기");
			labelItem.setStyle(new LabelStyle(uiComponentAssets.getFont(), Color.WHITE));
			labelItem.setFontScale(1.0f);
			labelItem.setTouchable(Touchable.disabled);
		}
	}

	private void setGameObjectVisibility(CompositeItem objectButton, GameObjectEnum gameObjectEnum) {
		switch (gameObjectEnum) {

		case NORMAL:
			objectButton.setLayerVisibilty(GameObjectEnum.PRESSED.toString(), false);
			objectButton.setLayerVisibilty(GameObjectEnum.NORMAL.toString(), true);
			objectButton.setTouchable(Touchable.enabled);
			break;
		case PRESSED:
			objectButton.setLayerVisibilty(GameObjectEnum.PRESSED.toString(), true);
			objectButton.setLayerVisibilty(GameObjectEnum.NORMAL.toString(), false);
			objectButton.setTouchable(Touchable.disabled);
			break;
		case FUNCTION:
			break;
		default:
			Gdx.app.log("BuildingStage", "NULL GameObjectEnum Type");
			break;
		}
	}
}
