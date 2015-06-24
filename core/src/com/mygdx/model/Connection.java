package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;

public class Connection implements Serializable {
	private int roadLength;
	private List<String> roadMonster;
	private String arrowName;

	public int getroadLength() {
		return roadLength;
	}

	public void setroadLength(int roadLength) {
		this.roadLength = roadLength;
	}

	@Override
	public void write(Json json) {}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		roadLength = json.readValue("roadLength", Integer.class, jsonData);
		roadMonster = json.readValue("roadMonster", ArrayList.class,
				String.class, jsonData);
		arrowName = json.readValue("arrowName", String.class, jsonData);
	}

	public List<String> getRoadMonster() {
		return roadMonster;
	}

	public void setRoadMonster(List<String> roadMonster) {
		this.roadMonster = roadMonster;
	}

	public String getArrowName() {
		return arrowName;
	}

	public void setArrowName(String arrowName) {
		this.arrowName = arrowName;
	}
}
