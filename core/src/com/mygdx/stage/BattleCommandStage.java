package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.controller.BattleCommandController;
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
	@Autowired
	private BattleCommandController battleCommandController;
	private Table battleCommandButtonTable = new Table();
	private ArrayList<ImageButton> battleCommandButtonList;
	private HashMap<String, Float> uiConstantsMap;
	// RMenuButton
	private ImageButton attackButton, skillButton, itemButton, defenseButton, waitButton, escapeButton;
	private int check = 0;

	public void act(float delta) {
		if (battleManager.getBattleFlag().isMonsterTurnEnd()) {
			for (ImageButton battleCommandButtons : battleCommandButtonList) {
				battleCommandButtons.setVisible(true);
				battleCommandButtons.setTouchable(Touchable.enabled);
			}
			battleCommandButtonTable.setVisible(true);
			battleCommandButtonTable.addAction(Actions.moveTo(1720, 15));
			battleCommandButtonTable.addAction(Actions.moveTo(1720, 15, 1));
		} else {
			for (ImageButton battleCommandButtons : battleCommandButtonList) {
				battleCommandButtons.setVisible(false);
				battleCommandButtons.setTouchable(Touchable.disabled);
			}
			battleCommandButtonTable.setVisible(false);
		}
	}

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("BattleStage");
		tableStack.add(makeBattleCommandTable());
		return this;
	}

	private Table makeBattleCommandTable() {
		Table uiTable = new Table();
		battleCommandButtonTable = battleCommandButtonTable();
		uiTable.right().bottom();
		uiTable.padRight(40).padBottom(20);
		uiTable.add(battleCommandButtonTable);
		addBattleCommandButtonListener();
		return uiTable;
	}

	private Table battleCommandButtonTable() {
		makebattleCommandButtonTable();
		battleCommandButtonList = new ArrayList<>();
		battleCommandButtonList.add(attackButton);
		battleCommandButtonList.add(skillButton);
		battleCommandButtonList.add(itemButton);
		battleCommandButtonList.add(defenseButton);
		battleCommandButtonList.add(waitButton);
		battleCommandButtonList.add(escapeButton);
		battleManager.setBattleCommandButtonList(battleCommandButtonList);
		for (int i = 0; i < battleCommandButtonList.size(); i++) {
			if (i == 0) {
				battleCommandButtonTable.add(battleCommandButtonList.get(i)).width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight")).padTop(uiConstantsMap.get("RMenuTablePadTop"))
						.padBottom(uiConstantsMap.get("RButtonSpace")).expandX();
				battleCommandButtonTable.row();
			} else {
				battleCommandButtonTable.add(battleCommandButtonList.get(i)).width(uiConstantsMap.get("RButtonWidth"))
						.height(uiConstantsMap.get("RButtonHeight")).padBottom(uiConstantsMap.get("RButtonSpace"));
				battleCommandButtonTable.row();
			}
		}
		battleManager.setBattleCommandButtonTable(battleCommandButtonTable);
		return battleCommandButtonTable;
	}

	private void makebattleCommandButtonTable() {
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
		for (ImageButton buttons : battleCommandButtonList) {
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
				battleManager.setBattleCommand(BattleCommandEnum.GENERAL_ATTACK);;
				setDarkButton(attackButton);
			}
		});

		skillButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				setDarkButton(skillButton);
				battleManager.setUsingSkill(true);
				BattleScreen.showSkillStage = true;
			}
		});

		itemButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setBattleCommand(BattleCommandEnum.USE_ITEM);
				setDarkButton(itemButton);
				BattleScreen.showItemStage = true;
			}
		});

		defenseButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setBattleCommand(BattleCommandEnum.DEFEND);
				setDarkButton(defenseButton);
				battleManager.handleTurnEnd();
			}
		});

		waitButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setBattleCommand(BattleCommandEnum.WAIT);

				setDarkButton(waitButton);
				battleCommandController.doBattleCommand(battleManager.getCurrentActor(), null, null);
				battleManager.handleTurnEnd();
			}
		});

		escapeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				soundManager.playClickSound();
				battleManager.setBattleCommand(BattleCommandEnum.RUN_AWAY);
				setDarkButton(escapeButton);
				battleManager.getGameObjectPopup().setAtlasUiAssets(atlasUiAssets);
				battleManager.getGameObjectPopup().setListenerFactory(listenerFactory);
				battleManager.getGameObjectPopup().setConstantsAssets(constantsAssets);
				checkRunAway();
				battleManager.getGameObjectPopup().initialize(
						"도망 치시겠습니까?" + "\n" + "도망칠 확률" + battleManager.getRunPercent() + "%입니다");
				addActor(battleManager.getGameObjectPopup());
				battleManager.getGameObjectPopup().setVisible(true);
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
		check = 50 + battleManager.getCurrentMonster().getStatus().getSpeed() - minSpeed;
		if (check < 5) {
			check = 5;
		}
		battleManager.setRunPercent(check);
	}
}
