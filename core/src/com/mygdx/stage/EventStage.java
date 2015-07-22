package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.EventScene;
import com.uwsoft.editor.renderer.actor.LabelItem;
import com.uwsoft.editor.renderer.data.LabelVO;

/**
 * make and return stage(Event)
 *
 * @author Velmont
 *
 */
public class EventStage extends BaseOverlapStage {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private RewardManager rewardManager;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("EventStage");
	private Label scriptTitle;
	private Label scriptContent;
	private ArrayList<LabelVO> labels;
	private Image characterImage;
	private Image characterImage2;
	private Image backgroundImage;
	private final String SCRIPT = "script";
	private final String NAME = "name";
	private final String SELECT_01 = "select_label_01";
	private final String SELECT_02 = "select_label_02";
	private final String SELECT_03 = "select_label_03";
	private final String SELECT_04 = "select_label_04";

	public Stage makeStage(final Iterator<EventScene> eventSceneIterator) {
		initSceneLoader(StaticAssets.rm);
		sceneLoader.loadScene("event_scene");
		if (eventSceneIterator.hasNext()) {
			setScene(eventSceneIterator.next());

			this.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					if (eventSceneIterator.hasNext()) {
						setScene(eventSceneIterator.next());
					} else {
						rewardManager.doReward(); // 보상이 있을경우 보상실행
						eventManager.finishEvent();
						storySectionManager.runStorySequence();
					}
					return true;
				}
			});
		}

		return this;
	}

	public Stage makeStage(EventScene eventScene) {

		setScene(eventScene);
		return this;
	}

	public void setScene(EventScene eventScene) {
		labels = sceneLoader.getRoot().dataVO.composite.sLabels;

		LabelItem scriptContent = sceneLoader.getRoot().getLabelById(SCRIPT);
		scriptContent.setText(eventScene.getScript());

		LabelItem characterName = sceneLoader.getRoot().getLabelById(NAME);
		characterName.setText(eventScene.getCharacterPath());

		LabelItem selectButton_01 = sceneLoader.getRoot().getLabelById(SELECT_01);
		selectButton_01.setText("select01");

		LabelItem selectButton_02 = sceneLoader.getRoot().getLabelById(SELECT_02);
		selectButton_02.setText("select02");

		LabelItem selectButton_03 = sceneLoader.getRoot().getLabelById(SELECT_03);
		selectButton_03.setText("select03");

		LabelItem selectButton_04 = sceneLoader.getRoot().getLabelById(SELECT_04);
		selectButton_04.setText("select04");

		backgroundImage = new Image(eventScene.getBackground());
		/* scriptTitle = new Label("Title", uiComponentAssets.getSkin()); */
		characterImage = new Image(eventScene.getCharacter());

		// Greeting인지 아닌지 여부에 따라 처리
		makeEventStage(eventManager.getCurrentEvent().getEventType());
		addActor(sceneLoader.getRoot());
	}

	private void makeEventStage(EventTypeEnum eventType) {
		switch (eventType) {
		case CHAT:
			// makeChatStage();
			break;
		case SELECT_EVENT:
		case GREETING:
		case SELECT_COMPONENT:
			// makeSelectEventStage();
			break;
		case CREDIT:
			// makeCreditStage();
			break;
		default:
			Gdx.app.log("error", " scene 주입 에러");
			break;
		}
	}

	/*
	 * private Table makeChatTable() { Table chatTable = new Table();
	 * chatTable.left().bottom();
	 * 
	 * // FIXME talkerHeight가 이상하다.
	 * chatTable.add(characterImage).width(uiConstantsMap.get("talkerWidth"))
	 * .height(uiConstantsMap.get("talkerHeight"))
	 * .padLeft(uiConstantsMap.get("talkerPadLeft"));
	 * scriptContent.setFontScale(1.0f); scriptContent.setWrap(true);
	 * scriptContent.setSize(uiConstantsMap.get("scriptWidth"),
	 * uiConstantsMap.get("scriptHeight"));
	 * 
	 * Table scriptTable = new Table(); scriptTable.add(scriptTitle)
	 * .width(uiConstantsMap.get("scriptTitleWidth"))
	 * .height(uiConstantsMap.get("scriptTitleHeight"))
	 * .padBottom(uiConstantsMap.get("scriptTitlePadBottom"));
	 * scriptTable.row(); scriptTable.add(scriptContent)
	 * .width(uiConstantsMap.get("scriptContentWidth"))
	 * .height(uiConstantsMap.get("scriptContentHeight"));
	 * 
	 * chatTable.add(scriptTable).padLeft(uiConstantsMap.get("scriptPadLeft"))
	 * .padBottom(uiConstantsMap.get("scriptPadBottom"));
	 * 
	 * return chatTable; }
	 */
}
