package com.mygdx.resource;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.OneLevelHero;
import com.mygdx.screen.BattleScreen;

public class GameUi extends Table {
	OneLevelHero game;
	ImageButton optionButton;
	ImageButton minimapButton;
	ImageButton inventoryButton;
	TextButton statusButton1;
	TextButton statusButton2;
	TextButton statusButton3;
	TextButton battleButton;

	Table toptable;
	Table buttomtable;

	float realheight;
	float realwidth;

	public GameUi(OneLevelHero game) {
		// 초기화
		this.game = game;
		toptable = new Table(Assets.skin);
		buttomtable = new Table(Assets.skin);

		realheight = Assets.realHeight;
		realwidth = Assets.realWidth;

		optionButton = new ImageButton(Assets.ibuttond, Assets.ibuttonu);
		minimapButton = new ImageButton(Assets.ibuttond, Assets.ibuttonu);
		inventoryButton = new ImageButton(Assets.ibuttond, Assets.ibuttonu);
		statusButton1 = new TextButton("status1", Assets.skin);
		statusButton2 = new TextButton("status2", Assets.skin);
		statusButton3 = new TextButton("status3", Assets.skin);
		battleButton = new TextButton("Battle", Assets.skin);

		addListener();
		makeTable();
	}

	// 테이블 디자인
	public void makeTable() {

		this.setFillParent(true);

		toptable.add(minimapButton).expand().width(100).height(50).top().left();
		toptable.add(inventoryButton).width(100).height(50).top().left();
		toptable.add(optionButton).width(100).height(50).top().left();
		toptable.add(battleButton).width(100).height(50).top().right();

		buttomtable.add(statusButton1).width(realwidth / 3)
				.height(realheight / 7).bottom().left();
		buttomtable.add(statusButton2).width(realwidth / 3)
				.height(realheight / 7).bottom().left();
		buttomtable.add(statusButton3).width(realwidth / 3)
				.height(realheight / 7).bottom().left();

		this.add(toptable).expand().top();
		this.row();
		this.add(buttomtable).bottom();
	}

	// 리스너 할당
	public void addListener() {

		// 버튼 동작
		statusButton1.addListener(new InputListener() {
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				// TODO Auto-generated method stub
				return true;
			}

			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("정보", "Status1의 상태가 나타납니다.");
			}
		});
		statusButton2.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("정보", "Status2의 상태가 나타납니다.");
			}
		});
		statusButton3.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("정보", "Status3의 상태가 나타납니다.");
			}
		});
		inventoryButton.addListener(new InputListener() {

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

		minimapButton.addListener(new InputListener() {

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
		}); // test

	}
}
