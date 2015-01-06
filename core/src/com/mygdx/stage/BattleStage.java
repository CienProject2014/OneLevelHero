package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.PlatformResourceManager;
import com.mygdx.model.LivingUnit;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

public class BattleStage extends Overlap2DStage {
	// Multi-Resolution 을 위한 클래스
	private PlatformResourceManager rm;

	// Table
	Table uiTable; // 화면 전체 테이블
	Table orderTable; // 순서를 나타내는 테이블

	// Value
	float maximumWidth = Assets.windowWidth * 0.0625f;
	float maximumHeight = Assets.windowHeight * 0.125f;

	// Button
	private SimpleButtonScript attackButton;
	private SimpleButtonScript skillButton;
	private SimpleButtonScript inventoryButton;
	private SimpleButtonScript escapeButton;

	// Battle controller
	private BattleManager controller;

	// Unit array
	private ArrayList<LivingUnit> units;
	private Queue<LivingUnit> orderedUnits;

	public BattleStage(PlatformResourceManager rm) {
		super(new StretchViewport(rm.stageWidth, rm.currentResolution.height));
		this.rm = rm;

		controller = new BattleManager();

		// Overlap2D로 만든 신(Scene)
		initSceneLoader(rm);
		sceneLoader.setResolution(rm.currentResolution.name);
		sceneLoader.loadScene("BattleUI");
		addActor(sceneLoader.getRoot());
		setButton();

		// 좌측 순서를 나타내는 테이블
		makeOrderedList();
		makeTable();

		addActor(uiTable);
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		//makeTable();
	}

	public void makeOrderedList() {
		units = new ArrayList<LivingUnit>(4);
		units.addAll(CurrentState.getInstance().getParty().getPartyList());
		units.add(CurrentState.getInstance().getCurrentDungeon().getMonster());

		Collections.sort(units);

		orderedUnits = new LinkedList<LivingUnit>(units);

		for (LivingUnit unit : units) {
			Gdx.app.log("BattleStage", "유닛이름: " + unit.getName());
		}
	}

	public LivingUnit getCurrentActor() {
		LivingUnit unit = orderedUnits.poll();
		orderedUnits.add(unit);
		return unit;
	}

	public void makeTable() {
		uiTable = new Table();
		uiTable.align(Align.left);

		orderTable = new Table();

		for (LivingUnit unit : orderedUnits) {
			Gdx.app.log("BattleStage", unit.getName());
			orderTable.add(new Image(unit.getFaceTexture()))
					.width(maximumWidth).height(maximumHeight);
			orderTable.row();
		}

		uiTable.add(orderTable);
		uiTable.setFillParent(true);
	}

	public void updateTable() {
		uiTable.clear();
		orderTable.clear();

		for (LivingUnit unit : orderedUnits) {
			Gdx.app.log("Unit name", unit.getName());
			orderTable.add(new Image(unit.getFaceTexture()))
					.width(maximumWidth).height(maximumHeight);
			orderTable.row();
		}

		uiTable.add(orderTable);
	}

	public void setButton() {
		attackButton = SimpleButtonScript.selfInit(sceneLoader.getRoot()
				.getCompositeById("attackButton"));
		skillButton = SimpleButtonScript.selfInit(sceneLoader.getRoot()
				.getCompositeById("skillButton"));
		inventoryButton = SimpleButtonScript.selfInit(sceneLoader.getRoot()
				.getCompositeById("inventoryButton"));
		escapeButton = SimpleButtonScript.selfInit(sceneLoader.getRoot()
				.getCompositeById("escapeButton"));

		addListener();
	}

	public void addListener() {
		attackButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "어택!");
				// 한 가지 시나리오를 예로 생각해보자.
				// 공격 버튼을 눌러 공격을 하면, 다음 턴엔 몬스터가 날 때릴 것이다.
				// 그렇다면 공격버튼이 눌린 것을 알아채고,
				// 몬스터의 턴을 실행해줄 클래스가 하나 필요하다.
				// 바로 BattleController가 해주면 된다.
				// 거기서 GameUiStage의 act도 실행해주면 된다.
				CurrentState
						.getInstance()
						.getParty()
						.getBattleMemberList()
						.get(0)
						.getStatus()
						.setHealthPoint(
								CurrentState.getInstance().getParty()
										.getBattleMemberList().get(0)
										.getStatus().getHealthPoint() - 10);

				controller.userAttack(getCurrentActor());
				updateTable();
			}
		});

		skillButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "스킬!");
				controller.userSkill(getCurrentActor(), "짱쎈공격");
				updateTable();
			}
		});

		inventoryButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "인벤토리!");

			}
		});

		escapeButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "도망!");
				new ScreenController(ScreenEnum.MOVING);
			}
		});
	}

	private Image getMonsterImage() {
		return new Image(CurrentState.getInstance().getCurrentDungeon()
				.getMonster().getFaceTexture());
	}
}
