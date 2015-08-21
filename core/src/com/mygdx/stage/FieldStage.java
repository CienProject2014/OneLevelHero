package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.GoBackwardFieldButtonListener;
import com.mygdx.listener.GoForwardFieldButtonListener;
import com.mygdx.manager.FieldManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TextureManager;

public class FieldStage extends BaseOneLevelStage {
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private TextureManager textureManager;

	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("MovingStage");
	private Stage stage;
	private Table outerTable;
	private Label movingLabel;
	private ImageButton goForwardFieldButton;
	private ImageButton goBackwardFieldButton;

	public Stage makeStage() {
		Gdx.app.log("FieldStage", "FieldType - " + fieldManager.getFieldType());
		super.makeStage();
		movingLabel = new Label("Point", uiComponentAssets.getSkin());
		movingLabel.setColor(Color.WHITE);

		stage = new Stage();
		// 초기화

		outerTable = new Table();
		outerTable.setBackground(getBackgroundTRD(), false);
		outerTable.top(); // table을 위로 정렬

		makeButton();
		addListener();
		Gdx.input.setInputProcessor(stage);

		tableStack.add(outerTable);
		tableStack.add(movingLabel);
		tableStack.add(makeTable());
		return this;
	}
	@Override
	public void act(float delta) {
		super.act(delta);
		movingLabel.setText(String.format("%s까지%d", fieldManager.getDestinationNode(),
				fieldManager.getLeftFieldLength()));
		outerTable.setBackground(getBackgroundTRD(), false);
	}
	private TextureRegionDrawable getBackgroundTRD() {
		return new TextureRegionDrawable(new TextureRegion(textureManager.getBackgroundTexture(fieldManager
				.getFieldType().toString())));
	}

	// 테이블 디자인
	public Table makeTable() {

		Table moveTable = new Table();

		moveTable.add(goForwardFieldButton).width(uiConstantsMap.get("ButtonWidth"))
				.height(uiConstantsMap.get("ButtonHeight"));
		moveTable.row();
		moveTable.add(goBackwardFieldButton).width(uiConstantsMap.get("ButtonWidth"))
				.height(uiConstantsMap.get("ButtonWidth"));
		moveTable.right().bottom();
		return moveTable;
	}

	public void makeButton() {
		goForwardFieldButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture(
				"texture/worldmap/fieldui_front.png"))));
		goBackwardFieldButton = new ImageButton(new SpriteDrawable(new Sprite(new Texture(
				"texture/worldmap/fieldui_back.png"))));

	}

	// 리스너 할당
	public void addListener() {
		GoForwardFieldButtonListener goForwardListener = listenerFactory.getGoForwardFieldButtonListener();
		GoBackwardFieldButtonListener goBackwardListener = listenerFactory.getGoBackwardFieldButtonListener();
		goForwardFieldButton.addListener(goForwardListener);
		goBackwardFieldButton.addListener(goBackwardListener);
	}

	@Override
	public void dispose() {
		super.dispose();
	}
}