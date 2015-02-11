package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.PlatformResourceManager;
import com.mygdx.model.Hero;
import com.mygdx.model.LivingUnit;
import com.mygdx.model.Monster;
import com.mygdx.state.Assets;
import com.uwsoft.editor.renderer.Overlap2DStage;
import com.uwsoft.editor.renderer.script.SimpleButtonScript;

@Component
public class BattleStage extends Overlap2DStage {
	@Autowired
	private Assets assets;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private MovingInfo movingInfo;
	@Autowired
	private PartyInfo partyInfo;

	// Table
	Table uiTable; // 화면 전체 테이블
	Table orderTable; // 순서를 나타내는 테이블

	// Value
	float maximumWidth;
	float maximumHeight;

	// Button
	private SimpleButtonScript attackButton;
	private SimpleButtonScript skillButton;
	private SimpleButtonScript inventoryButton;
	private SimpleButtonScript escapeButton;

	// Battle controller
	@Autowired
	private BattleManager battleManager;
	private Monster monster;

	// Unit array
	private ArrayList<LivingUnit> units;
	private Queue<LivingUnit> orderedUnits;
	
	// Orthographic Camera
	private OrthographicCamera cam;
	
	// Trigger
	private boolean monsterTrigger;

	public BattleStage() {
		Gdx.app.debug("BattleStage", "Constructor() call");
	}
	
	@Override
	public void act(float delta) {
		if (monsterTrigger){
			LivingUnit actor = getCurrentActor();
			if (!(actor instanceof Monster)){
				// 일어날 수 없는 시나리오
				// 몬스터의 턴이 아니라면 monsterTrigger가 true여서는 안된다.
				return;
			}
			battleManager.monsterAttack();
			updateTable();
			
			monsterTrigger = false;
		}
		
		super.act(delta);
	}

	public LivingUnit getCurrentActor() {
		LivingUnit unit = orderedUnits.poll();
		orderedUnits.add(unit);
		return unit;
	}
	
	public LivingUnit getNextActor() {
		LivingUnit unit = orderedUnits.peek();
		return unit;
	}

	public Stage makeStage() {
		cam = new OrthographicCamera(1920f, 1080f);
		cam.position.set(1920/ 2.0f, 1080/2.0f, 0);
		getViewport().setCamera(cam);
		
		Gdx.app.debug("BattleStage", "makeStage(Rm rm)");
		maximumWidth = assets.windowWidth * 0.0625f;
		maximumHeight = assets.windowHeight * 0.125f;
		monster = movingInfo.getSelectedMonster();

		// Overlap2D로 만든 신(Scene)
		initSceneLoader();
		sceneLoader.loadScene("battle_ui_scene");
		addActor(sceneLoader.getRoot());
		setButton();

		// 좌측 순서를 나타내는 테이블
		makeOrderedList();
		makeTable();

		return this;
	}

	public void makeOrderedList() {
		units = new ArrayList<LivingUnit>(4);
		units.addAll(partyInfo.getPartyList());
		units.add(monster);

		Collections.sort(units);

		orderedUnits = new LinkedList<LivingUnit>(units);

		for (LivingUnit unit : units) {
			Gdx.app.log("BattleStage", "유닛이름: " + unit.getName());
		}
	}

	public void makeTable() {
		uiTable = new Table();
		uiTable.align(Align.left);
		uiTable.setWidth(maximumWidth);
		uiTable.setY(maximumHeight);
		
		orderTable = new Table();

		updateTable();

		uiTable.setFillParent(true);
		
		addActor(uiTable);
	}
	
	public void updateTable() {
		uiTable.clear();
		orderTable.clear();

		for (LivingUnit unit : orderedUnits) {
			Gdx.app.log("Unit name", unit.getName());
			orderTable.add(new Image(unit.getBattleTexture()))
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
				
				LivingUnit actor = getCurrentActor();
				
				if (!(actor instanceof Hero)){
					// 일어날 수 없는 시나리오
					// 만약 몬스터의 턴이라면 이 이벤트가 호출되지 않아야 한다.
					return;
				}
				
				battleManager.userAttack(actor);
				updateTable();
				
				if (getNextActor() instanceof Monster){
					monsterTrigger = true;
				}
			}
		});

		skillButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("BattleStage", "스킬!");
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
				screenFactory.show(ScreenEnum.MOVING);
			}
		});
	}
}
