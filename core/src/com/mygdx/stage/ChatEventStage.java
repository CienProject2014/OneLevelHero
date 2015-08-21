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
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.manager.EventCheckManager;
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
	private EventCheckManager eventCheckManager;
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private UnitAssets unitAssets;
	@Autowired
	private TextureManager textureManager;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("EventStage");
	private Label scriptTitle = new Label("", StaticAssets.skin);
	private Label scriptContent = new Label("", StaticAssets.skin);
	private Image characterImage;
	private Image backgroundImage;
	private Table chatLineImageTable = new Table();
	private Table characterBustTable = new Table();
	private Table scriptTable = new Table();

	public Stage makeStage(final Iterator<EventScene> eventSceneIterator) {
		super.makeStage();
		if (eventSceneIterator.hasNext()) {
			setScene(eventSceneIterator.next());
			this.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					if (eventSceneIterator.hasNext()) {
						setScene(eventSceneIterator.next());
					} else {
						if (!eventCheckManager.isSelectEvent(eventManager.getCurrentNpcEvent())) {
							rewardManager.doReward(); // 보상이 있을경우 보상실행
							eventManager.finishNpcEvent();
							storySectionManager.runStorySequence();
						}
					}
					return true;
				}
			});
		}
		return this;
	}

	public Stage makeStage(EventScene eventScene) {
		super.makeStage();
		eventManager.setCurrentEventElementType(EventElementEnum.NPC);
		setScene(eventScene);
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

	public void setScene(EventScene eventScene) {
		setScript(eventScene);
		makeChatTable(eventScene);
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
		characterBustTable.left().bottom();
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
