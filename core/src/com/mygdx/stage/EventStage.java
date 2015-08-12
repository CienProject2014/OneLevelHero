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
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("EventStage");
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
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					if (eventSceneIterator.hasNext()) {
						setScene(eventSceneIterator.next());
					} else {
						if (!eventCheckManager.isSelectEvent(eventManager.getCurrentEvent())) {
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

		scriptContentStyle = new ArrayList<TextButtonStyle>();
		/* scriptContent = new ArrayList<TextButton>(); */

		scriptTitle = new Label("Title", uiComponentAssets.getSkin());
		scriptContentStyle.add(new TextButtonStyle(uiComponentAssets.getScriptButton(),
				uiComponentAssets.getScriptButton(), uiComponentAssets.getScriptButton(), uiComponentAssets.getFont()));
		/*
		 * scriptContent.add(new TextButton(eventScene.getScript(),
		 * scriptContentStyle.get(0)));
		 */
		scriptContent = new TextButton(eventScene.getScript(), scriptContentStyle.get(0));

		Texture selectedCharacter = eventScene.getCharacter();
		characterImage = new Image(selectedCharacter);
		tableStack.add(backgroundImage);
		tableStack.add(makeChatTable());
	}

	private Table makeChatTable() {

		final float buttonSize[] = { StaticAssets.BASE_WINDOW_WIDTH * 1.0f, StaticAssets.BASE_WINDOW_HEIGHT * 0.338f };
		/*
		 * for (TextButton contentButton : scriptContent) {
		 * contentButton.setSize(buttonSize[0], buttonSize[1]); }
		 */

		scriptContent.setSize(buttonSize[0], buttonSize[1]);

		scriptContent.setPosition(0, 0);

		addActor(scriptContent);
		/*
		 * for (TextButton contentButton : scriptContent) {
		 * this.addActor(contentButton); }
		 */

		Table chatTable = new Table();
		chatTable.left().bottom();

		// FIXME talkerHeight가 이상하다.
		chatTable.add(characterImage).width(uiConstantsMap.get("talkerWidth"))
				.height(uiConstantsMap.get("talkerHeight")).padLeft(uiConstantsMap.get("talkerPadLeft"));

		/*
		 * Table scriptTable = new Table();
		 * scriptTable.add(scriptTitle).width(uiConstantsMap.get(
		 * "scriptTitleWidth"))
		 * .height(uiConstantsMap.get("scriptTitleHeight")).padBottom(
		 * uiConstantsMap.get("scriptTitlePadBottom")); scriptTable.row();
		 * 
		 * scriptTable.add(scriptContent.get(0)).width(uiConstantsMap.get(
		 * "scriptContentWidth"))
		 * .height(uiConstantsMap.get("scriptContentHeight"));
		 * 
		 * chatTable.add(scriptTable).padLeft(uiConstantsMap.get("scriptPadLeft"
		 * )) .padBottom(uiConstantsMap.get("scriptPadBottom"));
		 */
		return chatTable;
	}
}
