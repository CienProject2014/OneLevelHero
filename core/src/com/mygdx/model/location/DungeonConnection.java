package com.mygdx.model.location;

import java.util.HashMap;

public class DungeonConnection {
	private final static String TAG_LABEL = "label";
	private final static String TAG_FROM = "from";
	private final static String TAG_TO = "to";

	public HashMap<String, Object> data;

	private int from;
	private int to;
	private String label;

	public boolean isFrom(int Pos) {
		return Pos == from;
	}

	public boolean isTo(int Pos) {
		return Pos == to;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.data.put(TAG_LABEL, label);
	}

	public int getFrom() {
		return from;
	}

	public void setFrom(DungeonRoom from) {
		this.data.put(TAG_FROM, from);
	}

	public int getTo() {
		return to;
	}

	public void setTo(DungeonRoom to) {
		this.data.put(TAG_TO, to);
	}
}
