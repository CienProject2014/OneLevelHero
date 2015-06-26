package com.mygdx.model;

import java.util.Map;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.mygdx.manager.JsonParser;

public class WorldNode implements Serializable {
	private String nodeName;
	private String type;
	private Map<String, Connection> connection;

	public Map<String, Connection> getConnection() {
		return connection;
	}

	public void setConnection(Map<String, Connection> connection) {
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

	@Override
	public void write(Json json) {}

	@Override
	public void read(Json json, JsonValue jsonData) {
		nodeName = json.readValue("nodeName", String.class, jsonData);
		type = json.readValue("type", String.class, jsonData);
		connection = JsonParser.parseMap(Connection.class, jsonData.get("connection").toString());
	}
}
