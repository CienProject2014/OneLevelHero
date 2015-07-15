package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.currentState.EventInfo;
import com.mygdx.model.EventScene;

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
	private EventInfo eventInfo;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("EventStage");
	private Label scriptTitle;
	private Label scriptContent;
	private Image characterImage;
	private Image backgroundImage;

	/**
	 * EventManager로부터 eventScene정보를 받아 그래픽처리를 해준다.
	 *
	 * @param eventScene
	 * @return
	 */
	public Stage makeStage(EventScene eventScene) {
		super.makeStage();
		backgroundImage = new Image(eventScene.getBackground());
		scriptTitle = new Label("Title", uiComponentAssets.getSkin());
		scriptContent = new Label(eventScene.getScript(),
				uiComponentAssets.getSkin());
		characterImage = new Image(eventScene.getCharacter());

		tableStack.add(backgroundImage);
		tableStack.add(makeChatTable());

		return this;
	}

	private Table makeChatTable() {
		Table chatTable = new Table();
		chatTable.left().bottom();

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

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public UiComponentAssets getUiComponentAssets() {
		return uiComponentAssets;
	}

	public void setUiComponentAssets(UiComponentAssets uiComponentAssets) {
		this.uiComponentAssets = uiComponentAssets;
	}
}
