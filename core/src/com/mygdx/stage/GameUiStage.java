package com.mygdx.stage;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.RewardManager;
import com.mygdx.manager.SaveManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.popup.GameObjectPopup;
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
	private SaveManager saveManager;
	@Autowired
	private StorySectionManager storySectionManager;

	@Autowired
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;

	private Table uiTable;
	private Table topTable;
	private StatusMessagePopup statusMessagePopup;
	private Stack<GameObjectPopup> alertMessage;

	private TextButton placeInfoButton;
	private TextButton timeInfoButton;

	private ImageButton backButton;
	private ImageButton questLogButton;
	private ImageButton helpButton;
	private ImageButton settingButton;
	private TextButtonStyle style;

	@Override
	public void act(float delta) {
		timeInfoButton.setText(timeManager.getTimeInfo());
		if (storySectionManager.getCurrentStorySection().getNextSections() != null
				&& storySectionManager.getCurrentStorySection().getNextSections().size() > 0) {
			timeInfoButton
					.setText(timeManager.getTimeInfo() + " / " + storySectionManager.getCurrentStorySectionNumber());
		}
	}

	public Stage makeStage() {
		super.makeStage();
		// 초기화
		uiConstantsMap = constantsAssets.getUiConstants("GameUiStage");
		uiTable = new Table();
		topTable = new Table(uiComponentAssets.getSkin());

		makeButton();
		addListener();
		makeTable();

		tableStack.add(uiTable);
		conditionalHidingBackButton();

		return this;
	}

	private void conditionalHidingBackButton() {
		if (!positionManager.getCurrentPositionType().equals(PositionEnum.SUB_NODE)) {
			backButton.setVisible(false);
		}
		if (positionManager.isInWorldMap()) {
			placeInfoButton.setVisible(false);
			timeInfoButton.setVisible(false);
			questLogButton.setVisible(false);
			helpButton.setVisible(false);
			settingButton.setVisible(false);
			backButton.setVisible(true);
		}
	}

	// 테이블 디자인
	public void makeTable() {
		topTable.setWidth(StaticAssets.BASE_WINDOW_WIDTH);
		float width = uiConstantsMap.get("TButtonWidthSmall");
		topTable.add(backButton).width(width).height(uiConstantsMap.get("TButtonHeightSmall"))
				.padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(placeInfoButton).width(uiConstantsMap.get("TButtonWidthLarge"))
				.height(uiConstantsMap.get("TButtonHeightLarge")).padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(timeInfoButton).width(uiConstantsMap.get("TButtonWidthLarge"))
				.height(uiConstantsMap.get("TButtonHeightLarge")).padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(questLogButton).width(uiConstantsMap.get("TButtonWidthSmall"))
				.height(uiConstantsMap.get("TButtonHeightSmall")).padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(helpButton).width(uiConstantsMap.get("TButtonWidthSmall"))
				.height(uiConstantsMap.get("TButtonHeightSmall")).padRight(uiConstantsMap.get("buttonPadRight"));
		topTable.add(settingButton).width(uiConstantsMap.get("TButtonWidthSmall"))
				.height(uiConstantsMap.get("TButtonHeightSmall"));
		uiTable.align(Align.top);
		uiTable.add(topTable).padLeft(uiConstantsMap.get("padLeft")).padTop(uiConstantsMap.get("padTop"));
	}

	public void makeButton() {
		style = new TextButtonStyle(atlasUiAssets.getAtlasUiFile("time_info_button"),
				atlasUiAssets.getAtlasUiFile("time_info_button"), atlasUiAssets.getAtlasUiFile("time_info_button"),
				uiComponentAssets.getFont());
		timeInfoButton = new TextButton(timeManager.getTimeInfo(), style);
		makePlaceInfoButton();
		backButton = new ImageButton(atlasUiAssets.getAtlasUiFile("back_button"),
				atlasUiAssets.getAtlasUiFile("back_toggle_button"));
		questLogButton = new ImageButton(atlasUiAssets.getAtlasUiFile("quest_log_button"),
				atlasUiAssets.getAtlasUiFile("quest_log_toggle_button"));
		helpButton = new ImageButton(atlasUiAssets.getAtlasUiFile("help_button"),
				atlasUiAssets.getAtlasUiFile("help_toggle_button"));
		settingButton = new ImageButton(atlasUiAssets.getAtlasUiFile("setting_button"),
				atlasUiAssets.getAtlasUiFile("setting_toggle_button"));
	}

	private void makePlaceInfoButton() {
		if (positionManager.getCurrentPositionType().equals(PositionEnum.NODE)) {
			placeInfoButton = new TextButton(positionManager.getCurrentNodeHanguelName(), style);
		} else if (positionManager.getCurrentPositionType().equals(PositionEnum.SUB_NODE)) {
			placeInfoButton = new TextButton(positionManager.getCurrentSubNodeHanguelName(), style);
		} else {
			placeInfoButton = new TextButton(positionManager.getCurrentNodeHanguelName(), style);
		}
	}

	// 리스너 할당
	public void addListener() {
		placeInfoButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				partyManager.healAllHero();
				return true;
			}
		});
		questLogButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				positionManager.setBeforePositionType(positionManager.getCurrentPositionType());
				screenFactory.show(ScreenEnum.LOG);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				Iterator<GameObjectPopup> alertMessageIterator = alertMessage.iterator();

				GameObjectPopup nextIterator = alertMessageIterator.next();
				addActor(nextIterator);
				nextIterator.setVisible(true);
			}
		});
		timeInfoButton.addListener(listenerFactory.getJumpSectionListener());
		helpButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
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
