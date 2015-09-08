package com.mygdx.model.location;

import java.util.HashMap;

import com.mygdx.enums.DungeonNodeEnum;

public class DungeonNode {
	public final static int FLG_NULL = 0;
	public final static int FLG_ENTRANCE = (1 << 0);
	public final static int FLG_ROAD = (1 << 1);
	public final static int FLG_ENCOUNT = (1 << 2);
	public final static int FLG_TRESURE = (1 << 3) | FLG_ROAD;
	public final static int FLG_BOSS = (1 << 4) | FLG_ENCOUNT;

	private final static String TAG_LABEL = "label";
	private final static String TAG_FLAG = "flag";

	private String nodeLabel;
	private int nodePosX;
	private int nodePosY;
	private String directionType;
	private String eliteMonster;

	private DungeonNodeEnum dungeonNodeType;

	public HashMap<String, Object> data = new HashMap<>();

	public String getDirectionType() {
		return directionType;
	}

	public boolean chkFlag(int flg) {
		if (dungeonNodeType.equals(DungeonNodeEnum.GATE))
			setFlag(FLG_ENTRANCE);
		else if (dungeonNodeType.equals(DungeonNodeEnum.NORMAL))
			setFlag(FLG_ROAD);
		else if (dungeonNodeType.equals(DungeonNodeEnum.OBJECT))
			setFlag(FLG_TRESURE);
		else if (dungeonNodeType.equals(DungeonNodeEnum.ELITE))
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

	public int getNodePosX() {
		return nodePosX;
	}

	public int getNodePosY() {
		return nodePosY;
	}

	public DungeonNodeEnum getDungeonNodeType() {
		return dungeonNodeType;
	}

	public void setDungeonNodeType(DungeonNodeEnum dungeonNodeType) {
		this.dungeonNodeType = dungeonNodeType;
	}

	public String getEliteMonster() {
		return eliteMonster;
	}

	public void setEliteMonster(String eliteMonster) {
		this.eliteMonster = eliteMonster;
	}

	public String getNodeLabel() {
		return nodeLabel;
	}

	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}
}
