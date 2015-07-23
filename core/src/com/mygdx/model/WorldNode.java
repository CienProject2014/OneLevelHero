package com.mygdx.model;

import java.util.HashMap;
import java.util.Map;

import com.mygdx.enums.PlaceEnum;

public class WorldNode {
	private String nodeName;
	private PlaceEnum nodeType;
	private HashMap<String, Connection> connection;

	public Map<String, Connection> getConnection() {
		return connection;
	}

	public void setConnection(HashMap<String, Connection> connection) {
		this.connection = connection;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public PlaceEnum getNodeType() {
		return nodeType;
	}

	public void setNodeType(PlaceEnum nodeType) {
		this.nodeType = nodeType;
	}
}
