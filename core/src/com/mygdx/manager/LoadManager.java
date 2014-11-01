package com.mygdx.manager;

import com.mygdx.resource.Assets;

public class LoadManager {
	private static LoadManager instance;

	public LoadManager() {
		Assets.loadAll();
	}

	public static LoadManager getInstance() {
		if (null == instance) {
			instance = new LoadManager();
		}
		return instance;
	}

}
