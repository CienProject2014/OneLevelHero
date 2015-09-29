package com.mygdx.model.location;

import com.mygdx.enums.DungeonEnum;

public class DungeonConnection {
	private DungeonEnum.Direction directionType;
	private String roomLabel;

	public DungeonEnum.Direction getDirectionType() {
		return directionType;
	}

	public void setDirectionType(DungeonEnum.Direction directionType) {
		this.directionType = directionType;
	}

	public String getRoomLabel() {
		return roomLabel;
	}

	public void setRoomLabel(String roomLabel) {
		this.roomLabel = roomLabel;
	}
}
