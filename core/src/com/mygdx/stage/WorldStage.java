package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.model.WorldMapInfo;
import com.mygdx.model.WorldMapInfo.NodeInfo;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class WorldStage extends Stage {

	private Image background;

	private int worldmapsize = 1;

	private int villageInfoSize;
	private int dungeonSize;
	private int turningPointSize;

	private TextureRegionDrawable arrow;

	private WorldMapInfo worldInfo;

	private int nodeSize;

	private Camera camera;

	public WorldStage() {
		camera = getViewport().getCamera();

		setWorldMap();
		setCurrentPosition();

	}

	// 월드맵 정보에 맞게 스테이지 형성
	private void setWorldMap() {

		background = new Image(new Texture(
				Gdx.files.internal("texture/worldmap/WorldMap.png")));

		nodeSize = 90 * worldmapsize;

		addActor(background);

		TextureRegionDrawable villagecircle = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files
						.internal("texture/worldmap/villagecircle.png"))));

		TextureRegionDrawable dungeoncircle = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files
						.internal("texture/worldmap/dungeoncircle.png"))));

		TextureRegionDrawable turningpointarrow = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files
						.internal("texture/worldmap/turningpoint.png"))));

		worldInfo = Assets.worldInfo;

	}

	// 현재 위치를 화살표로 표시해줌
	private void setCurrentPosition() {

		NodeInfo currentNodeInfo = worldInfo.getNodeInfo().get(
				worldInfo.getNodeInfo().get(
						CurrentState.getInstance().getVillageInfo()
								.getCurrentPosition()));

		int connectionNum = currentNodeInfo.getConnection().size();

		arrow = new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("texture/worldmap/arrow.png"))));

		camera.translate(currentNodeInfo.getPositionX() * worldmapsize
				- Assets.windowWidth / 2, currentNodeInfo.getPositionY()
				* worldmapsize - Assets.windowHeight / 2, 0);
		camera.update();

		TextureRegionDrawable villagecircle = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files
						.internal("texture/worldmap/villagecircle.png"))));

		TextButton currentNodeButton = new TextButton("currentNodeButton",
				new TextButtonStyle(villagecircle, villagecircle,
						villagecircle, Assets.font));

		currentNodeButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				new ScreenController(ScreenEnum.VILLAGE);

				return true;
			}
		});

		addActor(currentNodeButton);
	}
}
