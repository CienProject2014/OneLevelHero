package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.battle.Fight;
import com.mygdx.controller.ScreenController;
import com.mygdx.game.OneLevelHero;
import com.mygdx.resource.Assets;
import com.mygdx.util.ScreenEnum;

public class BattleScreen implements Screen {

	OneLevelHero game;
	Stage stage;
	Fight fight;
	TextButton fightButton;
	TextButton fleeButton;
	boolean a;

	public BattleScreen(OneLevelHero game) {
		this.game = game;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		stage = new Stage();
		fight = new Fight();

		Gdx.input.setInputProcessor(stage);
		Table uitable = new Table(Assets.skin);

		fightButton = new TextButton("전투", Assets.skin);
		fleeButton = new TextButton("도망", Assets.skin);

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
				Gdx.app.log("정보", "전투");
				fight.decideOutcome();
				if (fight.battleEnd)
					new ScreenController(ScreenEnum.VILLAGE);
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
				new ScreenController(ScreenEnum.VILLAGE);
				Gdx.app.log("정보", "전투에서 도망쳤습니다.");
			}
		});

		uitable.setFillParent(true);
		uitable.add(fightButton).width(320).height(100).top().right();
		uitable.add(fleeButton).width(320).height(100).bottom().left();

		stage.addActor(uitable);
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
