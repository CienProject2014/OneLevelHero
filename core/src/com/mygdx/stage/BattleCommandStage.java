package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.enums.BattleCommandEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.SoundManager;
import com.mygdx.model.unit.Unit;
import com.mygdx.screen.BattleScreen;

public class BattleCommandStage extends BaseOneLevelStage {
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private SoundManager soundManager;
	@Autowired
	private ListenerFactory listenerFactory;
	private Table rMenuTable = new Table();
	private ArrayList<ImageButton> rMenuButtonList;
	private HashMap<String, Float> uiConstantsMap;
	// RMenuButton
	private ImageButton attackButton, skillButton, itemButton, defenseButton, waitButton, escapeButton;
	private int check = 0;

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("BattleStage");
		tableStack.add(makeBattleCommandTable());
		return this;
	}

	private Table makeBattleCommandTable() {
		Table uiTable = new Table();
		rMenuTable = makeRMenuTable();
		uiTable.right().bottom();
		uiTable.padRight(40).padBottom(20);
		uiTable.add(rMenuTable);
		addBattleCommandButtonListener();
		return uiTable;
	}

	private Table makeRMenuTable() {
		makeRButton();
		rMenuButtonList = new ArrayList<>();
		rMenuButtonList.add(attackButton);
		rMenuButtonList.add(skillButton);
		rMenuButtonList.add(itemButton);
		rMenuButtonList.add(defenseButton);
		rMenuButtonList.add(waitButton);
		rMenuButtonList.add(escapeButton);
		battleManager.setrMenuButtonList(rMenuButtonList);
		for (int i = 0; i < rMenuButtonList.size(); i++) {
			if (i == 0) {
				rMenuTable.add(rMenuButtonList.get(i)).width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight")).padTop(uiConstantsMap.get("RMenuTablePadTop"))
						.padBottom(uiConstantsMap.get("RButtonSpace")).expandX();
				rMenuTable.row();
			} else {
				rMenuTable.add(rMenuButtonList.get(i)).width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight")).padBottom(uiConstantsMap.get("RButtonSpace"));
				rMenuTable.row();
			}
		}
		battleManager.setRMenuTable(rMenuTable);
		return rMenuTable;
	}

	private void makeRButton() {
		// 이미지 추가
		attackButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_attack"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_attack"));
		skillButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_skill"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_skill"));
		itemButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_item"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_item"));
		defenseButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_defense"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_defense"));
		waitButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_wait"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_wait"));
		escapeButton = new ImageButton(atlasUiAssets.getAtlasUiFile("battleui_rb_escape"),
				atlasUiAssets.getAtlasUiFile("battleui_rbac_escape"));
	}

	private void setDarkButton(ImageButton button) {
		for (ImageButton buttons : rMenuButtonList) {
			buttons.setVisible(true);
			buttons.setTouchable(Touchable.enabled);
		}
		button.setVisible(false);
		button.setTouchable(Touchable.disabled);
	}

	private void addBattleCommandButtonListener() {
		// 클릭시 발동
		attackButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setCurrentUsingCommand(BattleCommandEnum.GENERAL_ATTACK);
				battleManager.setStateByCurrentUsingCommand();
				setDarkButton(attackButton);
				battleManager.updateGauge(BattleCommandEnum.GENERAL_ATTACK.getCostGauge());
				battleManager.setShowGrid(true);
			}
		});

		skillButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setStateByCurrentUsingCommand();
				setDarkButton(skillButton);
				battleManager.setSkill(true);
				BattleScreen.showSkillStage = true;
			}
		});

		itemButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setStateByCurrentUsingCommand();
				battleManager.setCurrentUsingCommand(BattleCommandEnum.USE_ITEM);
				setDarkButton(itemButton);
				battleManager.updateGauge(BattleCommandEnum.USE_ITEM.getCostGauge());
				BattleScreen.showItemStage = true;
			}
		});

		defenseButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setStateByCurrentUsingCommand();
				battleManager.setCurrentUsingCommand(BattleCommandEnum.DEFEND);
				setDarkButton(defenseButton);
				battleManager.updateGauge(BattleCommandEnum.DEFEND.getCostGauge());
				battleManager.endTurn();
			}
		});

		waitButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setStateByCurrentUsingCommand();
				battleManager.setCurrentUsingCommand(BattleCommandEnum.WAIT);
				setDarkButton(waitButton);
				battleManager.doWaitCommand();
				battleManager.endTurn();
			}
		});

		escapeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setStateByCurrentUsingCommand();
				battleManager.setCurrentUsingCommand(BattleCommandEnum.RUN_AWAY);
				setDarkButton(escapeButton);
				battleManager.updateGauge(BattleCommandEnum.RUN_AWAY.getCostGauge());
				battleManager.gameObjectPopup.setAtlasUiAssets(atlasUiAssets);
				battleManager.gameObjectPopup.setListenerFactory(listenerFactory);
				battleManager.gameObjectPopup.setConstantsAssets(constantsAssets);
				checkRunAway();
				battleManager.gameObjectPopup.initialize("도망 치시겠습니까?" + "\n" + "도망칠 확률" + battleManager.getRunPercent()
						+ "%입니다");
				addActor(battleManager.gameObjectPopup);
				battleManager.gameObjectPopup.setVisible(true);
			}
		});

	}

	private void checkRunAway() {
		int minSpeed = 500;
		for (Unit unit : partyManager.getBattleMemberList()) {
			if (minSpeed >= unit.getStatus().getSpeed()) {
				minSpeed = unit.getStatus().getSpeed();
			}
		}
		check = 50 + battleManager.getSelectedMonster().getStatus().getSpeed() - minSpeed;
		if (check < 5) {
			check = 5;
		}
		battleManager.setRunPercent(check);
	}
}
