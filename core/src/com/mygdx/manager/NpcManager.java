package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.model.NPC;
import com.mygdx.state.Assets;

public class NpcManager {
	@Autowired
	private Assets assets;

	public Assets getAssets() {
		return assets;
	}

	public void setAssets(Assets assets) {
		this.assets = assets;
	}

	public NPC getNpc(String npcName) {
		return assets.npcMap.get(npcName);
	}
}
