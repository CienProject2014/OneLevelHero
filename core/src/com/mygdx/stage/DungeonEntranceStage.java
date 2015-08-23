package com.mygdx.stage;

import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
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
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.ArrowButtonListener;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.surroundings.DungeonEntrance;
import com.mygdx.model.surroundings.NodeConnection;

/**
 * @author Velmont
 * 
 */
public class DungeonEntranceStage extends BaseOneLevelStage {
	private ImageButton entranceButton, saveButton, restButton, backButton;
	@Autowired
	private PartyManager partymanager;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private PositionManager positionManager;
	private DungeonEntrance dungeonEntranceInfo;

	public Stage makeStage() {
		super.makeStage();
		dungeonEntranceInfo = nodeAssets.getDungeonEntranceByName(positionManager.getCurrentNodeName());
		makeScene(dungeonEntranceInfo);
		setButton();
		return this;
	}

	private void makeScene(DungeonEntrance dungeonEntranceInfo) {
		Table backgroundTable = new Table();
		backgroundTable.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		backgroundTable.setHeight(StaticAssets.BASE_WINDOW_HEIGHT);
		TextureRegionDrawable backgroundImage = new TextureRegionDrawable(new TextureRegion(
				textureManager.getBackgroundTexture(dungeonEntranceInfo.getNodePath())));
		backgroundTable.setBackground(backgroundImage);
		tableStack.addActor(backgroundTable);
	}

	private void setButton() {
		entranceButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_go"));
		saveButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_save"));
		restButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_rest"));
		backButton = new ImageButton(atlasUiAssets.getAtlasUiFile("stay_button_field"));

		entranceButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				screenFactory.show(ScreenEnum.DUNGEON);
			}
		});

		saveButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.debug("DungeonEntranceStage", "게임이 저장되었다...");
			}
		});

		restButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				partymanager.setFatigue(0);
				partymanager.healAllHero();
				Gdx.app.debug("DungeonEntranceStage", "잘 쉬었도다...");
			}
		});

		String currentNode = positionManager.getCurrentNodeName();
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
