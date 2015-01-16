package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.manager.UnitTextureManager;

public class Unit {
	private String name;
	private Texture statusTexture;
	private Texture battleTexture;
	private Texture npcTexture;

	public Texture getBattleTexture() {
		if (battleTexture == null) {
			UnitTextureManager.setBattleTexture(this);
		}
		return battleTexture;
	}

	public void setBattleTexture(Texture battleTexture) {
		this.battleTexture = battleTexture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Texture getStatusTexture() {
		if (statusTexture == null) {
			UnitTextureManager.setStatusTexture(this);
		}
		return statusTexture;
	}

	public void setStatusTexture(Texture statusTexture) {

		this.statusTexture = statusTexture;
	}

	public Texture getNpcTexture() {
		return npcTexture;
	}

	public void setNpcTexture(Texture npcTexture) {
		if (npcTexture == null) {
			UnitTextureManager.setNpcTexture(this);
		}
		this.npcTexture = npcTexture;
	}
}
