package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.BuildingTypeEnum;
import com.mygdx.enums.GameObjectEnum;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.manager.TimeManager;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.event.NPC;
import com.mygdx.model.location.Building;
import com.mygdx.nextSectionChecker.ArgumentChecker;
import com.mygdx.popup.GameObjectPopup;
import com.mygdx.screen.BuildingScreen;

public class BuildingStage extends BaseOneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private transient UiComponentAssets uiComponentAssets;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private transient AtlasUiAssets atlasUiAssets;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private transient TextureManager textureManager;
	@Autowired
	private transient ConstantsAssets constantsAssets;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private PositionManager positionManager;

	private static final int GAME_OBJECT_POSITION[][] = {{80, 45}, {450, 45}, {820, 45}, {1210, 45}, {1570, 45}};
	private Building buildingInfo;
	private GameObjectPopup gameObjectPopup;

	public Stage makeStage() {
		super.makeStage();
		cameraManager.stretchToDevice(this);
		buildingInfo = nodeAssets.getVillageByPath(positionManager.getCurrentNodePath()).getBuilding()
				.get(positionManager.getCurrentSubNodePath());
		makeBuildingScene();
		setBuildingFunction();
		setNpcList();
		setGameObject();
		this.addActor(tableStack);
		return this;
	}

	private void setBuildingFunction() {
		if (buildingInfo.getBuildingType().equals(BuildingTypeEnum.INN)) {
			Table buttonTable = new Table();
			ImageButton saveButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_save"));
			buttonTable.add(saveButton);
			buttonTable.row();
			buttonTable.row();

			saveButton.addListener(new SimpleTouchListener() {
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					BuildingScreen.isInSave = true;
				}
			});

			ImageButton restButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(
					uiComponentAssets.getStayButton())));
			buttonTable.add(restButton);
			buttonTable.row();
			restButton.addListener(new SimpleTouchListener() {
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					timeManager.plusMinute(5);
					gameObjectPopup = new GameObjectPopup();
					gameObjectPopup.setAtlasUiAssets(atlasUiAssets);
					gameObjectPopup.setListenerFactory(listenerFactory);
					gameObjectPopup.setConstantsAssets(constantsAssets);
					addActor(gameObjectPopup);
					gameObjectPopup.setVisible(true);
				}
			});
			tableStack.add(buttonTable);
		}
	}

	private void setGameObject() {
		cameraManager.stretchToDevice(this);
		Table buttonTable = new Table();
		buttonTable.setFillParent(true);
		buttonTable.right().bottom();
		buttonTable.padRight(StaticAssets.BASE_WINDOW_WIDTH * 0.14f).padBottom(StaticAssets.BASE_WINDOW_HEIGHT * 0.16f);
		buttonTable.row();
		tableStack.addActor(buttonTable);
		if (buildingInfo.getGameObject() != null) {
			for (final String objectName : buildingInfo.getGameObject()) {
				final GameObject gameObject = eventManager.getEventInfo().getGameObjectMap().get(objectName);
				{
					if (gameObject != null) {
						if (!gameObject.getObjectType().equals(GameObjectEnum.FUNCTION)) {
							ImageButton gameObjectButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(
									textureManager.getGameObjectTexture(gameObject.getFacePath()))));
							Table gameObjectTable = new Table();
							gameObjectTable.add(gameObjectButton);
							gameObjectTable.left().bottom();
							gameObjectTable.padLeft(GAME_OBJECT_POSITION[gameObject.getPositionIndex()][0]).padBottom(
									GAME_OBJECT_POSITION[gameObject.getPositionIndex()][1]);
							gameObjectTable.addListener(new SimpleTouchListener() {
								@Override
								public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
									timeManager.plusMinute(5);
									eventManager.setCurrentGameObject(gameObject);
									positionManager.setCurrentEventPositionType(EventPosition.GAME_OBJECT);
									screenFactory.show(ScreenEnum.GAME_OBJECT);
								}
							});
							tableStack.add(gameObjectTable);
						}
					}
				}
			}
		}
	}

	private void makeBuildingScene() {
		Table backgroundTable = new Table();
		backgroundTable.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		backgroundTable.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		TextureRegionDrawable backgroundImage;
		if (buildingInfo.getBackgroundPath() != null) {
			backgroundImage = new TextureRegionDrawable(new TextureRegion(
					textureManager.getBackgroundTexture(buildingInfo.getBackgroundPath())));
		} else {
			backgroundImage = new TextureRegionDrawable(new TextureRegion(
					textureManager.getBackgroundTexture(buildingInfo.getSubNodePath())));
		}
		backgroundTable.setBackground(backgroundImage);
		addActor(backgroundTable);
	}

	private void setNpcList() {
		if (buildingInfo.getBuildingNpc() != null) {
			for (final String npcName : buildingInfo.getBuildingNpc()) {
				NPC npc = eventManager.getEventInfo().getNpcMap().get(npcName);
				if (npc != null) {
					if (ArgumentChecker.checkIsInTargetTime(npc.getTargetTime(), timeManager.getDayMinute())) {
						ImageButton npcImage;
						if (npc.getFacePath() != null) {
							npcImage = new ImageButton(new TextureRegionDrawable(new TextureRegion(
									textureManager.getCharacterBodyTexture(npc.getFacePath()))));
						} else {
							npcImage = new ImageButton(new TextureRegionDrawable(new TextureRegion(
									textureManager.getCharacterBodyTexture(npc.getElementPath()))));
						}

						npcImage.setTouchable(Touchable.enabled);
						Table npcTable = new Table();
						npcTable.left().bottom();
						npcTable.padLeft(GAME_OBJECT_POSITION[npc.getPositionIndex()][0]).padBottom(
								GAME_OBJECT_POSITION[npc.getPositionIndex()][1]);
						npcTable.add(npcImage);
						npcTable.addListener(new SimpleTouchListener() {
							@Override
							public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
								timeManager.plusMinute(5);
								eventManager.setCurrentNpc(npcName);
								positionManager.setCurrentEventPositionType(EventPosition.NPC);
								screenFactory.show(ScreenEnum.GREETING);
							}
						});
						tableStack.add(npcTable);
					}
				}
			}
		}
	}
}
