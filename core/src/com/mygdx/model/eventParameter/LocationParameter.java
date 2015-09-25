package com.mygdx.model.eventParameter;

public class LocationParameter extends Parameter {
	private String nodeName;
	private String subNodeName;
	private String floorName;
	private String arrowName;
	private String roomLabel;
	private String npcName;
	public String getNodeName() {
		return nodeName;
	}
	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}
	public String getSubNodeName() {
		return subNodeName;
	}
	public void setSubNodeName(String subNodeName) {
		this.subNodeName = subNodeName;
	}
	public String getArrowName() {
		return arrowName;
	}
	public void setArrowName(String arrowName) {
		this.arrowName = arrowName;
	}
	public String getFloorName() {
		return floorName;
	}
	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}
	public String getRoomLabel() {
		return roomLabel;
	}
	public void setRoomLabel(String roomLabel) {
		this.roomLabel = roomLabel;
	}
	public String getNpcName() {
		return npcName;
	}
	public void setNpcName(String npcName) {
		this.npcName = npcName;
	}
}
