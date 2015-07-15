package com.mygdx.model;

import java.util.ArrayList;
import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.enums.TextureEnum;
import com.mygdx.manager.TextureManager;

/**
 * Village 모델 클래스
 *
 * @author Velmont
 *
 */
public class Village {
	private String villageName;
	private String backgroundPath;
	private String sceneName;
	private ArrayList<String> villageNpc;
	private HashMap<String, Building> building;

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public Texture getBackgroundUp() {
		return TextureManager.getBackgroundTexture(backgroundPath,
				TextureEnum.BACKGROUND_UP);
	}

	public Texture getBackgroundDown() {
		return TextureManager.getBackgroundTexture(backgroundPath,
				TextureEnum.BACKGROUND_DOWN);
	}

	public ArrayList<String> getVillageNpc() {
		return villageNpc;
	}

	public void setVillageNpc(ArrayList<String> villageNpc) {
		this.villageNpc = villageNpc;
	}

	public HashMap<String, Building> getBuilding() {
		return building;
	}

	public void setBuilding(HashMap<String, Building> building) {
		this.building = building;
	}

	public String getBackgroundPath() {
		return backgroundPath;
	}

	public void setBackgroundPath(String backgroundPath) {
		this.backgroundPath = backgroundPath;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}
}
