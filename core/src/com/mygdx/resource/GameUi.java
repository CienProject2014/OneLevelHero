package com.mygdx.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.game.OneLevelHero;
import com.mygdx.screen.BattleScreen;
import com.mygdx.screen.WorldMapScreen;

public class GameUi extends Table {
	OneLevelHero game;
	ImageButton downArrowButton;
	ImageButton bagButton;
	ImageButton helpButton;
	ImageButton optionButton;
	TextButton worldMapButton;
	TextButton leftTimeButton;
	TextButton battleButton;

	Table toptable;
	Table buttomtable;
	Table status1table;
	Table status2table;
	Table status3table;

	float realheight;
	float realwidth;

	public GameUi(OneLevelHero game) {
		// 초기화
		this.game = game;
		Assets.gameUiButtonLoad();

		toptable = new Table(Assets.skin);
		buttomtable = new Table(Assets.skin);

		realheight = Assets.realHeight;
		realwidth = Assets.realWidth;

		TextButtonStyle style = new TextButtonStyle(Assets.nameAndTime,
				Assets.nameAndTime, Assets.nameAndTime, Assets.font);
		downArrowButton = new ImageButton(Assets.downArrowButton,
				Assets.downArrowButton);
		bagButton = new ImageButton(Assets.bagButton, Assets.bagButton);
		worldMapButton = new TextButton("worldMap", style);
		leftTimeButton = new TextButton("12h30m", style);
		helpButton = new ImageButton(Assets.helpButton, Assets.helpButton);
		optionButton = new ImageButton(Assets.optionButton, Assets.optionButton);

		battleButton = new TextButton("Battle", Assets.skin);

		addListener();
		makeTable();
	}

	// 테이블 디자인
	public void makeTable() {

		this.setFillParent(true);

		toptable.add(downArrowButton).expand().width(realwidth / 8)
				.height(realwidth / 12).top().left();
		toptable.add(bagButton).width(realwidth / 8).height(realwidth / 12)
				.top();
		toptable.add(worldMapButton).width(realwidth / 4)
				.height(realwidth / 12).top();
		toptable.add(leftTimeButton).width(realwidth / 4)
				.height(realwidth / 12).top();
		toptable.add(helpButton).width(realwidth / 8).height(realwidth / 12)
				.top();
		toptable.add(optionButton).width(realwidth / 8).height(realwidth / 12)
				.top();

		buttomtable.add(status1table).bottom().left();
		buttomtable.add(status2table).bottom();
		buttomtable.add(status3table).bottom();

		this.add(toptable).expand().top();
		this.row();
		this.add(buttomtable).bottom();
	}

	// 리스너 할당
	public void addListener() {

		bagButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				Gdx.app.log("정보", "inventoryPopUp창이 나타납니다.");
			}
		});
		optionButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				Gdx.app.log("정보", "OptionScreen이 나타납니다.");
			}
		});

		downArrowButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				Gdx.app.log("정보", "minimap창이 나타납니다.");
			}
		});

		battleButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				game.setScreen(new BattleScreen(game));
				Gdx.app.log("정보", "전투가 시작됩니다");
			}
		});
		worldMapButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				game.setScreen(new WorldMapScreen(game));
			}
		});
	}
}
