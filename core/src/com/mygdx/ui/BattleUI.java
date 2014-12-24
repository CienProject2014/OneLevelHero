package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.resource.Assets;

public class BattleUI extends Stage {
	TextButton fightButton;
	TextButton fleeButton;
	
	Table uiTable;
	
	Image[] character;
	Table toptable;
	Table bottomtable;

	Table[] statusbartable;
	Table[] charatertable;

	StatusBarUi[] hpbar;
	StatusBarUi[] expbar;
	StatusBarUi[] turnbar;

	float realheight;
	float realwidth;
	
	public BattleUI() {
		fightButton = new TextButton("전투", Assets.skin);
		fleeButton = new TextButton("도망", Assets.skin);
		
		uiTable = new Table();
		realheight = Assets.realHeight;
		realwidth = Assets.realWidth;
		hpbar = new StatusBarUi[3];
		expbar = new StatusBarUi[3];
		turnbar = new StatusBarUi[3];
		character = new Image[3];
		statusbartable = new Table[3];
		charatertable = new Table[3];

		for (int i = 0; i < 3; i++) {
			hpbar[i] = new StatusBarUi("hp", 0f, 100f, 1f, false, Assets.skin);
			expbar[i] = new StatusBarUi("exp", 0f, 100f, 1f, false, Assets.skin);
			turnbar[i] = new StatusBarUi("turn", 0f, 100f, 1f, false,
					Assets.skin);
			statusbartable[i] = new Table(Assets.skin);
			charatertable[i] = new Table(Assets.skin);
			character[i] = new Image(new Texture(Gdx.files.internal("texture/char" + (i + 1) + ".jpg")));
		}

		addListener();
		makeTable();
		addActor(uiTable);
		
	}
	
	public void makeTable() {
		uiTable.setFillParent(true);
		uiTable.add(fightButton).width(320).height(100).top().right();
		uiTable.add(fleeButton).width(320).height(100).bottom().left();
		
		for (int i = 0; i < 3; i++) {
			charatertable[i].add(character[i]).width(realwidth / 4).height(realheight / 4);
			statusbartable[i].add(hpbar[i]).width(realwidth / 12).height(realheight / 12).bottom();
			statusbartable[i].row();
			statusbartable[i].add(expbar[i]).width(realwidth / 12).height(realheight / 12).bottom();
			statusbartable[i].row();
			statusbartable[i].add(turnbar[i]).width(realwidth / 12).height(realheight / 12).bottom();
			bottomtable.add(charatertable[i]);
			bottomtable.add(statusbartable[i]);
		}

		uiTable.add(toptable).expand().top().height((realheight) / 12);
		uiTable.row();
		uiTable.add(bottomtable).bottom();
	}
	
	public void addListener() {
		fightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
	
				// TODO Auto-generated method stub
				return true;
			}
	
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			
			}
		});
		fleeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
	
				// TODO Auto-generated method stub
				return true;
			}
	
			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				new ScreenController(ScreenEnum.MOVING);
				Gdx.app.log("정보", "전투에서 도망쳤습니다.");
			}
		});
	}
}
