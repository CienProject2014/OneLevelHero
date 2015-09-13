package com.mygdx.model.location;

import java.util.HashMap;

import com.mygdx.enums.DungeonEnum;

public class DungeonRoom {
	private HashMap<String, DungeonConnection> forwardConnections;
	private HashMap<String, DungeonConnection> backwardConnections;
	private DungeonEnum.Type roomType;
	private DungeonEnum.ForwardAngle forwardAngle;
	private String roomLabel;
	private String link;
	private int roomPosX;
	private int roomPosY;

	public int getRoomPosX() {
		return roomPosX;
	}

	public int getRoomPosY() {
		return roomPosY;
	}

	public String getRoomLabel() {
		return roomLabel;
	}

	public void setRoomLabel(String roomLabel) {
		this.roomLabel = roomLabel;
	}

	public void setRoomPosX(int roomPosX) {
		this.roomPosX = roomPosX;
	}

	public void setRoomPosY(int roomPosY) {
		this.roomPosY = roomPosY;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public DungeonEnum.Type getRoomType() {
		return roomType;
	}

	public void setRoomType(DungeonEnum.Type roomType) {
		this.roomType = roomType;
	}

	public DungeonEnum.ForwardAngle getForwardAngle() {
		return forwardAngle;
	}

	public void setForwardAngle(DungeonEnum.ForwardAngle forwardAngle) {
		this.forwardAngle = forwardAngle;
	}

	public HashMap<String, DungeonConnection> getForwardConnections() {
		return forwardConnections;
	}

	public void setForwardConnections(HashMap<String, DungeonConnection> forwardConnections) {
		this.forwardConnections = forwardConnections;
	}

	public HashMap<String, DungeonConnection> getBackwardConnections() {
		return backwardConnections;
	}

	public void setBackwardConnections(HashMap<String, DungeonConnection> backwardConnections) {
		this.backwardConnections = backwardConnections;
	}
}
