package com.mygdx.model;

import java.util.ArrayList;
import java.util.List;

public class Connection {
	private int roadLength;
	private ArrayList<String> roadMonster;
	private String arrowName;

	public int getroadLength() {
		return roadLength;
	}

	public void setroadLength(int roadLength) {
		this.roadLength = roadLength;
	}

	public List<String> getRoadMonster() {
		return roadMonster;
	}

	public void setRoadMonster(ArrayList<String> roadMonster) {
		this.roadMonster = roadMonster;
	}

	public String getArrowName() {
		return arrowName;
	}

	public void setArrowName(String arrowName) {
		this.arrowName = arrowName;
	}
}
