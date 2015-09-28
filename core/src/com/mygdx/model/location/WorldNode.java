package com.mygdx.model.location;

import java.util.HashMap;
import java.util.Map;

import com.mygdx.enums.WorldNodeEnum;

public class WorldNode {
	private String nodeName;
	private WorldNodeEnum.NodeType nodeType;
	private HashMap<String, NodeConnection> nodeConnection;

	public Map<String, NodeConnection> getNodeConnection() {
		return nodeConnection;
	}

	public void setConnection(HashMap<String, NodeConnection> nodeConnection) {
		this.nodeConnection = nodeConnection;
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
