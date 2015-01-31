package com.mygdx.model;

import com.mygdx.enums.MinimapEnum;

/**
 * Dungeon 모델 클래스
 * @author Velmont
 *
 */
public class Dungeon {
	private String dungeonName;
	private int properLevel;
	private String attribute;
	private MinimapEnum[][] minimap;

	public String getDungeonName() {
		return dungeonName;
	}

	public void setDungeonName(String dungeonName) {
		this.dungeonName = dungeonName;
	}

	public int getProperLevel() {
		return properLevel;
	}

	public void setProperLevel(int properLevel) {
		this.properLevel = properLevel;
	}

	public String getAttribute() {
		return attribute;
	}

	public void setAttribute(String attribute) {
		this.attribute = attribute;
	}

	public MinimapEnum[][] getMinimap() {
		return minimap;
	}

	public void setMinimap(MinimapEnum[][] minimap) {
		this.minimap = minimap;
	}
}
