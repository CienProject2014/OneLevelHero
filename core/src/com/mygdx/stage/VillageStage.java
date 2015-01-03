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
import com.mygdx.state.Assets;

public class VillageStage extends Stage {

	// JSONParser parser = new JSONParser();
	private String delimiter = "-";
	private String villageName;

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Village getVillage() {
		return village;
	}

	public void setVillage(Village village) {
		this.village = village;
	}

	public TextButton getSiftButton() {
		return siftButton;
	}

	public void setSiftButton(TextButton siftButton) {
		this.siftButton = siftButton;
	}

	// ImageButton buildingbutton[];
	private BuildingImage buildingButton[];
	private TextButton npcButton[];
	private TextButton exitButton;
	private Village village;

	public TextButton siftButton;
	private TextureAtlas villageAtlas;

	private float viewportWidth;
	private float viewportHeight;

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public BuildingImage[] getBuildingbutton() {
		return buildingButton;
	}

	public void setBuildingbutton(BuildingImage[] buildingbutton) {
		this.buildingButton = buildingbutton;
	}

	public TextureAtlas getVillageAtlas() {
		return villageAtlas;
	}

	public void setVillageAtlas(TextureAtlas villageAtlas) {
		this.villageAtlas = villageAtlas;
	}

	public Camera getCamera() {
		return camera;
	}

	public BuildingImage[] getBuildingButton() {
		return buildingButton;
	}

	public void setBuildingButton(BuildingImage[] buildingButton) {
		this.buildingButton = buildingButton;
	}

	public TextButton[] getNpcButton() {
		return npcButton;
	}

	public void setNpcButton(TextButton[] npcButton) {
		this.npcButton = npcButton;
	}

	public TextButton getExitButton() {
		return exitButton;
	}

	public void setExitButton(TextButton exitButton) {
		this.exitButton = exitButton;
	}

	public float getViewportWidth() {
		return viewportWidth;
	}

	public void setViewportWidth(float viewportWidth) {
		this.viewportWidth = viewportWidth;
	}

	public float getViewportHeight() {
		return viewportHeight;
	}

	public void setViewportHeight(float viewportHeight) {
		this.viewportHeight = viewportHeight;
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

			posX = viewportWidth * (float) buildinginfo.getPositionX();
			posY = viewportHeight * (float) buildinginfo.getPositionY();

			width = viewportWidth * (float) buildinginfo.getWidth();
			height = viewportHeight * (float) buildinginfo.getHeight();

			setBounds(posX - width / 2, posY - height / 2, width, height);
		}

		public void setBuildingkey(String buildingkey) {
		}
	}

	public VillageStage() {
		super();
	}

	public VillageStage(String villageName) {
		super();
		this.villageName = villageName;
		setVillage();
	}

	// 마을 정보에 맞게 스테이지 형성
	private void setVillage() {

		OrthographicCamera cam = new OrthographicCamera(Assets.windowWidth,
				Assets.windowHeight);
		// cam.translate(100, 300);
		cam.position
				.set(Assets.windowWidth / 2, Assets.windowHeight * 0.25f, 0);
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
		village = (Village) Assets.jsonObjectMap.get("village_json").get(
				"Blackwood");

		if (village == null)
			Gdx.app.log("asdf", "asdf");

		float ratio = (float) village.getRatio();

		viewportWidth = Assets.windowWidth;
		viewportHeight = viewportWidth * ratio;

		villageAtlas = new TextureAtlas(Gdx.files.internal("texture/village/"
				+ (String) village.getImagesource()));

		Texture background = new Texture(Gdx.files.internal("texture/village/"
				+ (String) village.getBackground()));
		Texture frontground = new Texture(Gdx.files.internal("texture/village/"
				+ (String) village.getFrontground()));

		Image backgroundImage = new Image(background);
		backgroundImage.setBounds(0, 0, viewportWidth, viewportHeight);
		addActor(backgroundImage);

		Image frontgroundImage = new Image(frontground);
		frontgroundImage.setBounds(0, 0, viewportWidth, viewportHeight);
		addActor(frontgroundImage);

		List<Building> buildingArray = village.getBuilding();
		List<NPC> npcArray = village.getNpc();

		buildingButton = new BuildingImage[buildingArray.size()];
		npcButton = new TextButton[npcArray.size()];

		for (int i = 0; i < buildingArray.size(); i++) {
			Building buildinginfo = buildingArray.get(i);

			buildingButton[i] = new BuildingImage(buildinginfo);

			buildingButton[i].addListener(new InputListener() {

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
			addActor(buildingButton[i]);
		}

		for (int i = 0; i < npcArray.size(); i++) {
			final NPC npcinfo = npcArray.get(i);

			npcButton[i] = new TextButton(npcinfo.getName(), Assets.skin);

			float posX = viewportWidth * (float) npcinfo.getPositionX();
			float posY = viewportHeight * (float) npcinfo.getPositionY();

			npcButton[i].moveBy(posX, posY);

			//임시로 npcbutton[0]에만 이벤트를 적용함
			npcButton[0].addListener(new InputListener() {

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
			addActor(npcButton[i]);
		}

		// 전환 버튼 기능은 빌리지 스크린에서 구현
		siftButton = new TextButton("전환", Assets.skin);
		siftButton.center();

		siftButton.addListener(new InputListener() {

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
				}

				event.getListenerActor().setVisible(false);
			}
		});
		addActor(siftButton);

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
			siftButton.moveBy(0, movingSpeed);

		} else if (currentState.equals("movedown")) {
			camera.translate(0, -movingSpeed, 0);
			siftButton.moveBy(0, -movingSpeed);
		} else {

		}
	}

	private void checkBound() {
		if (camera.position.y > (viewportHeight - Assets.windowHeight / 2)) {
			camera.position.y = viewportHeight - Assets.windowHeight / 2;
			currentState = "up";
			siftButton.setVisible(true);

		} else if (camera.position.y < Assets.windowHeight * 0.25f) {
			camera.position.y = Assets.windowHeight * 0.25f;
			currentState = "down";
			siftButton.setVisible(true);
		}
	}
}
