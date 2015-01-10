package com.mygdx.model;

import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.Json.Serializable;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.ObjectMap;
import com.mygdx.manager.JsonParser;

public class WorldNodeInfo implements Serializable {
	private String nodeName;
	private String type;
	private ObjectMap<String, Connection> connection;

	public ObjectMap<String, Connection> getConnection() {
		return connection;
	}

	public void setConnection(ObjectMap<String, Connection> connection) {
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
	public void write(Json json) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("unchecked")
	@Override
	public void read(Json json, JsonValue jsonData) {
		nodeName = json.readValue("nodeName", String.class, jsonData);
		type = json.readValue("type", String.class, jsonData);
		connection = JsonParser.parseObjectMap(Connection.class,
				jsonData.get("connection").toString());

	}

}