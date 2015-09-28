package com.mygdx.stage;

import java.util.HashMap;

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
import com.mygdx.currentState.CurrentInfo;
import com.mygdx.enums.PositionEnum.LocatePosition;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.SimpleTouchListener;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.MusicManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.popup.SettingPopup;

public class GameUiStage extends BaseOneLevelStage {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private MusicManager musicManager;
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private DungeonManager dungeonManager;
	private HashMap<String, Float> uiConstantsMap;

	private SettingPopup settingPopup;
	private Table uiTable;
	private Table topTable;
	private int adminCount;
	private TextButton placeInfoButton;
	private TextButton timeInfoButton;

	private ImageButton backButton;
	private ImageButton questLogButton;
	private ImageButton helpButton;
	private ImageButton settingButton;
	private TextButtonStyle style;

	@Override
	public void act(float delta) {
		showTimeInfoButton();
		showPlaceInfoButton();
	}

	private void showTimeInfoButton() {
		if (CurrentInfo.isAdminMode) {
			if (storySectionManager.getCurrentStorySection().getNextSections() != null
					&& storySectionManager.getCurrentStorySection().getNextSections().size() > 0) {
				timeInfoButton.setText(timeManager.getTimeInfo() + " / "
						+ storySectionManager.getCurrentStorySectionNumber());
			}
		} else {
			timeInfoButton.setText(timeManager.getTimeInfo());
		}
	}

	private void showPlaceInfoButton() {
		switch (positionManager.getCurrentLocatePositionType()) {
			case NODE :
				placeInfoButton.setText(positionManager.getCurrentNodeName() + CurrentInfo.getAdminMessage());
				break;
			case SUB_NODE :
				placeInfoButton.setText(positionManager.getCurrentSubNodeName() + CurrentInfo.getAdminMessage());
				break;
			case DUNGEON :
				placeInfoButton.setText(dungeonManager.getDungeonInfo().getCurrentFloor().getFloorName()
						+ CurrentInfo.getAdminMessage());
				break;
			default :
				placeInfoButton.setText(positionManager.getCurrentNodeName() + CurrentInfo.getAdminMessage());
				break;
		}
	}

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("GameUiStage");
		uiTable = new Table();
		topTable = new Table(uiComponentAssets.getSkin());
		settingPopup = new SettingPopup();
		makeButton();
		addListener();
		makeTable();

		tableStack.add(uiTable);
		conditionalHidingBackButton();

		return this;
	}
	private void conditionalHidingBackButton() {
		if (!positionManager.getCurrentLocatePositionType().equals(LocatePosition.SUB_NODE)) {
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
		timeInfoButton = new TextButton("", style);
		placeInfoButton = new TextButton("", style);
		backButton = new ImageButton(atlasUiAssets.getAtlasUiFile("back_button"),
				atlasUiAssets.getAtlasUiFile("back_toggle_button"));
		questLogButton = new ImageButton(atlasUiAssets.getAtlasUiFile("quest_log_button"),
				atlasUiAssets.getAtlasUiFile("quest_log_toggle_button"));
		helpButton = new ImageButton(atlasUiAssets.getAtlasUiFile("help_button"),
				atlasUiAssets.getAtlasUiFile("help_toggle_button"));
		settingButton = new ImageButton(atlasUiAssets.getAtlasUiFile("setting_button"),
				atlasUiAssets.getAtlasUiFile("setting_toggle_button"));
	}

	// 리스너 할당
	public void addListener() {
		placeInfoButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				soundManager.playClickSound();
				partyManager.healAllHero();
				return true;
			}
		});
		questLogButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				soundManager.playClickSound();
				setAdminCount(getAdminCount() + 1);
				if (getAdminCount() > 3) {
					CurrentInfo.changeAdminMode();
					setAdminCount(0);
				}
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
			}
		});
		timeInfoButton.addListener(listenerFactory.getJumpSectionListener());
		helpButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				soundManager.playClickSound();
				if (CurrentInfo.isAdminMode) {
					screenFactory.show(ScreenEnum.WORLD_MAP);
				}
			}

		});
		settingButton.addListener(new SimpleTouchListener() {
			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				soundManager.playClickSound();
				settingPopup.setAtlasUiAssets(atlasUiAssets);
				settingPopup.setListenerFactory(listenerFactory);
				settingPopup.setConstantsAssets(constantsAssets);
				settingPopup.setMusicManager(musicManager);
				settingPopup.setSoundManager(soundManager);
				settingPopup.initialize();
				addActor(settingPopup);
				settingPopup.setVisible(true);
			}

		});
		backButton.addListener(listenerFactory.getBackButtonListener());
	}

	@Override
	public void dispose() {
		super.dispose();
	}
	public void setAdminCount(int i) {
		this.adminCount = i;
	}

	public int getAdminCount() {
		return adminCount;
	}
}
