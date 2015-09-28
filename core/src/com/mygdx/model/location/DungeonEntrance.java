package com.mygdx.model.location;

public class DungeonEntrance {
	private String nodeName;
	private String nodePath;
	private String connectionNode;
	private boolean isHidden;
	private int startDungeonRoomIndex;
	public String getNodePath() {
		return nodePath;
	}
	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
	public String getConnectionNode() {
		return connectionNode;
	}
	public void setConnectionNode(String connectionNode) {
		this.connectionNode = connectionNode;
	}
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public int getStartDungeonRoomIndex() {
		return startDungeonRoomIndex;
	}
	public void setStartDungeonRoomIndex(int startDungeonRoomIndex) {
		this.startDungeonRoomIndex = startDungeonRoomIndex;
	}
	public boolean isHidden() {
		return isHidden;
	}
	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}
}
