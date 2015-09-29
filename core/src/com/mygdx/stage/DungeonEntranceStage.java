package com.mygdx.stage;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.enums.TextureEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.ArrowButtonListener;
import com.mygdx.listener.DungeonEntranceButtonListener;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.event.GameObject;
import com.mygdx.model.location.DungeonEntrance;
import com.mygdx.model.location.NodeConnection;
import com.mygdx.screen.DungeonEntranceScreen;

/**
 * @author Velmont
 * 
 */
public class DungeonEntranceStage extends BaseOneLevelStage {
	private ImageButton entranceButton, saveButton, restButton, backButton;
	@Autowired
	private PartyManager partymanager;
	@Autowired
	private transient TextureManager textureManager;
	@Autowired
	private transient EventManager eventManager;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private PositionManager positionManager;
	private DungeonEntrance dungeonEntranceInfo;

	public Stage makeStage() {
		super.makeStage();
		dungeonEntranceInfo = nodeAssets.getDungeonEntranceByPath(positionManager.getCurrentNodePath());
		makeScene(dungeonEntranceInfo);
		setButton();
		return this;
	}

	private void makeScene(DungeonEntrance dungeonEntranceInfo) {
		Table backgroundTable = new Table();
		backgroundTable.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		backgroundTable.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		TextureRegionDrawable backgroundImage = new TextureRegionDrawable(new TextureRegion(
				textureManager.getBackgroundTexture(dungeonEntranceInfo.getNodePath(), TextureEnum.NORMAL)));
		backgroundTable.setBackground(backgroundImage);
		tableStack.addActor(backgroundTable);
	}

	private void setButton() {
		entranceButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_go"));
		saveButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_save"));
		restButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_rest"));
		backButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_field"));

		dungeonManager.getDungeonInfo().setStartDungeonRoomIndex(dungeonEntranceInfo.getStartDungeonRoomIndex());
		DungeonEntranceButtonListener dungeonEntranceButtonListener = listenerFactory
				.getDungeonEntranceButtonListener();
		dungeonEntranceButtonListener.setDungeonPath(dungeonEntranceInfo.getNodePath());
		entranceButton.addListener(dungeonEntranceButtonListener);

		saveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				DungeonEntranceScreen.isInSave = true;
			}
		});

		restButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				timeManager.plusMinute(5);
				GameObject gameObject = eventManager.getEventInfo().getGameObjectMap().get("rest");
				eventManager.setCurrentGameObject(gameObject);
				DungeonEntranceScreen.isClickPopup = true;

			}
		});

		String currentNode = positionManager.getCurrentNodePath();
		Entry<String, NodeConnection> nodeConnection = worldMapAssets.getWorldNodeInfo(currentNode).getNodeConnection()
				.entrySet().iterator().next();
		ArrowButtonListener arrowButtonListener = listenerFactory.getArrowButtonListener();
		arrowButtonListener.setConnection(nodeConnection);
		backButton.addListener(arrowButtonListener);

		Table entranceTable = new Table();
		entranceTable.setFillParent(true);
		entranceTable.center();
		entranceTable.add(entranceButton);

		Table buttonTable = new Table();
		buttonTable.setFillParent(true);
		buttonTable.right().bottom();
		buttonTable.padRight(StaticAssets.BASE_WINDOW_WIDTH * 0.14f).padBottom(StaticAssets.BASE_WINDOW_HEIGHT * 0.16f);
		buttonTable.row();
		buttonTable.add(saveButton);
		buttonTable.row();
		buttonTable.add(restButton);
		buttonTable.row();
		buttonTable.add(backButton);
		tableStack.addActor(entranceTable);
		tableStack.addActor(buttonTable);
	}
}
