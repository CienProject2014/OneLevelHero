package com.mygdx.stage;

import java.util.HashMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.controller.ScreenController;
import com.mygdx.resource.Assets;
import com.mygdx.util.CurrentManager;
import com.mygdx.util.ScreenEnum;
import com.mygdx.util.ScreenManager;

public class WorldStage extends Stage {

	private Image background;
	private worldNode villages[];
	private worldNode dungeons[];
	private worldNode turningpoints[];

	private int worldmapsize = 3;

	private int villageNumber;
	private int dungeonNumber;
	private int turningporintNumber;

	private TextButtonStyle villageStyle;
	private TextButtonStyle dungeonStyle;

	private TextureRegionDrawable arrow;

	private int villageCount;
	private int dungeonCount;
	private int turningpointCount;

	private JSONArray worldData;

	private int nodeSize;

	private Camera camera;

	private HashMap<String, worldNode> worldHashmap = new HashMap<String, worldNode>();

	private class worldNode extends TextButton {

		private String key;
		private String name;
		private String type;
		private String position;
		private JSONArray connection;

		private int posX;
		private int posY;

		public worldNode(String text, Skin skin) {
			super(text, skin);
			// TODO Auto-generated constructor stub
		}

		public worldNode(String text, TextButtonStyle style) {
			super(text, style);
			// TODO Auto-generated constructor stub
		}

		private void setOption(JSONObject jsonObject) {
			this.type = (String) jsonObject.get("type");
			this.name = (String) jsonObject.get("name");
			this.position = (String) jsonObject.get("position");
			this.key = (String) jsonObject.get("key");
			this.connection = (JSONArray) jsonObject.get("connection");

			setting();
		}

		private void setting() {

			setText(name);

			String temppos[] = position.split("-");

			posX = Integer.parseInt(temppos[0]);
			posY = 1080 - Integer.parseInt(temppos[1]);

			setBounds(posX * worldmapsize - nodeSize / 2, posY * worldmapsize
					- nodeSize / 2, nodeSize, nodeSize);

		}
	}

	private class RoadNode extends Image {

		private String roadkey;
		private String destination;
		private int direction;

		public RoadNode(TextureRegionDrawable arrow) {
			// TODO Auto-generated constructor stub
			super(arrow);
			setSize(nodeSize / 2, nodeSize / 2);
			setOrigin(this.getWidth() / 2, this.getHeight() / 2);
		}

		public void setOption(JSONObject object) {
			// TODO Auto-generated method stub

			roadkey = (String) object.get("roadkey");
			direction = Integer.parseInt((String) object.get("direction"));
			destination = (String) object.get("destination");

			setRotation((float) direction);

			moveBy((float) nodeSize / 1.2f
					* (float) Math.cos((double) direction * 3.151592 / 180.0),
					(float) nodeSize
							/ 1.2f
							* (float) Math
									.sin((double) direction * 3.151592 / 180.0));
		}

		public void setPosition(int positionX, int positionY) {
			setPosition((float) positionX * worldmapsize - this.getWidth() / 2,
					(float) positionY * worldmapsize - this.getHeight() / 2);
		}
	}

	public WorldStage() {
		super();
		camera = getViewport().getCamera();

		world_setter();
		setCurrentPosition();

	}

	// 월드맵 정보에 맞게 스테이지 형성
	private void world_setter() {

		background = new Image(new Texture(
				Gdx.files.internal("texture/worldmap/WorldMap.png")));

		background.setBounds(0, 0, 1920 * 3, 1080 * 3);

		nodeSize = 90 * worldmapsize;

		addActor(background);

		TextureRegionDrawable villagecircle = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files
						.internal("texture/worldmap/villagecircle.png"))));

		TextureRegionDrawable dungeoncircle = new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files
						.internal("texture/worldmap/dungeoncircle.png"))));

		villageStyle = new TextButtonStyle(villagecircle, villagecircle,
				villagecircle, Assets.font);
		dungeonStyle = new TextButtonStyle(dungeoncircle, dungeoncircle,
				dungeoncircle, Assets.font);

		worldData = (JSONArray) Assets.worldmap_json.get("Worldmap");

		for (int i = 0; i < worldData.size(); i++) {
			JSONObject temp = (JSONObject) worldData.get(i);
			String type = (String) temp.get("type");

			if (type.equals("village")) {
				villageNumber++;
			} else if (type.equals("dungeon")) {
				dungeonNumber++;
			} else {
				turningporintNumber++;
			}
		}

		villages = new worldNode[villageNumber];
		dungeons = new worldNode[dungeonNumber];
		turningpoints = new worldNode[turningporintNumber];

		for (int i = 0; i < worldData.size(); i++) {

			JSONObject temp = (JSONObject) worldData.get(i);
			String type = (String) temp.get("type");

			if (type.equals("village")) {

				villages[villageCount] = new worldNode("default", villageStyle);
				villages[villageCount].setOption(temp);

				worldHashmap.put(villages[villageCount].key,
						villages[villageCount]);
				addActor(villages[villageCount]);
				villageCount++;

			} else if (type.equals("dungeon")) {
				dungeons[dungeonCount] = new worldNode("default", dungeonStyle);
				dungeons[dungeonCount].setOption(temp);
				worldHashmap.put(dungeons[dungeonCount].key,
						dungeons[dungeonCount]);
				addActor(dungeons[dungeonCount]);
				dungeonCount++;
			} else {
				turningpoints[turningpointCount] = new worldNode("default",
						Assets.skin);
				turningpoints[turningpointCount].setOption(temp);
				worldHashmap.put(turningpoints[turningpointCount].key,
						turningpoints[turningpointCount]);
				turningpointCount++;
			}
		}

	}

	// 현재 위치를 화살표로 표시해줌
	private void setCurrentPosition() {

		worldNode temp = worldHashmap
				.get(ScreenManager.getGame().currentManager
						.getCurrentPosition());

		int connectionNum = temp.connection.size();

		JSONArray connections = new JSONArray();

		connections = temp.connection;

		arrow = new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("texture/worldmap/arrow.png"))));

		RoadNode roadnode[] = new RoadNode[connectionNum];

		for (int i = 0; i < connectionNum; i++) {
			roadnode[i] = new RoadNode(arrow);

			roadnode[i].setPosition(temp.posX, temp.posY);
			roadnode[i].setOption((JSONObject) connections.get(i));

			roadnode[i].addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {

					RoadNode temp;
					temp = (RoadNode) event.getListenerActor();
					CurrentManager currentTemp = ScreenManager.getGame().currentManager;

					currentTemp.setCurrentDestination(temp.destination);
					currentTemp.setCurrentStarting(currentTemp
							.getCurrentPosition());
					currentTemp.setCurrentPosition(temp.roadkey);
					currentTemp.setCurrentState("road");

					new ScreenController(ScreenEnum.MOVING);

					return true;
				}
			});

			addActor(roadnode[i]);
		}

		camera.translate(temp.posX * 3 - Assets.realWidth / 2, temp.posY * 3
				- Assets.realHeight / 2, 0);
		camera.update();

		temp.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				new ScreenController(ScreenEnum.VILLAGE);

				return true;
			}
		});

		addActor(temp);
	}
}
