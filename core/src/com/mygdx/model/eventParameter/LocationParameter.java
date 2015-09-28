package com.mygdx.model.eventParameter;

public class LocationParameter extends Parameter {
	private String nodePath;
	private String subNodePath;
	private String floorPath;
	private String arrowLabel;
	private String roomLabel;
	private String npcPath;
	private String gameObjectPath;

	public String getRoomLabel() {
		return roomLabel;
	}
	public void setRoomLabel(String roomLabel) {
		this.roomLabel = roomLabel;
	}
	public String getNodePath() {
		return nodePath;
	}
	public void setNodePath(String nodePath) {
		this.nodePath = nodePath;
	}
	public String getSubNodePath() {
		return subNodePath;
	}
	public void setSubNodePath(String subNodePath) {
		this.subNodePath = subNodePath;
	}
	public String getFloorPath() {
		return floorPath;
	}
	public void setFloorPath(String floorPath) {
		this.floorPath = floorPath;
	}
	public String getGameObjectPath() {
		return gameObjectPath;
	}
	public void setGameObjectPath(String gameObjectPath) {
		this.gameObjectPath = gameObjectPath;
	}
	public String getNpcPath() {
		return npcPath;
	}
	public void setNpcPath(String npcPath) {
		this.npcPath = npcPath;
	}
	public String getArrowLabel() {
		return arrowLabel;
	}
	public void setArrowLabel(String arrowLabel) {
		this.arrowLabel = arrowLabel;
	}
}
