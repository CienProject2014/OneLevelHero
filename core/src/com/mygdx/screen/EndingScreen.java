package com.mygdx.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.TouchListener;

public class EndingScreen extends BaseScreen {
	private Stage stage;
	private TextButton backButton;

	public EndingScreen() {
	}

	@Override
	public void render(float delta) {
		super.render(delta);

		Gdx.app.log("Ending", "Ending");
		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		Gdx.input.setInputProcessor(stage);
		Table table = new Table(assets.skin);

		backButton = new TextButton("Back", assets.skin);

		backButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.COLLETION);
			}
		}));

		table.setFillParent(true);
		table.add(backButton).bottom();
		table.row();

		stage.addActor(table);
	}
}
