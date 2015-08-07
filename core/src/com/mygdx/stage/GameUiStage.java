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
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.popup.AlertMessagePopup;
import com.mygdx.popup.MessagePopup;
import com.mygdx.popup.StatusMessagePopup;

public class GameUiStage extends BaseOneLevelStage {
	@Autowired
	private RewardManager rewardManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private EventManager eventManager;

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

	@Override
	public void act(float delta) {
		timeInfoButton.setText(timeManager.getDay() + "일째 "
				+ timeManager.getHour() + "시 " + timeManager.getMinute() + "분");
	}

	public Stage makeStage() {
		super.makeStage();
		// 초기화
		uiTable = new Table();
		topTable = new Table(uiComponentAssets.getSkin());

		makeButton();
		addListener();
		makeTable();

		tableStack.add(uiTable);
		conditionalHidingBackButton();

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

	private void conditionalHidingBackButton() {
		if (!positionManager.getCurrentPositionType().equals(
				PositionEnum.SUB_NODE)) {
			backButton.setVisible(false);
		}
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
		timeInfoButton = new TextButton(timeManager.getDay() + "일째 "
				+ timeManager.getHour() + "시 " + timeManager.getMinute() + "분",
				style);

		placeInfoButton = new TextButton("장소", style);

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
		placeInfoButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				battleManager.healAllHero();
				return true;
			}
		});
		questLogButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				if (positionManager.getCurrentPositionType().equals(
						PositionEnum.NODE)) {
					screenFactory.show(ScreenEnum.LOG);
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.WORLD_MAP);
			}
		});
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
		backButton.addListener(listenerFactory.getBackButtonListener());
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
