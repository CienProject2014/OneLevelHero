package com.mygdx.stagemanager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.resource.Assets;

public class VillageStage extends Stage {

	JSONParser parser = new JSONParser();
	JSONObject object;
	String delimiter = "-";
	String village_name;
	int village_state;
	int num_of_building;
	int num_of_npc;
	ImageButton buildingbutton[];

	public TextButton sift_button;
	public Texture background;

	float viewportwidth;
	float viewportheight;

	public VillageStage() {
		super();
	}

	public VillageStage(String key) {
		super();
		Assets.load();
		keyParser(key);
		jsonread();
		village_setter();
	}

	// 마을 정보 로딩
	private void jsonread() {

		FileHandle file = Gdx.files.internal("data/village.json");
		String text = file.readString();
		Object obj = JSONValue.parse(text);
		object = (JSONObject) obj;

	}

	// 마을 정보에 맞게 스테이지 형성
	private void village_setter() {

		viewportwidth = this.getWidth();
		viewportheight = this.getHeight();

		background = new Texture(
				Gdx.files.internal("texture/village/blackwood.png"));

		JSONArray village_data = (JSONArray) object.get(village_name);
		JSONObject this_village = (JSONObject) village_data.get(village_state);

		num_of_building = Integer.parseInt(this_village.get("num_of_building")
				.toString());
		num_of_npc = Integer
				.parseInt(this_village.get("num_of_npc").toString());

		JSONArray buildingarray = (JSONArray) this_village.get("building");
		JSONArray npcarray = (JSONArray) this_village.get("npc");

		buildingbutton = new ImageButton[num_of_building];

		for (int i = 0; i < num_of_building; i++) {
			JSONObject building = (JSONObject) buildingarray.get(i);
			System.out.println((String) building.get("name"));

			Texture buildingtex = new Texture(
					Gdx.files.internal((String) building.get("imagesource")));
			TextureRegionDrawable buildingimg = new TextureRegionDrawable(
					new TextureRegion(buildingtex));

			buildingbutton[i] = new ImageButton(buildingimg);

			int positionx = Integer
					.parseInt((String) building.get("positionx"));
			int positiony = Integer
					.parseInt((String) building.get("positiony"));

			positionx = (int) (viewportwidth * (positionx / 1920.0));
			positiony = (int) (viewportheight * (positiony / 1080.0));

			System.out.println(positionx);
			System.out.println(positiony);

			buildingbutton[i].moveBy(positionx, positiony);

			addActor(buildingbutton[i]);
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
