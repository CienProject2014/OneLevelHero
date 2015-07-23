package com.mygdx.stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.PlaceEnum;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.StorySectionPacket;
import com.mygdx.popup.AlertMessagePopup;
import com.mygdx.popup.MessagePopup;
import com.mygdx.popup.StatusMessagePopup;

public class GameUiStage extends BaseOneLevelStage {
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventCheckManager eventCheckManager;

	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("GameUiStage");

	private Table uiTable;
	private Table topTable;
	private StatusMessagePopup statusMessagePopup;
	private Stack<MessagePopup> alertMessage;

	private TextButton placeInfoButton;
	private TextButton timeInfoButton;

	private ImageButton backButton;
	private ImageButton questLogButton;
	private ImageButton helpButton;
	private ImageButton settingButton;

	public Stage makeStage() {
		super.makeStage();
		// 초기화
		uiTable = new Table();
		topTable = new Table(uiComponentAssets.getSkin());

		makeButton();
		addListener();
		makeTable();

		tableStack.add(uiTable);

		alertMessage = new Stack<MessagePopup>();
		// 보상 이벤트 처리
		Iterator<RewardInfo> iterator = rewardManager.getRewardQueue()
				.iterator();
		while (iterator.hasNext()) {
			RewardInfo nextIterator = iterator.next();
			if (nextIterator.getRewardState().equals(RewardStateEnum.ING))
				alertMessage.add(new AlertMessagePopup("[ 보상 ]",
						uiComponentAssets.getSkin()).text(rewardManager
						.getRewardMessage(nextIterator)));
			Gdx.app.log("리워드정보", nextIterator.getRewardTarget() + ", "
					+ nextIterator.getRewardType());
		}
		// 알림 메시지
		statusMessagePopup = new StatusMessagePopup("[ 스테이터스  ]",
				uiComponentAssets.getSkin(), partyManager.getBattleMemberList());

		Iterator<MessagePopup> alertMessageIterator = alertMessage.iterator();
		while (alertMessageIterator.hasNext()) {
			MessagePopup nextIterator = alertMessageIterator.next();
			addActor(nextIterator);
			nextIterator.setVisible(true);
			rewardManager.pollRewardQueue();
		}
		addActor(statusMessagePopup);

		return this;
	}

	// 테이블 디자인
	public void makeTable() {
		topTable.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		float width = uiConstantsMap.get("TButtonWidthSmall");
		topTable.add(backButton).width(width)
				.height(uiConstantsMap.get("TButtonHeightSmall"))
				.padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(placeInfoButton)
				.width(uiConstantsMap.get("TButtonWidthLarge"))
				.height(uiConstantsMap.get("TButtonHeightLarge"))
				.padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(timeInfoButton)
				.width(uiConstantsMap.get("TButtonWidthLarge"))
				.height(uiConstantsMap.get("TButtonHeightLarge"))
				.padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(questLogButton)
				.width(uiConstantsMap.get("TButtonWidthSmall"))
				.height(uiConstantsMap.get("TButtonHeightSmall"))
				.padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(helpButton).width(uiConstantsMap.get("TButtonWidthSmall"))
				.height(uiConstantsMap.get("TButtonHeightSmall"))
				.padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(settingButton)
				.width(uiConstantsMap.get("TButtonWidthSmall"))
				.height(uiConstantsMap.get("TButtonHeightSmall"));

		uiTable.align(Align.top);
		uiTable.add(topTable).padLeft(uiConstantsMap.get("padLeft"))
				.padTop(uiConstantsMap.get("padTop"));
	}

	public void makeButton() {
		TextButtonStyle style = new TextButtonStyle(
				atlasUiAssets.getAtlasUiFile("time_info_button"),
				atlasUiAssets.getAtlasUiFile("time_info_button"),
				atlasUiAssets.getAtlasUiFile("time_info_button"),
				uiComponentAssets.getFont());

		placeInfoButton = new TextButton("장소", style);
		timeInfoButton = new TextButton(timeManager.getDay() + "d"
				+ timeManager.getHour() + "h" + timeManager.getMinute() + "m",
				style);

		backButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("back_button"),
				atlasUiAssets.getAtlasUiFile("back_toggle_button"));
		questLogButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("quest_log_button"),
				atlasUiAssets.getAtlasUiFile("quest_log_toggle_button"));
		helpButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("help_button"),
				atlasUiAssets.getAtlasUiFile("help_toggle_button"));
		settingButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("setting_button"),
				atlasUiAssets.getAtlasUiFile("setting_toggle_button"));
	}

	// 리스너 할당
	public void addListener() {
		helpButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.WORLD_MAP);
			}

		});
		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {

				switch (positionManager.getCurrentPlace()) {
					case BUILDING:
						positionManager.setCurrentPlace(PlaceEnum.VILLAGE);
						screenFactory.show(ScreenEnum.VILLAGE);
						for (StorySectionPacket nextStorySectionPacket : storySectionManager
								.getNextSections()) {
							if (eventCheckManager
									.checkMovedVillage(nextStorySectionPacket)) {
								storySectionManager
										.setNewStorySectionAndPlay(nextStorySectionPacket
												.getNextSectionNumber());
								break;
							}
						}
						break;
					case VILLAGE:
					case DUNGEON_ENTRANCE:
						positionManager.setCurrentPlace(PlaceEnum.MOVING);
						screenFactory.show(ScreenEnum.WORLD_MAP);
						break;
					case MOVING:
						if (positionManager.getCurrentNodeType() == PlaceEnum.VILLAGE) {
							positionManager.setCurrentPlace(PlaceEnum.VILLAGE);
							screenFactory.show(ScreenEnum.VILLAGE);
						} else if (positionManager.getCurrentNodeType() == PlaceEnum.DUNGEON) {
							positionManager.setCurrentPlace(PlaceEnum.DUNGEON);
							screenFactory.show(ScreenEnum.DUNGEON);
						}
					default:
						Gdx.app.log("GameUiStage", "Error : 현재 PlaceEnum정보가 없음");
				}
			}

		});
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
