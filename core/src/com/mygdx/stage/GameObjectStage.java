package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.event.EventScene;

public class GameObjectStage extends BaseOneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private TextureManager textureManager;
	private Label scriptTitle;
	private Label scriptContent;
	private Image characterImage;
	private Image backgroundImage;
	@Autowired
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("EventStage");
		eventManager.setCurrentEventElementType(EventElementEnum.GAME_OBJECT);
		EventScene eventScene = eventManager.getGameObjectEventScene();
		setScene(eventScene);
		this.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				rewardManager.doRewards(); // 보상이 있을경우 보상실행
				eventManager.finishNpcEvent();
				movingManager.goCurrentPosition();
			}
		});
		return this;
	}

	public void setScene(EventScene eventScene) {
		backgroundImage = new Image(textureManager.getBackgroundTexture(eventScene.getBackgroundPath()));
		scriptTitle = new Label("Title", uiComponentAssets.getSkin());
		scriptContent = new Label(eventScene.getScript(), uiComponentAssets.getSkin());
		characterImage = new Image(textureManager.getBackgroundTexture(eventScene.getCharacterPath()));
		tableStack.add(backgroundImage);
		tableStack.add(makeChatTable());
	}

	private Table makeChatTable() {
		Table chatTable = new Table();
		chatTable.left().bottom();

		// FIXME talkerHeight가 이상하다.
		chatTable.add(characterImage).width(uiConstantsMap.get("talkerWidth"))
				.height(uiConstantsMap.get("talkerHeight")).padLeft(uiConstantsMap.get("talkerPadLeft"));
		scriptContent.setFontScale(1.0f);
		scriptContent.setWrap(true);
		scriptContent.setSize(uiConstantsMap.get("scriptWidth"), uiConstantsMap.get("scriptHeight"));

		Table scriptTable = new Table();
		scriptTable.add(scriptTitle).width(uiConstantsMap.get("scriptTitleWidth"))
				.height(uiConstantsMap.get("scriptTitleHeight")).padBottom(uiConstantsMap.get("scriptTitlePadBottom"));
		scriptTable.row();
		scriptTable.add(scriptContent).width(uiConstantsMap.get("scriptContentWidth"))
				.height(uiConstantsMap.get("scriptContentHeight"));

		chatTable.add(scriptTable).padLeft(uiConstantsMap.get("scriptPadLeft"))
				.padBottom(uiConstantsMap.get("scriptPadBottom"));

		return chatTable;
	}
}
