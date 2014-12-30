package com.mygdx.stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.battle.Fight;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.model.Monster;
import com.mygdx.screen.MovingScreen;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class BattleStage extends Stage {
	private float viewportWidth, viewportHeight;
	private float realWidth, realHeight;

	private Fight fight;

	public boolean isFight;

	public Texture background;
	private Image backgroundImage;

	// 던전 정보 ---------------------
	private JSONObject dungeonInfo;
	//-------------------------------

	// 유닛 정보 ---------------------
	private int monsterNumber; // 몬스터 수
	private String monsterName[]; // 몬스터 이름
	//	private int itemNumber;					// 아이템 수
	//	private String item[];					// 아이템 이름
	private Monster monsters[]; // 몬스터 객체
	//	private Hero heros[];					// 영웅 객체
	//-------------------------------

	// 테이블 ------------------------
	private Table monsterTable;
	private Table monster1, monster2, monster3;
	private Table selTable;
	//-------------------------------

	// 버튼 --------------------------
	private TextButton fightButton;
	private TextButton fleeButton;
	private ImageButton test;
	private int positionX[], positionY[];

	//라벨 -------------------------
	private Label fightLabel;

	//-------------------------------

	public BattleStage() {
		super();
	}

	public BattleStage(String dungeonID) {
		super();

		fight = new Fight(dungeonID);
		viewportWidth = this.getWidth();
		viewportHeight = this.getHeight();
		realHeight = Assets.realHeight;
		realWidth = Assets.realWidth;

		JSONObject actual = (JSONObject) Assets.jsonObjectMap.get(
				"dungeon_json").get("actual");
		dungeonInfo = (JSONObject) actual.get(CurrentState.getInstance()
				.getVillageInfo().getCurrentDungeon());

		initialize(); // 초기화
		setFightLayout(); // 디자인, addActor
		addListener(); // 리스너 할당
	}

	private void initialize() {
		isFight = false; // 싸우기 전 화면을 보여준다

		// --------------------- Background //
		background = new Texture(
				Gdx.files
						.internal("texture/unit/monster/griffith_background.png"));
		backgroundImage = new Image(background);
		backgroundImage.setBounds(0, 0, viewportWidth, viewportHeight);

		// ------------------------- Select //
		selTable = new Table(Assets.skin);
		fightLabel = new Label("몬스터와 조우했다!", Assets.skin);
		fightButton = new TextButton("싸운다", Assets.skin);
		fleeButton = new TextButton("도망친다", Assets.skin);

		//--------------------------- monster Table //
		monsterTable = new Table(Assets.skin);
		Texture griffith_texture = new Texture(
				Gdx.files.internal("texture/unit/monster/griffith_unit.png"));
		TextureRegionDrawable griffith = new TextureRegionDrawable(
				new TextureRegion(griffith_texture));
		monster1 = new Table(Assets.skin);
		monster1.setBackground(griffith);

		monster2 = new Table(Assets.skin);
		monster2.setBackground(griffith);

		monster3 = new Table(Assets.skin);
		monster3.setBackground(griffith);

		// ------------------------ Monster //
		// 몬스터
		monsterNumber = ((Long) dungeonInfo.get("monsterNumber")).intValue();
		monsterName = new String[monsterNumber];
		monsters = new Monster[monsterNumber];
		JSONArray monsterArray = (JSONArray) dungeonInfo.get("monster");
		for (int i = 0; i < monsterNumber; i++) {
			monsterName[i] = (String) monsterArray.get(i);
			//monsters[i] = monsterLoader.load(monsterName[i]); // 몬스터 로드
		}

		// 아이템을 몬스터가 갖고 있게 할지, 던전에서 갖고 있을지...
		/*		// --------------------------- Item //
				// 아이템
				itemNumber =  ((Long)dungeonInfo.get("itemNumber")).intValue();
				item = new String[itemNumber];
				JSONArray items = (JSONArray) dungeonInfo.get("item");
				for(int i=0; i<itemNumber; i++)
					item[i] = (String) items.get(i);
				//---------------------------- Item //*/
	}

	private void setFightLayout() {
		@SuppressWarnings("unused")
		float factor; // 화면 크기에 따른 비율 교정용 변수

		//------------------------- Monster //

		if (!isFight) {
			// ------------------------- Select //
			addActor(backgroundImage);
			selTable.setFillParent(true);
			selTable.add(fightLabel);
			selTable.row();
			selTable.add(fightButton);
			selTable.add(fleeButton);
			addActor(selTable);
			//-------------------------- Select //
		} else {
			// ------------------------ Monster //
			background = new Texture(
					Gdx.files.internal("texture/battle/forest.png"));
			backgroundImage = new Image(background);
			backgroundImage.setBounds(0, 0, viewportWidth, viewportHeight);
			addActor(backgroundImage);
			monsterTable.setFillParent(true);
			monsterTable.pad(150);
			monsterTable.moveBy(0, 100);
			monsterTable.add(monster1);
			monsterTable.add(monster2);
			monsterTable.add(monster3);
			monsterTable.row();
			monster3.setColor(Color.DARK_GRAY);
			Texture enemybg = new Texture(
					Gdx.files.internal("texture/enemybg2.png"));
			Image enemybgImg = new Image(enemybg);
			Image enemybgImg2 = new Image(enemybg);
			Image enemybgImg3 = new Image(enemybg);

			monsterTable.add(enemybgImg);
			monsterTable.add(enemybgImg2);
			monsterTable.add(enemybgImg3);
			addActor(monsterTable);
		}
	}

	private void addListener() {
		fightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				clear();
				isFight = true;
				setFightLayout(); //refresh해준다.
			}
		});
		fleeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				MovingScreen.temp++; // 임시. 캡슐화 고려 필요
				new ScreenController(ScreenEnum.MOVING);
			}
		});

	}
}
