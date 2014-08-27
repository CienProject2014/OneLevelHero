package com.mygdx.stage;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.event.EventTrigger;
import com.mygdx.resource.Assets;
import com.mygdx.unit.NPC;
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
	Label buildingbutton[];
	TextButton npcbutton[];
	TextButton exitbutton;

	private TextButton sift_button;
	public Texture background;

	float viewportwidth;
	float viewportheight;

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

		viewportwidth = this.getWidth();
		viewportheight = this.getHeight() * 2;

		background = new Texture(Gdx.files.internal("village/blackwood" + village_state + ".png"));

		Image backgroundImage = new Image(background);
		backgroundImage.setBounds(0, 0, viewportwidth, viewportheight);
		addActor(backgroundImage);

		JSONObject villageData = (JSONObject) Assets.village_json.get(CurrentManager.getInstance().getCurrentPosition());

		// 일단은 이렇게 한닷
		villageData = (JSONObject) Assets.village_json.get("B");

		JSONArray buildingArray = (JSONArray) villageData.get("building");
		JSONArray npcArray = (JSONArray) villageData.get("npc");
		JSONArray exitArray = (JSONArray) villageData.get("exit");

		num_of_building = buildingArray.size();
		num_of_npc = npcArray.size();
		num_of_exit = exitArray.size();

		// buildingbutton = new ImageButton[num_of_building];
		buildingbutton = new Label[num_of_building];
		npcbutton = new TextButton[num_of_npc];

		for (int i = 0; i < num_of_exit; i++) {
			JSONObject exit = (JSONObject) exitArray.get(i);
			exitbutton = new TextButton("Exit", Assets.skin);

			int positionx = Integer.parseInt((String) exit.get("positionx"));
			int positiony = Integer.parseInt((String) exit.get("positiony"));

			positionx = (int) (viewportwidth * (positionx / 1920.0));
			positiony = (int) (viewportheight * (positiony / 1080.0));

			exitbutton.moveBy(positionx, positiony);

			addActor(exitbutton);

			exitbutton.addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					Gdx.app.log("info", "Down");
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					Gdx.app.log("info", "Up");

					new ScreenController(ScreenEnum.WORLD);

					super.touchUp(event, x, y, pointer, button);
				}
			});
		}

		for (int i = 0; i < num_of_building; i++) {
			JSONObject building = (JSONObject) buildingArray.get(i);
			/*
			 * Texture buildingtex = new Texture( Gdx.files.internal((String)
			 * building.get("imagesource"))); TextureRegionDrawable buildingimg
			 * = new TextureRegionDrawable( new TextureRegion(buildingtex));
			 * 
			 * buildingbutton[i] = new ImageButton(buildingimg);
			 */

			buildingbutton[i] = new Label((String) building.get("name"), Assets.skin);

			int positionx = Integer.parseInt((String) building.get("positionx"));
			int positiony = Integer.parseInt((String) building.get("positiony"));

			positionx = (int) (viewportwidth * (positionx / 1920.0));
			positiony = (int) (viewportheight * (positiony / 1080.0));

			buildingbutton[i].moveBy(positionx, positiony);

			addActor(buildingbutton[i]);

			buildingbutton[i].addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
					Gdx.app.log("info", "Up");
					event.getListenerActor().setColor(Color.RED);
					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					Gdx.app.log("info", "Down");
					event.getListenerActor().setColor(Color.WHITE);

					// 기능 클래스 연결
					// game.setScreen(new MenuScreen(game));

					super.touchUp(event, x, y, pointer, button);
				}
			});// buildingbutton[i].set
		}

		for (int i = 0; i < num_of_npc; i++) {
			JSONObject npc = (JSONObject) npcArray.get(i);

			npcbutton[i] = new TextButton((String) npc.get("name"), Assets.skin);

			int positionx = Integer.parseInt((String) npc.get("positionx"));
			int positiony = Integer.parseInt((String) npc.get("positiony"));

			positionx = (int) (viewportwidth * (positionx / 1920.0));
			positiony = (int) (viewportheight * (positiony / 1080.0));

			npcbutton[i].moveBy(positionx, positiony);

			addActor(npcbutton[i]);
			npcbutton[i].addListener(new InputListener() {
				@Override
				public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

					return true;
				}

				@Override
				public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
					EventManager.getInstance().setEventCode("B-waiji-0");
					EventManager.getInstance().setEventType(EventTypeEnum.SELECT);
					NPC parathNPC = new NPC();
					EventTrigger.getInstance().setNpcEvent(parathNPC);
					new ScreenController(ScreenEnum.EVENT);

					super.touchUp(event, x, y, pointer, button);
				}
			});

		}

		// 전환 버튼 기능은 빌리지 스크린에서 구현
		sift_button = new TextButton("전환", Assets.skin);
		sift_button.center();
		addActor(sift_button);

	}

	void keyParser(String key) {
		String[] temp = key.split(delimiter);
		village_name = temp[0];
		village_state = Integer.parseInt(temp[1]);
	}
}
