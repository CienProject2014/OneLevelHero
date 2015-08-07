package com.mygdx.model.surroundings;

import java.util.HashMap;
import java.util.Map;

import com.mygdx.enums.WorldNodeEnum;

public class WorldNode {
	private String nodeName;
	private WorldNodeEnum.NodeType nodeType;
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

	public WorldNodeEnum.NodeType getNodeType() {
		return nodeType;
	}

	public void setNodeType(WorldNodeEnum.NodeType nodeType) {
		this.nodeType = nodeType;
	}
}
