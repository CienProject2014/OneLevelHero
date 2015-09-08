package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.EventAssets;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.GameObjectEnum;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.AssetsManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.location.Building;
import com.mygdx.popup.GameObjectPopup;
import com.mygdx.screen.BuildingScreen;
import com.uwsoft.editor.renderer.actor.CompositeItem;
import com.uwsoft.editor.renderer.actor.LabelItem;

public class BuildingStage extends BaseOverlapStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private EventAssets eventAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private TimeManager timeManager;

	private List<CompositeItem> npcButtonList;
	private List<CompositeItem> gameObjectList;
	private Building buildingInfo;
	private GameObjectPopup gameObjectPopup;

	public Stage makeStage() {
		buildingInfo = nodeAssets.getVillageByName(positionManager.getCurrentNodeName()).getBuilding()
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
			setGameObject();
		}
		cameraManager.stretchToDevice(this);
		return this;
	}

	private void setGameObject() {
		Stack tableStack = new Stack();
		tableStack.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		tableStack.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		this.addActor(tableStack);
		cameraManager.stretchToDevice(this);
		Table buttonTable = new Table();
		buttonTable.setFillParent(true);
		buttonTable.right().bottom();
		buttonTable.padRight(StaticAssets.BASE_WINDOW_WIDTH * 0.14f).padBottom(StaticAssets.BASE_WINDOW_HEIGHT * 0.16f);
		buttonTable.row();
		tableStack.addActor(buttonTable);
		if (buildingInfo.getGameObject() != null) {
			for (final String objectName : buildingInfo.getGameObject()) {
				final GameObject gameObject = eventAssets.getGameObject(objectName);
				if (objectName.equals("save")) {
					ImageButton saveButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_save"));

					buttonTable.add(saveButton);
					buttonTable.row();
					buttonTable.row();

					saveButton.addListener(new SimpleTouchListener() {
						public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
							BuildingScreen.isInSave = true;
						}
					});
				} else if (objectName.equals("rest")) {
					ImageButton restButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(
							uiComponentAssets.getStayButton())));
					buttonTable.add(restButton);
					buttonTable.row();
					restButton.addListener(new SimpleTouchListener() {
						public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

							timeManager.plusMinute(5);
							eventManager.setCurrentGameObject(gameObject);
							gameObjectPopup = new GameObjectPopup();
							gameObjectPopup.setAtlasUiAssets(atlasUiAssets);
							gameObjectPopup.setListenerFactory(listenerFactory);
							gameObjectPopup.setGameObject(gameObject);
							gameObjectPopup.setConstantsAssets(constantsAssets);
							gameObjectPopup.initialize(objectName);
							addActor(gameObjectPopup);
							gameObjectPopup.setVisible(true);
						}
					});
				} else {
					ImageButton restsButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_go"));
					addActor(restsButton);
					restsButton.addListener(new SimpleTouchListener() {
						@Override
						public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
							timeManager.plusMinute(5);
							eventManager.setCurrentGameObject(gameObject);
							gameObjectPopup = new GameObjectPopup();
							gameObjectPopup.setAtlasUiAssets(atlasUiAssets);
							gameObjectPopup.setListenerFactory(listenerFactory);
							gameObjectPopup.setGameObject(gameObject);
							gameObjectPopup.setConstantsAssets(constantsAssets);
							gameObjectPopup.initialize(objectName);
							addActor(gameObjectPopup);
							gameObjectPopup.setVisible(true);
						}
					});
				}
			}

		}

	}
	private void makeScene() {
		buildingInfo = nodeAssets.getVillageByName(positionManager.getCurrentNodeName()).getBuilding()
				.get(positionManager.getCurrentSubNodeName());

		assetsManager.initScene(buildingInfo.getSceneName());
		initSceneLoader(assetsManager.rm);
	}

	private void makeBuildingScene() {
		Table backgroundTable = new Table();
		backgroundTable.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		backgroundTable.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		TextureRegionDrawable backgroundImage = new TextureRegionDrawable(new TextureRegion(
				textureManager.getBackgroundTexture(buildingInfo.getSubNodePath())));
		backgroundTable.setBackground(backgroundImage);
		addActor(backgroundTable);
	}
	private void setItemList() {
		// TODO Auto-generated method stub
	}

	private void setNpcList() {
		if (buildingInfo.getBuildingNpc() != null) {
			for (final String npcName : buildingInfo.getBuildingNpc()) {
				ImageButton npcButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(
						textureManager.getBustTexture(npcName, "01"))));
				npcButton.setTouchable(Touchable.enabled);
				npcButton.addListener(new SimpleTouchListener() {
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						timeManager.plusMinute(5);
						eventManager.setCurrentNpc(npcName);
						positionManager.setCurrentEventPositionType(EventPosition.GREETING);
						screenFactory.show(ScreenEnum.GREETING);
					}
				});
				addActor(npcButton);
			}
		}
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
				npcButton.addListener(new SimpleTouchListener() {
					@Override
					public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
						timeManager.plusMinute(5);
						eventManager.setCurrentNpc(npcName);
						positionManager.setCurrentEventPositionType(EventPosition.GREETING);
						screenFactory.show(ScreenEnum.GREETING);
					}
				});
				npcButtonList.add(npcButton);
			}
		}
	}

	private void setSceneItemList() {
		gameObjectList = new ArrayList<CompositeItem>();

		if (buildingInfo.getGameObject() != null) {
			for (final String objectName : buildingInfo.getGameObject()) {
				final GameObject gameObject = eventAssets.getGameObject(objectName);
				CompositeItem gameObjectButton = sceneLoader.getRoot().getCompositeById(objectName);
				gameObjectButton.setVisible(true);
				setGameObjectVisibility(gameObjectButton, gameObject.getObjectType());
				setGameObjectFunction(gameObjectButton, objectName);
				if (objectName.equals("save")) {
					gameObjectButton.addListener(new SimpleTouchListener() {
						public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
							BuildingScreen.isInSave = true;
						}
					});
				} else {
					gameObjectButton.addListener(new SimpleTouchListener() {
						@Override
						public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
							timeManager.plusMinute(5);
							eventManager.setCurrentGameObject(gameObject);
							gameObjectPopup = new GameObjectPopup();
							gameObjectPopup.setAtlasUiAssets(atlasUiAssets);
							gameObjectPopup.setListenerFactory(listenerFactory);
							gameObjectPopup.setGameObject(gameObject);
							gameObjectPopup.setConstantsAssets(constantsAssets);
							gameObjectPopup.initialize(objectName);
							addActor(gameObjectPopup);
							gameObjectPopup.setVisible(true);
						}
					});
				}
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
			case NORMAL :
				objectButton.setLayerVisibilty(GameObjectEnum.PRESSED.toString(), false);
				objectButton.setLayerVisibilty(GameObjectEnum.NORMAL.toString(), true);
				objectButton.setTouchable(Touchable.enabled);
				break;
			case PRESSED :
				objectButton.setLayerVisibilty(GameObjectEnum.PRESSED.toString(), true);
				objectButton.setLayerVisibilty(GameObjectEnum.NORMAL.toString(), false);
				objectButton.setTouchable(Touchable.disabled);
				break;
			case FUNCTION :
				break;
			default :
				Gdx.app.log("BuildingStage", "NULL GameObjectEnum Type");
				break;
		}
	}
}
