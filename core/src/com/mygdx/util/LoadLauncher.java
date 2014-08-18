package com.mygdx.util;

import org.json.simple.JSONObject;

import com.mygdx.game.OneLevelHero;
import com.mygdx.loader.StatusLoader;
import com.mygdx.resource.Assets;
import com.mygdx.unit.Unit;

public class LoadLauncher {
	OneLevelHero game;
	JSONObject status_json, bag_json;
	StatusLoader statusLoader;
	public Unit unit;

	public LoadLauncher(OneLevelHero game) {
		this.game = game;
	}

	//주요 클래스들에게 JSON을 주입해준다.
	public void jsonSetting() {
		setStatus();
	}

	//Unit클래스가 status정보를 갖도록 한다.
	public void setStatus() {
		status_json = Assets.status_json; //Asset에서 status관련 jsonObject를 읽어옴
		bag_json = Assets.bag_json; //Asset에서 bag관련 jsonObject를 읽어옴
		statusLoader = new StatusLoader(game.currentManager.getVersion(), "Hero", status_json, bag_json); //statusLoader에서 jsonObject를 파싱하고 클래스 정보를 주입함		
		unit = new Unit(statusLoader.getStatus(), statusLoader.getBag()); //unit클래스에 파싱한 json 정보를 뿌려줌
		game.currentManager.setUnit(unit); //currentManager가 unit을 소유하도록 만든다.
	}
}
