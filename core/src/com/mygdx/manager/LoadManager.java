package com.mygdx.manager;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.model.Hero;
import com.mygdx.resource.Assets;

public class LoadManager {
	private static LoadManager instance;
	private List<Hero> heroes;

	private LoadManager() {
		Assets.loadAll();
		setHeroes();
		setPartyList();

	}

	public static LoadManager getInstance() {
		if (null == instance) {
			instance = new LoadManager();
		}
		return instance;
	}

	// Hero클래스가 status정보를 갖도록 한다.
	private void setHeroes() {
		heroes = new ArrayList<Hero>();
		//추후 JSON에서 불러오도록 바꿀 것
		Hero yongsa = Assets.heroMap.get("yongsa");
		heroes.add(yongsa);

		CurrentManager.getInstance().setHeroes(heroes); // currentManager가 unit을 소유하도록 만든다.
	}

	// 해당 Hero들을 Party구성원에 포함시킨다
	private void setPartyList() {
		CurrentManager.getInstance().party.addPartyList(heroes);
		CurrentManager.getInstance().party.addBattleMemberList(heroes);
	}
}
