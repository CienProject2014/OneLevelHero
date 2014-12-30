package com.mygdx.stage;

import java.util.List;

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
import com.mygdx.manager.EventManager;
import com.mygdx.model.Village;
import com.mygdx.model.Village.Building;
import com.mygdx.model.Village.NPC;
import com.mygdx.model.Village.Settable;
import com.mygdx.state.Assets;

public class VillageStage extends Stage {

	// JSONParser parser = new JSONParser();
	private String delimiter = "-";
	private String village_name;
	private int village_state;
	private int num_of_building;
	private int num_of_npc;
	private int num_of_exit;
	// ImageButton buildingbutton[];
	private BuildingImage buildingbutton[];
	private TextButton npcbutton[];
	private TextButton exitbutton;
	private Village village;

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

	public BuildingImage[] getBuildingbutton() {
		return buildingbutton;
	}

	public void setBuildingbutton(BuildingImage[] buildingbutton) {
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

	public class BuildingImage extends Image {

		//		private String name;
		private float posX;
		private float posY;
		private float width;
		private float height;

		public BuildingImage(Building buildinginfo) {

			super(villageAtlas.findRegion(buildinginfo.getKey()));

			setBuildingkey(buildinginfo.getKey());

			posX = (viewportwidth * (float) buildinginfo.getPositionX());
			posY = (viewportheight * (float) buildinginfo.getPositionX());

			width = (viewportwidth * (float) buildinginfo.getWidth());
			height = (viewportheight * (float) buildinginfo.getHeight());

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
		/* JSONObject villageData = (JSONObject) Assets.jsonFileMap
				.get("village_json")
				.getJsonFile()
				.get(CurrentState.getInstance().getVillageInfo()
						.getCurrentPosition());
						*/

		// 아직까진 블랙 우드밖에 없으므로 직접 B를 넣어주자
		village = Assets.villageMap.get("Blackwood");

		float ratio = (float) village.getRatio();

		viewportwidth = Assets.realWidth;
		viewportheight = viewportwidth * ratio;

		villageAtlas = new TextureAtlas(Gdx.files.internal("texture/village/"
				+ (String) village.getImagesource()));

		Texture background = new Texture(Gdx.files.internal("texture/village/"
				+ (String) village.getBackground()));
		Texture frontground = new Texture(Gdx.files.internal("texture/village/"
				+ (String) village.getFrontground()));

		Image backgroundImage = new Image(background);
		backgroundImage.setBounds(0, 0, viewportwidth, viewportheight);
		addActor(backgroundImage);

		Image frontgroundImage = new Image(frontground);
		frontgroundImage.setBounds(0, 0, viewportwidth, viewportheight);
		addActor(frontgroundImage);

		List<Building> buildingArray = village.getBuilding();
		List<NPC> npcArray = village.getNpc();
		List<Settable> exitArray = village.getExit();

		num_of_building = buildingArray.size();
		num_of_npc = npcArray.size();
		num_of_exit = exitArray.size();

		buildingbutton = new BuildingImage[num_of_building];
		npcbutton = new TextButton[num_of_npc];

		for (int i = 0; i < num_of_building; i++) {
			Building buildinginfo = buildingArray.get(i);

			buildingbutton[i] = new BuildingImage(buildinginfo);

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

				}
			});
			addActor(buildingbutton[i]);
		}

		for (int i = 0; i < num_of_npc; i++) {
			final NPC npcinfo = npcArray.get(i);

			npcbutton[i] = new TextButton(npcinfo.getName(), Assets.skin);

			float posX = (viewportwidth * (float) npcinfo.getPositionX());
			float posY = (viewportheight * (float) npcinfo.getPositionY());

			npcbutton[i].moveBy(posX, posY);

			//임시로 npcbutton[0]에만 이벤트를 적용함
			npcbutton[0].addListener(new InputListener() {

				@Override
				public boolean touchDown(InputEvent event, float x, float y,
						int pointer, int button) {

					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y,
						int pointer, int button) {

					// EventManager에 CurrentNpc정보를 전달한다.
					EventManager.setEventInfo(
							Assets.npcMap.get(npcinfo.getKey()), true);
					new ScreenController(ScreenEnum.GREETING);
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
