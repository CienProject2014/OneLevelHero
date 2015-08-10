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
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventScene;

/**
 * make and return stage(Event)
 * 
 * @author Velmont
 * 
 */
public class EventStage extends BaseOneLevelStage {
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
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("EventStage");
	private Label scriptTitle;
	private Label scriptContent;
	private Image characterImage;
	private Image backgroundImage;

	public Stage makeStage(final Iterator<EventScene> eventSceneIterator) {
		super.makeStage();
		if (eventSceneIterator.hasNext()) {
			setScene(eventSceneIterator.next());

			this.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					if (eventSceneIterator.hasNext()) {
						setScene(eventSceneIterator.next());
					} else {
						if (!eventCheckManager.isSelectEvent(eventManager
								.getCurrentEvent())) {
							rewardManager.doReward(); // 보상이 있을경우 보상실행
							eventManager.finishEvent();
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
		setScene(eventScene);
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
