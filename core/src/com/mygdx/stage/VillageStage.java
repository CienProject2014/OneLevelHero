package com.mygdx.stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.CurrentManager;
import com.mygdx.resource.Assets;

public class VillageStage extends Stage {

	// JSONParser parser = new JSONParser();
	private String delimiter = "-";
	private String village_name;
	private int village_state;
	private int num_of_building;
	private int num_of_npc;
	private int num_of_exit;
	// ImageButton buildingbutton[];
	private Building buildingbutton[];
	private TextButton npcbutton[];
	private TextButton exitbutton;

	public TextButton sift_button;
	private TextureAtlas villageAtlas;

	private float viewportwidth;
	private float viewportheight;

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public String getVillage_name() {
		return village_name;
	}

	public void setVillage_name(String village_name) {
		this.village_name = village_name;
	}

	public int getVillage_state() {
		return village_state;
	}

	public void setVillage_state(int village_state) {
		this.village_state = village_state;
	}

	public int getNum_of_building() {
		return num_of_building;
	}

	public void setNum_of_building(int num_of_building) {
		this.num_of_building = num_of_building;
	}

	public int getNum_of_npc() {
		return num_of_npc;
	}

	public void setNum_of_npc(int num_of_npc) {
		this.num_of_npc = num_of_npc;
	}

	public int getNum_of_exit() {
		return num_of_exit;
	}

	public void setNum_of_exit(int num_of_exit) {
		this.num_of_exit = num_of_exit;
	}

	public Building[] getBuildingbutton() {
		return buildingbutton;
	}

	public void setBuildingbutton(Building[] buildingbutton) {
		this.buildingbutton = buildingbutton;
	}

	public TextButton[] getNpcbutton() {
		return npcbutton;
	}

	public void setNpcbutton(TextButton[] npcbutton) {
		this.npcbutton = npcbutton;
	}

	public TextButton getExitbutton() {
		return exitbutton;
	}

	public void setExitbutton(TextButton exitbutton) {
		this.exitbutton = exitbutton;
	}

	public TextButton getSift_button() {
		return sift_button;
	}

	public void setSift_button(TextButton sift_button) {
		this.sift_button = sift_button;
	}

	public TextureAtlas getVillageAtlas() {
		return villageAtlas;
	}

	public void setVillageAtlas(TextureAtlas villageAtlas) {
		this.villageAtlas = villageAtlas;
	}

	public float getViewportwidth() {
		return viewportwidth;
	}

	public void setViewportwidth(float viewportwidth) {
		this.viewportwidth = viewportwidth;
	}

	public float getViewportheight() {
		return viewportheight;
	}

	public void setViewportheight(float viewportheight) {
		this.viewportheight = viewportheight;
	}

	public Camera getCamera() {
		return camera;
	}

	public void setCamera(Camera camera) {
		this.camera = camera;
	}

	public String getCurrentState() {
		return currentState;
	}

	public void setCurrentState(String currentState) {
		this.currentState = currentState;
	}

	private Camera camera;
	private String currentState = "down";

	private class Building extends Image {

		private String name;
		private float posX;
		private float posY;
		private float width;
		private float height;

		public Building(JSONObject buildinginfo) {

			super(villageAtlas.findRegion((String) buildinginfo.get("key")));

			name = (String) buildinginfo.get("name");
			setBuildingkey((String) buildinginfo.get("key"));

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

		public void setBuildingkey(String buildingkey) {
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

			//임시로 npcbutton[0]에만 이벤트를 적용함
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
					// 1. CurrentManager에 CurrentNpc정보를 전달한다.
					CurrentManager.getInstance().setCurrentNPC(waiji);
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
