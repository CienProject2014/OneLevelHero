package com.mygdx.model;

import java.util.HashMap;
import java.util.Map;

public class WorldNode {
	private String nodeName;
	private String type;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
