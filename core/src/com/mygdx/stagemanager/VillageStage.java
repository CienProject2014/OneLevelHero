package com.mygdx.stagemanager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.resource.Assets;
import com.mygdx.screen.CollectionScreen;

public class VillageStage extends Stage{
	
	JSONParser parser = new JSONParser();
	JSONObject object;
	String delimiter = "-";
	String village_name;
	int village_state;
	int num_of_building;
	int num_of_npc;
	TextButton buildingbutton[];
	public TextButton sift_button;
	
	public VillageStage(){
		super();
	}
	
	public VillageStage(String key){
		super();
		Assets.load();
		keyParser(key);
		jsonread();
		village_setter();
	}
	
	private void jsonread(){
		
		FileHandle file = Gdx.files.internal("data/village.json");
		String text = file.readString();
		Object obj = JSONValue.parse(text);
		object = (JSONObject) obj;

	}
	private void village_setter(){
		
		JSONArray village_data = (JSONArray)object.get(village_name);
		JSONObject this_village = (JSONObject)village_data.get(village_state);
		num_of_building = Integer.parseInt(this_village.get("num_of_building").toString());
		num_of_npc = Integer.parseInt(this_village.get("num_of_npc").toString());
		JSONArray buildingarray = (JSONArray)this_village.get("building");
		JSONArray npcarray = (JSONArray)this_village.get("npc");
		
		buildingbutton = new TextButton[num_of_building];
		
		
		for(int i = 0 ; i < num_of_building ; i++)
		{
			JSONObject building = (JSONObject)buildingarray.get(i);
			System.out.println((String)building.get("name"));
			buildingbutton[i] = new TextButton((String)building.get("name"), Assets.skin);
			buildingbutton[i].moveBy(Integer.parseInt((String)building.get("positionx")),Integer.parseInt((String)building.get("positionx")));
			addActor(buildingbutton[i]);
		}
		
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
