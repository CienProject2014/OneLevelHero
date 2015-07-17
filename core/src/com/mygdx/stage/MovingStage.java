package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.MovingManager;

public class MovingStage extends BaseOneLevelStage {
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;

	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("MovingStage");

	private Stage stage;

	private ImageButton goButton;
	private ImageButton backButton;
	private Texture texture = new Texture(
			Gdx.files.internal("texture/justground.jpg"));
	private Image background;

	public Stage makeStage() {
		super.makeStage();

		stage = new Stage();
		// 초기화

		background = new Image(texture);
		background.setSize(StaticAssets.BASE_WINDOW_WIDTH,
				StaticAssets.BASE_WINDOW_HEIGHT);

		makeButton();
		addListener();
		Gdx.input.setInputProcessor(stage);

		tableStack.add(background);
		tableStack.add(makeTable());
		return this;
	}

	// 테이블 디자인
	public Table makeTable() {

		Table moveTable = new Table();

		moveTable.add(goButton).width(uiConstantsMap.get("ButtonWidth"))
				.height(uiConstantsMap.get("ButtonHeight"));
		moveTable.row();
		moveTable.add(backButton).width(uiConstantsMap.get("ButtonWidth"))
				.height(uiConstantsMap.get("ButtonWidth"));
		moveTable.right().bottom();
		return moveTable;
	}

	public void makeButton() {
		goButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture(
				"texture/worldmap/fieldui_front.png"))));
		backButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture(
				"texture/worldmap/fieldui_back.png"))));

	}

	// 리스너 할당
	public void addListener() {
		goButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				movingManager.goForward();
			}
		});

		backButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				movingManager.goBackward();
			}
		});
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}