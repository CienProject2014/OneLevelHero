package com.mygdx.stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.battle.Fight;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.CurrentManager;
import com.mygdx.model.Monster;
import com.mygdx.resource.Assets;
import com.mygdx.screen.MovingScreen;

public class BattleStage extends Stage {
	float viewportWidth, viewportHeight;
	float realWidth, realHeight;

	Fight fight;

	public boolean isFight;

	public Texture background;
	Image backgroundImage;

	// 던전 정보 ---------------------
	JSONObject dungeonInfo;
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
	Table monsterTable;
	Table monster1, monster2, monster3;
	Table selTable;
	//-------------------------------

	// 버튼 --------------------------
	TextButton fightButton;
	TextButton fleeButton;
	ImageButton test;
	int positionX[], positionY[];

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

		JSONObject actual = (JSONObject) Assets.dungeon_json.get("actual");
		dungeonInfo = (JSONObject) actual.get(CurrentManager.getInstance()
				.getVillageInfo().getCurrentDungeon());

		initialize(); // 초기화
		setLayout(); // 디자인, addActor
		addListener(); // 리스너 할당
	}

	private void initialize() {
		isFight = false;

		// --------------------- Background //
		background = new Texture(Gdx.files.internal("village/blackwood.png"));
		backgroundImage = new Image(background);
		backgroundImage.setBounds(0, 0, viewportWidth, viewportHeight);

		// ------------------------- Select //
		selTable = new Table(Assets.skin);
		fightButton = new TextButton("싸운다", Assets.skin);
		fleeButton = new TextButton("도망친다", Assets.skin);

		//--------------------------- Table //
		monsterTable = new Table(Assets.skin);
		monster1 = new Table(Assets.skin);
		monster1.setBackground(Assets.start_after);
		monster2 = new Table(Assets.skin);
		monster2.setBackground(Assets.option_after);
		monster3 = new Table(Assets.skin);
		monster3.setBackground(Assets.extra_after);

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

	private void setLayout() {
		@SuppressWarnings("unused")
		float factor; // 화면 크기에 따른 비율 교정용 변수

		addActor(backgroundImage);

		// ------------------------ Monster //
		monsterTable.setFillParent(true);
		monsterTable.add(monster1);
		monsterTable.add(monster2);
		monsterTable.add(monster3);
		addActor(monsterTable);
		//------------------------- Monster //

		if (!isFight) {
			// ------------------------- Select //
			selTable.setFillParent(true);
			selTable.add(fightButton);
			selTable.add(fleeButton);
			addActor(selTable);
			fightButton.moveBy(0, 50);
			fleeButton.moveBy(0, 50);
			//-------------------------- Select //
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
				setLayout();
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
