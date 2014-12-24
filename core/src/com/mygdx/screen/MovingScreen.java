package com.mygdx.screen;

import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.BattleController;
import com.mygdx.controller.MovingController;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.CurrentManager;
import com.mygdx.resource.Assets;

public class MovingScreen implements Screen {

	Stage stage;
	TextButton goButton;
	TextButton backButton;

	Label movingLabel;
	Label roadName;
	Table table;

	int roadlength;
	int leftlength;
	public static int temp;
	boolean battled;

	Texture texture = new Texture(Gdx.files.internal("texture/justground.jpg"));

	Image background;

	MovingController controller;
	BattleController battle;

	String presentVil;
	String targetVil;

	String currentPosition;
	String currentDestination;
	String currentStartingpoint;

	public MovingScreen() {
		Gdx.app.log("DEBUG", "MovingScreen constructor");
		controller = new MovingController();
		battle = new BattleController();
		battled = false;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		movingLabel.setText(Assets.worldHashmap.get(currentDestination)
				.getName() + "까지" + leftlength);

		stage.draw();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void show() {
		Gdx.app.log("DEBUG", "MovingSceen show");
		currentDestination = CurrentManager.getInstance().getVillageInfo()
				.getCurrentDestination();
		currentPosition = CurrentManager.getInstance().getVillageInfo()
				.getCurrentPosition();
		currentStartingpoint = CurrentManager.getInstance().getVillageInfo()
				.getCurrentStarting();

		JSONObject roadJson = (JSONObject) Assets.jsonObjectMap.get(
				"worldmap_json").get("Road");

		JSONObject roadInfo = (JSONObject) roadJson.get(currentPosition);

		roadlength = Integer.parseInt((String) roadInfo.get("length"));
		leftlength = roadlength;
		if (battled) {
			leftlength = temp;
			battled = false;
		}

		stage = new Stage();
		table = new Table();
		table.setFillParent(true);
		goButton = new TextButton("Go", Assets.skin);
		backButton = new TextButton("Back", Assets.skin);
		movingLabel = new Label("Point", Assets.skin);
		roadName = new Label((String) roadInfo.get("name"), Assets.skin);

		movingLabel.setColor(0, 0, 0, 1);
		roadName.setColor(0, 0, 0, 1);

		Gdx.input.setInputProcessor(stage);

		background = new Image(texture);
		background.setSize(Assets.realWidth, Assets.realHeight);

		goButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				goForward();
			}
		});

		backButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				goBackward();
			}
		});

		controller.checkStage();

		table.add(roadName).top();
		table.row();
		table.add(movingLabel).top();
		table.row();
		table.add(goButton).expand().top().padTop(20);
		table.row();
		table.add(backButton).bottom().padBottom(20);

		stage.addActor(background);
		stage.addActor(table);

	}

	public void goForward() {
		Gdx.app.log("test", "goForward");
		if (leftlength > 0) {
			leftlength--;
			if (battle.isOccur()) {
				temp = leftlength;
				battled = true;
				battle.start();
			}
		}
		if (leftlength == 0) {

			CurrentManager
					.getInstance()
					.getVillageInfo()
					.setCurrentState(
							Assets.worldHashmap.get(currentDestination)
									.getType());

			CurrentManager.getInstance().getVillageInfo()
					.setCurrentPosition(currentDestination);

			CurrentManager.getInstance().getVillageInfo()
					.setCurrentDestination(null);

			CurrentManager.getInstance().getVillageInfo()
					.setCurrentStarting(null);

			String currentState = CurrentManager.getInstance().getVillageInfo()
					.getCurrentState();

			if (currentState.equals("village")) {

				new ScreenController(ScreenEnum.VILLAGE);

			} else if (currentState.equals("dungeon")) {

				new ScreenController(ScreenEnum.VILLAGE);

			} else if (currentState.equals("turningpoint")) {

				new ScreenController(ScreenEnum.WORLD_MAP);

			} else {

			}
		}

	}

	public void goBackward() {

		String temp;
		temp = currentDestination;
		currentDestination = currentStartingpoint;
		currentStartingpoint = temp;

		leftlength = roadlength - leftlength;
	}

	public static void setController() {

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		Gdx.app.log("DEBUG", "MovingSceen hide");
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		Gdx.app.log("DEBUG", "MovingSceen pause");
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		Gdx.app.log("DEBUG", "MovingSceen resume");
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		Gdx.app.log("DEBUG", "MovingSceen dispose");
	}

}
