package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.RewardManager;
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
	private Label scriptTitle;
	private Label scriptContent;
	private Image characterImage;
	private Image backgroundImage;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("EventStage");

	public Stage makeStage() {
		super.makeStage();
		eventManager.setCurrentEventElementType(EventElementEnum.GAME_OBJECT);
		EventScene eventScene = eventManager.getGameObjectEventScene();
		setScene(eventScene);
		this.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				rewardManager.doReward(); // 보상이 있을경우 보상실행
				eventManager.finishGameObjectEvent();
				movingManager.goCurrentPosition();
				return true;
			}
		});
		return this;
	}
	public void setScene(EventScene eventScene) {
		backgroundImage = new Image(eventScene.getBackground());
		scriptTitle = new Label("Title", uiComponentAssets.getSkin());
		scriptContent = new Label(eventScene.getScript(),
				uiComponentAssets.getSkin());
		characterImage = new Image(eventScene.getCharacter());
		tableStack.add(backgroundImage);
		tableStack.add(makeChatTable());
	}

	private Table makeChatTable() {
		Table chatTable = new Table();
		chatTable.left().bottom();

		// FIXME talkerHeight가 이상하다.
		chatTable.add(characterImage).width(uiConstantsMap.get("talkerWidth"))
				.height(uiConstantsMap.get("talkerHeight"))
				.padLeft(uiConstantsMap.get("talkerPadLeft"));
		scriptContent.setFontScale(1.0f);
		scriptContent.setWrap(true);
		scriptContent.setSize(uiConstantsMap.get("scriptWidth"),
				uiConstantsMap.get("scriptHeight"));

		Table scriptTable = new Table();
		scriptTable.add(scriptTitle)
				.width(uiConstantsMap.get("scriptTitleWidth"))
				.height(uiConstantsMap.get("scriptTitleHeight"))
				.padBottom(uiConstantsMap.get("scriptTitlePadBottom"));
		scriptTable.row();
		scriptTable.add(scriptContent)
				.width(uiConstantsMap.get("scriptContentWidth"))
				.height(uiConstantsMap.get("scriptContentHeight"));

		chatTable.add(scriptTable).padLeft(uiConstantsMap.get("scriptPadLeft"))
				.padBottom(uiConstantsMap.get("scriptPadBottom"));

		return chatTable;
	}
}
