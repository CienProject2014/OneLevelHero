package com.mygdx.model;

import java.util.HashMap;

public class DungeonConnection {
	private final static String TAG_LABEL = "label";
	private final static String TAG_FROM = "from";
	private final static String TAG_TO = "to";

	public HashMap<String, Object> data;
	
	private int from;
	private int to;

	public boolean isFrom(DungeonNode node) {
		return node == getFrom();
	}

	public boolean isTo(DungeonNode node) {
		return node == getTo();
	}

	public String getLabel() {
		return (String) this.data.get(TAG_LABEL);
	}

	public void setLabel(String label) {
		this.data.put(TAG_LABEL, label);
	}

	public DungeonNode getFrom() {
		return (DungeonNode) this.data.get(TAG_FROM);
	}

	public void setFrom(DungeonNode from) {
		this.data.put(TAG_FROM, from);
	}

	public DungeonNode getTo() {
		return (DungeonNode) this.data.get(TAG_TO);
	}

	public void setTo(DungeonNode to) {
		this.data.put(TAG_TO, to);
	}
}
