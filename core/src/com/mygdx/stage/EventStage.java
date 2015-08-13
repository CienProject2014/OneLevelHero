package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.assets.UnitAssets;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.event.EventScene;
import com.mygdx.model.unit.Hero;

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
	@Autowired
	private UnitAssets unitAssets;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("EventStage");
	private Label scriptTitle;
	private TextButton scriptContent;
	private List<TextButtonStyle> scriptContentStyle;
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

	private void erazeAll() {
		if (scriptTitle != null) {
			scriptTitle.setText("");
		}
		if (scriptContent != null) {
			scriptContent.setText("");
		}
	}

	public void setScene(EventScene eventScene) {
		erazeAll();
		backgroundImage = new Image(eventScene.getBackground());

		scriptContentStyle = new ArrayList<TextButtonStyle>();
		/* scriptContent = new ArrayList<TextButton>(); */

		scriptTitle = new Label("Title", uiComponentAssets.getSkin());
		Hero hero = unitAssets.getHero(eventScene.getCharacterPath());
		if (hero != null) {
			scriptTitle.setText(hero.getName());
		}
		scriptContentStyle.add(new TextButtonStyle(uiComponentAssets
				.getScriptButton(), uiComponentAssets.getScriptButton(),
				uiComponentAssets.getScriptButton(), uiComponentAssets
						.getFont()));
		scriptContent = new TextButton(eventScene.getScript(),
				scriptContentStyle.get(0));

		Texture selectedCharacter = eventScene.getCharacter();
		characterImage = new Image(selectedCharacter);
		tableStack.add(backgroundImage);
		tableStack.add(makeChatTable());
	}

	private Table makeChatTable() {
		final float buttonSize[] = {StaticAssets.BASE_WINDOW_WIDTH * 1.0f,
				StaticAssets.BASE_WINDOW_HEIGHT * 0.338f};
		/*
		 * for (TextButton contentButton : scriptContent) {
		 * contentButton.setSize(buttonSize[0], buttonSize[1]); }
		 */

		scriptContent.setSize(buttonSize[0], buttonSize[1]);
		scriptContent.setPosition(0, 0);
		scriptTitle.setSize(uiConstantsMap.get("scriptTitleWidth"),
				uiConstantsMap.get("scriptTitleHeight"));
		scriptTitle.setPosition(0, uiConstantsMap.get("scriptTitlePadBottom"));
		addActor(scriptContent);
		addActor(scriptTitle);
		/*
		 * for (TextButton contentButton : scriptContent) {
		 * this.addActor(contentButton); }
		 */

		Table chatTable = new Table();
		chatTable.left().bottom();

		chatTable.add(characterImage).width(uiConstantsMap.get("talkerWidth"))
				.height(uiConstantsMap.get("talkerHeight"))
				.padLeft(uiConstantsMap.get("talkerPadLeft"));

		return chatTable;
	}
}
