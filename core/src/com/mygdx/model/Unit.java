package com.mygdx.model;

import java.util.ArrayList;
import java.util.Map;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.manager.TextureManager;

public abstract class Unit implements Comparable<Unit>, Fightable {
	private String facePath;
	private String name;
	protected Status status;
	private Map<String, Skill> skills;
	private int gauge;
	private Texture bodyTexture;
	private Texture faceTexture;
	private Texture bigBattleTexture;
	private Texture smallBattleTexture;

	public String getFacePath() {
		return facePath;
	}

	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}

	/* For Json Work */
	private ArrayList<String> skillList;

	public String getName() {
		return name;
	}

	public Map<String, Skill> getSkills() {
		return skills;
	}

	public void setSkills(Map<String, Skill> skills) {
		this.skills = skills;
	}

	public ArrayList<String> getSkillList() {
		return skillList;
	}

	public void setSkillList(ArrayList<String> skillList) {
		this.skillList = skillList;
	}

	public void setBodyTexture(Texture bodyTexture) {
		this.bodyTexture = bodyTexture;
	}

	public void setFaceTexture(Texture faceTexture) {
		this.faceTexture = faceTexture;
	}

	public void setBigBattleTexture(Texture bigBattleTexture) {
		this.bigBattleTexture = bigBattleTexture;

	}

	public void setSmallBattleTexture(Texture smallBattleTexture) {
		this.smallBattleTexture = smallBattleTexture;
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

	public int getGauge() {
		return gauge;
	}

	public void setGauge(int gauge) {
		this.gauge = gauge;
	}

	@Override
	public int compareTo(Unit obj) {
		if (this.getGauge() == obj.getGauge()) {
			if (this.getStatus().getSpeed() == obj.getStatus().getSpeed()) {
				return 0;
			} else if (this.getStatus().getSpeed() > obj.getStatus().getSpeed()) {
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

	public Texture getBodyTexture() {
		if (bodyTexture == null) {
			if (this instanceof Hero)
				bodyTexture = TextureManager.getCharacterBodyTexture(facePath);
			else
				bodyTexture = TextureManager.getMonsterBodyTexture(facePath);
		}
		return bodyTexture;
	}

	public Texture getFaceTexture() {
		if (faceTexture == null) {
			if (this instanceof Hero)
				faceTexture = TextureManager.getCharacterFaceTexture(facePath);
			else
				faceTexture = TextureManager.getMonsterFaceTexture(facePath);
		}
		return faceTexture;
	}

	public Texture getBigBattleTexture() {
		if (bigBattleTexture == null) {
			if (this instanceof Hero)
				bigBattleTexture = TextureManager
						.getCharacterBattleTurnBigTexture(facePath);
			else
				bigBattleTexture = TextureManager
						.getMonsterBattleTurnBigTexture(facePath);
		}
		return bigBattleTexture;
	}

	public Texture getSmallBattleTexture() {
		if (smallBattleTexture == null) {
			if (this instanceof Hero)
				smallBattleTexture = TextureManager
						.getCharacterBattleTurnSmallTexture(facePath);
			else
				smallBattleTexture = TextureManager
						.getMonsterBattleTurnSmallTexture(facePath);
		}
		return smallBattleTexture;
	}
}
