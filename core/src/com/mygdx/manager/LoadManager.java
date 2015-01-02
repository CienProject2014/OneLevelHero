package com.mygdx.manager;

import com.mygdx.model.Hero;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class LoadManager {
	private static LoadManager instance;
	private Hero hero;

	private LoadManager() {
		Assets.loadAll();
		setYongsa();
	}

	public static LoadManager getInstance() {
		if (null == instance) {
			instance = new LoadManager();
		}
		return instance;
	}

	private void setYongsa() {
		setHero(Assets.heroMap.get("yongsa"));
		CurrentState.getInstance().getParty().addParty(hero);
	}

	public Hero getHero() {
		return hero;
	}

	public void setHero(Hero hero) {
		this.hero = hero;
	}
}
