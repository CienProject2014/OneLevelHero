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
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.CurrentManager;
import com.mygdx.resource.Assets;

public class WorldStage extends Stage {

	private Image background;
	private worldNode villages[];
	private worldNode dungeons[];
	private worldNode turningpoints[];

	private int worldmapsize = 1;

	private int villageNumber;
	private int dungeonNumber;
	private int turningporintNumber;

	private TextButtonStyle villageStyle;
	private TextButtonStyle dungeonStyle;
	private TextButtonStyle turningpointStyle;

	private TextureRegionDrawable arrow;

	private int villageCount;
	private int dungeonCount;
	private int turningpointCount;

	private JSONArray worldData;

	private int nodeSize;

	private Camera camera;

	public class worldNode extends TextButton {

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
			this.setType((String) jsonObject.get("type"));
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

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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

		background.setBounds(0, 0, 1920 * worldmapsize, 1080 * worldmapsize);

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

		villageStyle = new TextButtonStyle(villagecircle, villagecircle,
				villagecircle, Assets.font);
		dungeonStyle = new TextButtonStyle(dungeoncircle, dungeoncircle,
				dungeoncircle, Assets.font);
		turningpointStyle = new TextButtonStyle(turningpointarrow,
				turningpointarrow, turningpointarrow, Assets.font);

		worldData = (JSONArray) Assets.jsonFileMap.get("worldmap_json")
				.getJsonFile().get("Worldmap");

		Assets.villageHashmap = new HashMap<String, String>();

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

			Assets.villageHashmap.put((String) temp.get("key"),
					(String) temp.get("name"));

			if (type.equals("village")) {

				villages[villageCount] = new worldNode("default", villageStyle);
				villages[villageCount].setOption(temp);

				Assets.worldHashmap.put(villages[villageCount].key,
						villages[villageCount]);
				addActor(villages[villageCount]);
				villageCount++;

			} else if (type.equals("dungeon")) {
				dungeons[dungeonCount] = new worldNode("default", dungeonStyle);
				dungeons[dungeonCount].setOption(temp);
				Assets.worldHashmap.put(dungeons[dungeonCount].key,
						dungeons[dungeonCount]);
				addActor(dungeons[dungeonCount]);
				dungeonCount++;
			} else {
				turningpoints[turningpointCount] = new worldNode("default",
						turningpointStyle);
				turningpoints[turningpointCount].setOption(temp);
				Assets.worldHashmap.put(turningpoints[turningpointCount].key,
						turningpoints[turningpointCount]);
				turningpointCount++;
			}
		}
	}

	// 현재 위치를 화살표로 표시해줌
	private void setCurrentPosition() {

		worldNode temp = Assets.worldHashmap.get(CurrentManager.getInstance()
				.getVillageInfo().getCurrentPosition());

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

					CurrentManager.getInstance().getVillageInfo()
							.setCurrentDestination(temp.destination);
					CurrentManager
							.getInstance()
							.getVillageInfo()
							.setCurrentStarting(
									CurrentManager.getInstance()
											.getVillageInfo()
											.getCurrentPosition());
					CurrentManager.getInstance().getVillageInfo()
							.setCurrentPosition(temp.roadkey);
					CurrentManager.getInstance().getVillageInfo()
							.setCurrentState("road");

					new ScreenController(ScreenEnum.MOVING);

					return true;
				}
			});

			addActor(roadnode[i]);
		}

		camera.translate(temp.posX * worldmapsize - Assets.realWidth / 2,
				temp.posY * worldmapsize - Assets.realHeight / 2, 0);
		camera.update();

		temp.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {

				worldNode temp;
				temp = (worldNode) event.getListenerActor();

				if (!temp.type.equals("turningpoint")) {
					new ScreenController(ScreenEnum.VILLAGE);
				}

				return true;
			}
		});

		addActor(temp);
	}
}
