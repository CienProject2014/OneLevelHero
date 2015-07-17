package com.mygdx.screen;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.MovingManager;

public class MovingScreen extends BaseScreen {
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	private Stage stage;
	private TextButton goButton;
	private TextButton backButton;
	private Label movingLabel;
	private Table table;
	private Texture texture = new Texture(Gdx.files.internal("texture/justground.jpg"));
	private Image background;

	@Override
	public void render(float delta) {
		super.render(delta);

		movingLabel.setText(movingInfo.getDestinationNode() + "까지" + movingInfo.getLeftRoadLength());
		stage.draw();
	}

	@Override
	public void show() {
		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		goButton = new TextButton("Go", uiComponentAssets.getSkin());
		backButton = new TextButton("Back", uiComponentAssets.getSkin());
		movingLabel = new Label("Point", uiComponentAssets.getSkin());
		movingLabel.setColor(0, 0, 0, 1);

		Gdx.input.setInputProcessor(stage);

		background = new Image(texture);
		background.setSize(StaticAssets.BASE_WINDOW_WIDTH, StaticAssets.BASE_WINDOW_HEIGHT);

		goButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				movingManager.goForward();
			}
		});

		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				movingManager.goBackward();
			}
		});
		table.add(movingLabel).top();
		table.row();
		table.add(goButton).expand().top().padTop(20);
		table.row();
		table.add(backButton).bottom().padBottom(20);

		stage.addActor(background);
		stage.addActor(table);

		musicManager.setMovingMusicAndPlay();
	}
}
