package com.mygdx.stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.resource.Assets;
import com.mygdx.util.CurrentManager;

public class BattleStage extends Stage {
	public Texture background;
	float viewportWidth, viewportHeight;
	float realWidth, realHeight;
	
	// 던전 정보 ---------------------
	JSONObject dungeonInfo;
	int monsterNumber;					// 몬스터 수
	int itemNumber;					// 아이템 수
	String monsterName[];			// 몬스터 이름
	String item[];				// 아이템 이름
	//-------------------------------
	
	// 테이블 ------------------------
	Table monsterTable;
	
	//-------------------------------
	
	ImageButton test;
	TextButton[] monster;
	int positionX[],
		positionY[];
	
	public BattleStage() {
		super();
		
		viewportWidth = this.getWidth();
		viewportHeight = this.getHeight();
		realHeight = Assets.realHeight;
		realWidth = Assets.realWidth;
		
		JSONObject actual = (JSONObject) Assets.dungeon_json.get("actual");
		dungeonInfo = (JSONObject) actual.get(CurrentManager.getInstance().getCurrentDungeon());
		
		initialize();	// 초기화
		setLayout();	// 디자인, addActor
		addListener();	// 리스너 할당
	}
	
	private void initialize() {
		
		// --------------------- Background //
		background = new Texture(Gdx.files.internal("village/blackwood.png"));
		Image backgroundImage = new Image(background);
		backgroundImage.setBounds(0, 0, viewportWidth, viewportHeight);
		addActor(backgroundImage);
		//---------------------- Background //
		
		// ------------------------ Monster //
		// 몬스터
		monsterTable = new Table(Assets.skin);
		monsterNumber = ((Long)dungeonInfo.get("monsterNumber")).intValue();
		monsterName = new String[monsterNumber];
		monster = new TextButton[monsterNumber];
		JSONArray monsters = (JSONArray) dungeonInfo.get("monster");
		for(int i=0; i<monsterNumber; i++) {
			monsterName[i] = (String) monsters.get(i);
			monster[i] = new TextButton(monsterName[i], Assets.skin);
		}
		//------------------------- Monster //
		
		// --------------------------- Item //
		// 아이템
		itemNumber =  ((Long)dungeonInfo.get("itemNumber")).intValue();
		item = new String[itemNumber];
		JSONArray items = (JSONArray) dungeonInfo.get("item");
		for(int i=0; i<itemNumber; i++)
			item[i] = (String) items.get(i);
		//---------------------------- Item //
	}
	
	private void setLayout() {
		@SuppressWarnings("unused")
		float factor;	// 화면 크기에 따른 비율 교정용 변수
		
		//------------------------- Monster //
		monsterTable.setFillParent(true);
		for(int i=0; i<monsterNumber; i++) {
			monsterTable.add(monster[i]).width(viewportWidth*2/7);
		}
		//------------------------- Monster //
		
		addActor(monsterTable);
	}
	
	private void addListener() {
		
	}
}
