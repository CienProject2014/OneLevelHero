package com.mygdx.manager;

import org.json.simple.JSONObject;

import com.mygdx.loader.StatusLoader;
import com.mygdx.resource.Assets;
import com.mygdx.unit.Hero;

public class LoadLauncher {
	JSONObject status_new_left, bag_json;
	StatusLoader statusLoader;
	public Hero leftHero;
	public Hero centerHero;
	public Hero rightHero;
	private static LoadLauncher instance;

	public LoadLauncher() {

	}

	public static LoadLauncher getInstance() {
		if (null == instance) {
			instance = new LoadLauncher();
		}
		return instance;
	}

	public void dispose() {
		instance = null;
	}

	// 주요 클래스들에게 JSON을 주입해준다.
	public void loadSetting() {
		setStatus();
		setParty();
	}

	// Hero클래스가 status정보를 갖도록 한다.
	private void setStatus() {
		status_new_left = Assets.status_new_left; // Asset에서 status관련
													// jsonObject를 읽어옴
		bag_json = Assets.bag_json; // Asset에서 bag관련 jsonObject를 읽어옴

		// statusLoader에서 jsonObject를 파싱하고 클래스 정보를 주입함
		statusLoader = new StatusLoader(CurrentManager.getInstance().getVersion(), "Hero", status_new_left, bag_json);

		leftHero = new Hero("yongsa", statusLoader.getStatus(), statusLoader.getBag()); // unit클래스에 파싱한 json 정보를 뿌려줌

		CurrentManager.getInstance().setLeftHero(leftHero); // currentManager가 unit을	 소유하도록 만든다.
	}

	// 해당 Hero들을 Party구성원에 포함시킨다
	private void setParty() {
		CurrentManager.getInstance().party.addParty(leftHero);
	}
}
