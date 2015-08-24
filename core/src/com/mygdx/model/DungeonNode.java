package com.mygdx.model;

import java.util.HashMap;

public class DungeonNode {
	public final static int FLG_NULL = 0;
	public final static int FLG_ENTRANCE = (1 << 0);
	public final static int FLG_ROAD = (1 << 1);
	public final static int FLG_ENCOUNT = (1 << 2);
	public final static int FLG_TRESURE = (1 << 3) | FLG_ROAD;
	public final static int FLG_BOSS = (1 << 4) | FLG_ENCOUNT;

	private final static String TAG_LABEL = "label";
	private final static String TAG_FLAG = "flag";

	private String nodePos;
	private String nodeFlag;

	public HashMap<String, Object> data = new HashMap<>();

	public boolean chkFlag(int flg) {
		if (nodeFlag.equals("ENTRANCE"))
			setFlag(FLG_ENTRANCE);
		else if (nodeFlag.equals("ROOM"))
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
}
