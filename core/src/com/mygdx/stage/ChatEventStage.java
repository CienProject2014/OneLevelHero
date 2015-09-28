package com.mygdx.stage;

import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.event.EventScene;
import com.mygdx.model.unit.Hero;

/**
 * make and return stage(Event)
 * 
 * @author Velmont
 * 
 */
public class ChatEventStage extends BaseOneLevelStage {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;
	private Label scriptTitle = new Label("", StaticAssets.skin);
	private Label scriptContent = new Label("", StaticAssets.skin);
	private Image characterImage;
	private Image backgroundImage;
	private Table chatLineImageTable = new Table();
	private Table characterBustTable = new Table();
	private Table scriptTable = new Table();

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("EventStage");
		final Iterator<EventScene> eventSceneIterator = eventManager.getCurrentChatScenes().iterator();

		if (eventSceneIterator.hasNext()) {
			final EventScene eventScene = eventSceneIterator.next();
			setChatScene(eventScene);
			rewardManager.addEventRewards(eventManager.getCurrentEvent().getRewards());
			this.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					if (eventSceneIterator.hasNext()) {
						final EventScene nextEventScene = eventSceneIterator.next();
						rewardManager.doSceneRewards();
						setChatScene(nextEventScene);
						rewardManager.addSceneRewards(nextEventScene.getRewards());
					} else {
						rewardManager.doSceneRewards();
						rewardManager.doEventRewards();
						eventManager.finishEvent();
						storySectionManager.runStorySequence();
					}
					return true;
				}
			});
		}
		return this;
	}

	private void setScript(EventScene eventScene) {
		Hero hero = unitAssets.getHero(eventScene.getCharacterPath());
		if (hero != null) {
			scriptTitle.setText("[" + hero.getName() + "]");
		} else {
			scriptTitle.setText("");
		}
		scriptContent.setText(eventScene.getScript());
	}

	public void setChatScene(EventScene eventScene) {
		setScript(eventScene);
		makeChatTable(eventScene);
		makeSkipButton();
	}

	private void makeSkipButton() {
		TextButton skipButton = new TextButton("스킵", uiComponentAssets.getSkin());
		skipButton.center();
		skipButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				rewardManager.doEventRewards(); // 보상이 있을경우 보상실행
				eventManager.finishEvent();
				storySectionManager.runStorySequence();
			}
		});

		addActor(skipButton);
	}

	private void makeChatTable(EventScene eventScene) {
		backgroundImage = new Image(textureManager.getBackgroundTexture(eventScene.getBackgroundPath()));
		characterImage = new Image(textureManager.getBustTexture(eventScene.getCharacterPath(),
				eventScene.getFaceNumber()));

		Image chatImage = uiComponentAssets.getChatLineImage();
		chatLineImageTable.clear();
		chatLineImageTable.left().bottom();
		chatLineImageTable.add(chatImage);

		characterBustTable.clear();

		if (eventScene.getSpeakerPosition() == null) {
			characterBustTable.left().bottom();
		} else if (eventScene.getSpeakerPosition().equals(EventScene.SPEAKER_RIGHT)) {
			characterBustTable.right().bottom();
		} else { // left일 경우
			characterBustTable.left().bottom();
		}

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
