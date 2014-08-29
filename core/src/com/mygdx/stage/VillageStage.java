package com.mygdx.stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.EventTrigger;
import com.mygdx.resource.Assets;
import com.mygdx.util.CurrentManager;
import com.mygdx.util.EventManager;

public class VillageStage extends Stage {

	// JSONParser parser = new JSONParser();
	String delimiter = "-";
	String village_name;
	int village_state;
	int num_of_building;
	int num_of_npc;
	int num_of_exit;
	// ImageButton buildingbutton[];
	Building buildingbutton[];
	TextButton npcbutton[];
	TextButton exitbutton;

	public TextButton sift_button;
	private TextureAtlas villageAtlas;

	float viewportwidth;
	float viewportheight;

	private Camera camera;
	private String currentState = "down";

	private class Building extends Image {

		private String name;
		private String buildingkey;
		private float posX;
		private float posY;
		private float width;
		private float height;

		public Building() {
		}

		public Building(TextureRegion region) {
			super(region);
		}

		public Building(JSONObject buildinginfo) {

			super(villageAtlas.findRegion((String) buildinginfo.get("key")));

			name = (String) buildinginfo.get("name");
			buildingkey = (String) buildinginfo.get("key");

			posX = viewportwidth
					* Float.parseFloat((String) buildinginfo.get("positionX"));
			posY = viewportheight
					* Float.parseFloat((String) buildinginfo.get("positionY"));

			width = viewportwidth
					* Float.parseFloat((String) buildinginfo.get("width"));
			height = viewportheight
					* Float.parseFloat((String) buildinginfo.get("height"));

			setBounds(posX - width / 2, posY - height / 2, width, height);

		}
	}

	public VillageStage() {
		super();
	}

	public VillageStage(String key) {
		super();
		keyParser(key);
		village_setter();
	}

	// 마을 정보에 맞게 스테이지 형성
	private void village_setter() {

		OrthographicCamera cam = new OrthographicCamera(Assets.realWidth,
				Assets.realHeight);
		// cam.translate(100, 300);
		cam.position.set(Assets.realWidth / 2, Assets.realHeight * 0.25f, 0);
		getViewport().setCamera(cam);

		camera = getViewport().getCamera();

		// 마을 제이슨 완성 시 이걸로
		JSONObject villageData = (JSONObject) Assets.village_json
				.get(CurrentManager.getInstance().getCurrentPosition());
		// 아직까진 블랙 우드밖에 없으므로 직접 B를 넣어주자
		villageData = (JSONObject) Assets.village_json.get("B");

		float ratio = Float.parseFloat((String) villageData.get("ratio"));

		viewportwidth = Assets.realWidth;
		viewportheight = viewportwidth * ratio;

		villageAtlas = new TextureAtlas(Gdx.files.internal("texture/village/"
				+ (String) villageData.get("imagesource")));

		Texture background = new Texture(Gdx.files.internal("texture/village/"
				+ (String) villageData.get("background")));
		Texture frontground = new Texture(Gdx.files.internal("texture/village/"
				+ (String) villageData.get("frontground")));

		Image backgroundImage = new Image(background);
		backgroundImage.setBounds(0, 0, viewportwidth, viewportheight);
		addActor(backgroundImage);

		Image frontgroundImage = new Image(frontground);
		frontgroundImage.setBounds(0, 0, viewportwidth, viewportheight);
		addActor(frontgroundImage);

		JSONArray buildingArray = (JSONArray) villageData.get("building");
		JSONArray npcArray = (JSONArray) villageData.get("npc");
		JSONArray exitArray = (JSONArray) villageData.get("exit");

		num_of_building = buildingArray.size();
		num_of_npc = npcArray.size();
		num_of_exit = exitArray.size();

		buildingbutton = new Building[num_of_building];
		npcbutton = new TextButton[num_of_npc];

		for (int i = 0; i < num_of_building; i++) {
			JSONObject buildinginfo = (JSONObject) buildingArray.get(i);

			buildingbutton[i] = new Building(buildinginfo);

			buildingbutton[i].addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					System.out.println(((Building) event.getListenerActor()).name);
				}
			});
			addActor(buildingbutton[i]);
		}

		for (int i = 0; i < num_of_npc; i++) {
			JSONObject npcinfo = (JSONObject) npcArray.get(i);

			npcbutton[i] = new TextButton((String) npcinfo.get("name"),
					Assets.skin);

			float posX = viewportwidth
					* Float.parseFloat((String) npcinfo.get("positionX"));
			float posY = viewportheight
					* Float.parseFloat((String) npcinfo.get("positionY"));

			npcbutton[i].moveBy(posX, posY);

			npcbutton[0].addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {
					// TODO Auto-generated method stub
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {
					EventManager.getInstance().setEventCode("B-waiji-0",
							EventTypeEnum.SELECT);
					EventTrigger.getInstance().setNpcEvent();
					new ScreenController(ScreenEnum.EVENT);
				}
			});
			addActor(npcbutton[i]);
		}

		// 전환 버튼 기능은 빌리지 스크린에서 구현
		sift_button = new TextButton("전환", Assets.skin);
		sift_button.center();

		sift_button.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				if (currentState.equals("down")) {
					currentState = "moveup";
				} else if (currentState.equals("up")) {
					currentState = "movedown";
					System.out.println(currentState);
				}

				event.getListenerActor().setVisible(false);
			}
		});
		addActor(sift_button);

	}

	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		village_name = temp[0];
		village_state = Integer.parseInt(temp[1]);
	}

	@Override
	public void draw() {
		moveCam();
		super.draw();
	}

	private void moveCam() {
		int movingSpeed = 6;
		checkBound();
		if (currentState.equals("moveup")) {
			camera.translate(0, movingSpeed, 0);
			sift_button.moveBy(0, movingSpeed);

		} else if (currentState.equals("movedown")) {
			camera.translate(0, -movingSpeed, 0);
			sift_button.moveBy(0, -movingSpeed);
		} else {

		}
	}

	private void checkBound() {
		if (camera.position.y > (viewportheight - Assets.realHeight / 2)) {
			camera.position.y = viewportheight - Assets.realHeight / 2;
			currentState = "up";
			sift_button.setVisible(true);

		} else if (camera.position.y < Assets.realHeight * 0.25f) {
			camera.position.y = Assets.realHeight * 0.25f;
			currentState = "down";
			sift_button.setVisible(true);
		}
	}
}
