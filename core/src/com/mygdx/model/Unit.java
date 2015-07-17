package com.mygdx.model;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.manager.TextureManager;

public class Unit implements Comparable<Unit> {
	private String name;
	private Status status;
	private Skill skill;
	private int gauge;
	private Texture statusTexture;
	private Texture battleTexture;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Skill getSkill() {
		return skill;
	}

	public void setSkill(Skill skill) {
		this.skill = skill;
	}

	public int getGauge() {
		return gauge;
	}

	public void setGauge(int gauge) {
		this.gauge = gauge;
	}

	@Override
	public int compareTo(Unit obj) {
		if (this.getGauge() == obj.getGauge()) {
			if (this.getStatus().getSpd() == obj.getStatus().getSpd()) {
				return 0;
			} else if (this.getStatus().getSpd() > obj.getStatus().getSpd()) {
				return 1;
			} else {
				return -1;
			}
		} else if (this.getGauge() > obj.getGauge()) {
			return 1;
		} else {
			return -1;
		}
	}

	public Texture getStatusTexture() {
		if (statusTexture == null)
			statusTexture = TextureManager.getStatusTexture(name);

		return statusTexture;
	}

	public void setStatusTexture(Texture statusTexture) {
		this.statusTexture = statusTexture;
	}

	public Texture getBattleTexture() {
		if (battleTexture == null) {
			if (this instanceof Hero)
				battleTexture = TextureManager.getCharacterBattleTexture(name);
			else
				battleTexture = TextureManager.getMonsterBattleTexture(name);
		}
		return battleTexture;
	}

	public void setBattleTexture(Texture battleTexture) {
		this.battleTexture = battleTexture;
	}
}
