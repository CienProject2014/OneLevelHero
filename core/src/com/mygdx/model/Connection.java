package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Connection implements Serializable {
	private int roadCount;
	private List<String> roadMonster;

	public int getRoadCount() {
		return roadCount;
	}

	public void setRoadCount(int roadCount) {
		this.roadCount = roadCount;
	}

	@Override
	public void write(Json json) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		roadCount = json.readValue("roadCount", Integer.class, jsonData);
		roadMonster = json.readValue("roadMonster", ArrayList.class,
				String.class, jsonData);

	}

	public List<String> getRoadMonster() {
		return roadMonster;
	}

	public void setRoadMonster(List<String> roadMonster) {
		this.roadMonster = roadMonster;
	}

}