package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.manager.TextureManager;

public class Unit {
	private String name;
	private Texture statusTexture;
	private Texture battleTexture;
	private Texture npcTexture;

	public Texture getBattleTexture() {
		if (battleTexture == null) {
			if (this instanceof NPC || this instanceof Hero)
				battleTexture = TextureManager.getCharacterBattleTexture(name);
			else
				battleTexture = TextureManager.getMonsterBattleTexture(name);
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
		if (statusTexture == null)
			statusTexture = TextureManager.getStatusTexture(name);

		return statusTexture;
	}

	public void setStatusTexture(Texture statusTexture) {
		this.statusTexture = statusTexture;
	}

	public Texture getNpcTexture() {
		return npcTexture;
	}

	public void setNpcTexture(Texture npcTexture) {
		if (npcTexture == null)
			npcTexture = TextureManager.getNpcTexture(name);

		this.npcTexture = npcTexture;
	}
}
