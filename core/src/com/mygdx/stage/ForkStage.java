package com.mygdx.stage;

import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.ArrowButtonListener;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.model.surroundings.Fork;
import com.mygdx.model.surroundings.NodeConnection;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 * 
 */
public class ForkStage extends BaseOverlapStage {

	@Autowired
	private NodeAssets nodeAssets;

	@Autowired
	private PartyManager partymanager;

	@Autowired
	private WorldMapAssets worldMapAssets;

	@Autowired
	private ListenerFactory listenerFactory;
	private Fork forkInfo;
	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);
		cameraManager.stretchToDevice(this);

		setButton();

		return this;
	}

	private void makeScene(PositionManager positionManager, NodeAssets nodeAssets) {
		Gdx.app.log("forkStage", String.valueOf(positionManager.getCurrentNodeName()));
		if (nodeAssets.getForkByName(positionManager.getCurrentNodeName()) != null) {
			forkInfo = nodeAssets.getForkByName(positionManager.getCurrentNodeName());
			sceneLoader.loadScene(forkInfo.getSceneName());
		} else {
			forkInfo = nodeAssets.getForkByName("blackwood");
			sceneLoader.loadScene(forkInfo.getSceneName());
		}
	}

	private void setButton() {
		Gdx.app.log("forkStage", String.valueOf(positionManager.getCurrentNodeName()));

		makeScene(positionManager, nodeAssets);
		addActor(sceneLoader.getRoot());

		CompositeItem saveButton = sceneLoader.getRoot().getCompositeById("save");
		CompositeItem restButton = sceneLoader.getRoot().getCompositeById("rest");

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
		Map<String, NodeConnection> connectionMap = worldMapAssets.getWorldNodeInfo(currentNode).getNodeConnection();

		for (final Entry<String, NodeConnection> connection : connectionMap.entrySet()) {
			final CompositeItem arrow = sceneLoader.getRoot().getCompositeById(connection.getValue().getArrowName());
			if (arrow != null) {
				arrow.setVisible(true);
				arrow.setTouchable(Touchable.enabled);
				ArrowButtonListener arrowButtonListener = listenerFactory.getArrowButtonListener();
				arrowButtonListener.setConnection(connection);
				arrow.addListener(arrowButtonListener);
			}
		}
	}
}
