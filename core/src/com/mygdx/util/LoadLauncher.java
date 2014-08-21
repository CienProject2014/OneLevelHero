package com.mygdx.util;

import org.json.simple.JSONObject;

import com.mygdx.game.OneLevelHero;
import com.mygdx.loader.StatusLoader;
import com.mygdx.resource.Assets;
import com.mygdx.unit.Hero;

public class LoadLauncher {
	JSONObject status_new_left, bag_json;
	StatusLoader statusLoader;
	public Hero unitLeft;
	public Hero unitCenter;
	public Hero unitRight;

	public LoadLauncher() {

	}

	// 주요 클래스들에게 JSON을 주입해준다.
	public void jsonSetting() {
		setStatus();
	}

	// Unit클래스가 status정보를 갖도록 한다.
	public void setStatus() {
		OneLevelHero game = ScreenManager.getGame();
		status_new_left = Assets.status_new_left; // Asset에서 status관련 jsonObject를 읽어옴
		bag_json = Assets.bag_json; // Asset에서 bag관련 jsonObject를 읽어옴

		// statusLoader에서 jsonObject를 파싱하고 클래스 정보를 주입함
		statusLoader = new StatusLoader(game.currentManager.getVersion(), "Hero", status_new_left, bag_json);

		unitLeft = new Hero("YongSa", statusLoader.getStatus(), statusLoader.getBag()); // unit클래스에 파싱한 json 정보를 뿌려줌
		game.currentManager.setUnit(unitLeft); // currentManager가 unit을 소유하도록 만든다.
		game.eventManager.setEvent("b-01-01");
	}
}
