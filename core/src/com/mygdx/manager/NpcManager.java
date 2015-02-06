package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mygdx.model.NPC;
import com.mygdx.state.Assets;

@Component
public class NpcManager {
	@Autowired
	private Assets assets;

	public NPC getNpc(String npcName) {
		return assets.npcMap.get(npcName);
	}
}
