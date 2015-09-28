package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.NodeAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.assets.WorldMapAssets;
import com.mygdx.enums.WorldNodeEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.LeaveEventElementListener;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.event.EventElement;
import com.mygdx.model.location.SubNode;

public class GreetingStage extends BaseOneLevelStage {
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private WorldMapAssets worldMapAssets;
	@Autowired
	private NodeAssets nodeAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private transient TextureManager textureManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private ListenerFactory listenerFactory;
	private Label scriptTitle = new Label("", StaticAssets.skin);
	private Label scriptContent = new Label("", StaticAssets.skin);
	private Image characterImage;
	private Image backgroundImage;
	private Table chatLineImageTable = new Table();
	private Table characterBustTable = new Table();
	private Table scriptTable = new Table();
	private HashMap<String, Float> uiConstantsMap;

	public Stage makeStage() {
		super.makeStage();
		setUiConstantsMap(constantsAssets.getUiConstants("EventStage"));
		setScene();
		addLeaveListener();
		return this;
	}

	private void addLeaveListener() {
		LeaveEventElementListener leaveEventElementListener = listenerFactory.getLeaveEventElementListener();
		this.addListener(leaveEventElementListener);
	}

	public void setScene() {
		List<String> greetingMessageList = eventManager.getCurrentNpc().getGreetingMessages();
		WorldNodeEnum.NodeType nodeType = worldMapAssets.getNodeType(positionManager.getCurrentNodePath());
		SubNode subNodeInfo = nodeAssets.getSubNodeInfo(nodeType, positionManager.getCurrentNodePath(),
				positionManager.getCurrentSubNodePath());
		EventElement eventElement = eventManager.getCurrentNpc();
		setScript(eventElement, greetingMessageList);
		makeChatTable(eventElement, subNodeInfo);
	}

	public void setScript(EventElement eventElement, List<String> greetingMessageList) {
		List<String> shuffleList = new ArrayList<String>(greetingMessageList);
		Collections.shuffle(shuffleList);
		String greetingMessage = shuffleList.get(0);

		scriptTitle.setText("[" + eventElement.getName() + "]");
		scriptContent.setText(greetingMessage);
	}

	public HashMap<String, Float> getUiConstantsMap() {
		return uiConstantsMap;
	}

	public void setUiConstantsMap(HashMap<String, Float> uiConstantsMap) {
		this.uiConstantsMap = uiConstantsMap;
	}

	private void makeChatTable(EventElement eventElement, SubNode subNodeInfo) {
		String backgroundPath = subNodeInfo.getSubNodePath();
		backgroundImage = new Image(textureManager.getBackgroundTexture(backgroundPath));
		characterImage = new Image(textureManager.getBustTexture(eventElement.getFacePath(), "01"));

		Image chatImage = uiComponentAssets.getChatLineImage();
		chatLineImageTable.clear();
		chatLineImageTable.left().bottom();
		chatLineImageTable.add(chatImage);

		characterBustTable.clear();
		characterBustTable.right().bottom();

		characterBustTable.add(characterImage).width(uiConstantsMap.get("talkerWidth"))
				.height(uiConstantsMap.get("talkerHeight")).padLeft(uiConstantsMap.get("talkerPadLeft"));

		scriptTable.clear();
		scriptTable.left().bottom().padLeft(uiConstantsMap.get("scriptPadLeft"))
				.padBottom(uiConstantsMap.get("scriptPadBottom"));
		scriptTable.add(scriptTitle).width(uiConstantsMap.get("scriptTitleWidth"))
				.height(uiConstantsMap.get("scriptTitleHeight"));
		scriptTable.row();
		scriptContent.setWrap(true);
		scriptContent.setAlignment(Align.top);
		scriptTable.add(scriptContent).width(uiConstantsMap.get("scriptContentWidth"))
				.height(uiConstantsMap.get("scriptContentHeight")).padTop(uiConstantsMap.get("scriptContentPadTop"));

		tableStack.add(backgroundImage);
		tableStack.add(chatLineImageTable);
		tableStack.add(characterBustTable);
		tableStack.add(scriptTable);
	}
}
