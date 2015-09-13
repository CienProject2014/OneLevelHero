package com.mygdx.model.location;

import com.mygdx.enums.DungeonEnum;

public class DungeonConnection {
	private DungeonEnum.Direction directionType;
	public DungeonEnum.Direction getDirectionType() {
		return directionType;
	}

	public void setDirectionType(DungeonEnum.Direction directionType) {
		this.directionType = directionType;
	}
}
