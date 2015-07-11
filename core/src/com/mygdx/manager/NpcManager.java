package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.UnitAssets;
import com.mygdx.model.NPC;

public class NpcManager {
	@Autowired
	private UnitAssets unitAssets;

	public UnitAssets getUnitAssets() {
		return unitAssets;
	}

	public void setUnitAssets(UnitAssets unitAssets) {
		this.unitAssets = unitAssets;
	}

	public NPC getNpc(String npcName) {
		return unitAssets.getNpc(npcName);
	}
}
