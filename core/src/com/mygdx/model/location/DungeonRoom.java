package com.mygdx.model.location;

import java.util.HashMap;

import com.mygdx.enums.DungeonRoomEnum;

public class DungeonRoom {
	public final static int FLG_NULL = 0;
	public final static int FLG_ENTRANCE = (1 << 0);
	public final static int FLG_ROAD = (1 << 1);
	public final static int FLG_ENCOUNT = (1 << 2);
	public final static int FLG_TRESURE = (1 << 3) | FLG_ROAD;
	public final static int FLG_BOSS = (1 << 4) | FLG_ENCOUNT;

	private final static String TAG_LABEL = "label";
	private final static String TAG_FLAG = "flag";

	private String roomLabel;
	private int roomPosX;
	private int roomPosY;
	private String directionType;
	private String eliteMonster;

	private DungeonRoomEnum dungeonRoomType;

	public HashMap<String, Object> data = new HashMap<>();

	public String getDirectionType() {
		return directionType;
	}

	public boolean chkFlag(int flg) {
		if (dungeonRoomType.equals(DungeonRoomEnum.GATE))
			setFlag(FLG_ENTRANCE);
		else if (dungeonRoomType.equals(DungeonRoomEnum.NORMAL))
			setFlag(FLG_ROAD);
		else if (dungeonRoomType.equals(DungeonRoomEnum.OBJECT))
			setFlag(FLG_TRESURE);
		else if (dungeonRoomType.equals(DungeonRoomEnum.ELITE))
			setFlag(FLG_ENCOUNT);
		return (flg & (int) this.data.get(TAG_FLAG)) != FLG_NULL;
	}

	public void addjustFlag(int flg) {
		this.data.put(TAG_FLAG, flg | (int) this.data.get(TAG_FLAG));
	}

	public String getLabel() {
		return (String) this.data.get(TAG_LABEL);
	}

	public void setLabel(String label) {
		this.data.put(TAG_LABEL, label);
	}

	public int getFlag() {
		return (int) this.data.get(TAG_FLAG);
	}

	public void setFlag(int flg) {
		this.data.put(TAG_FLAG, flg);
	}

	public int getRoomPosX() {
		return roomPosX;
	}

	public int getRoomPosY() {
		return roomPosY;
	}

	public DungeonRoomEnum getDungeonRoomType() {
		return dungeonRoomType;
	}

	public void setDungeonRoomType(DungeonRoomEnum dungeonRoomType) {
		this.dungeonRoomType = dungeonRoomType;
	}

	public String getEliteMonster() {
		return eliteMonster;
	}

	public void setEliteMonster(String eliteMonster) {
		this.eliteMonster = eliteMonster;
	}

	public String getRoomLabel() {
		return roomLabel;
	}

	public void setRoomLabel(String roomLabel) {
		this.roomLabel = roomLabel;
	}
}
