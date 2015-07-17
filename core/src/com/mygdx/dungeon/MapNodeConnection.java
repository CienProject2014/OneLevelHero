package com.mygdx.dungeon;

public class MapNodeConnection {
	private MapNode from, to;

	public MapNodeConnection(MapNode from, MapNode to) {
		this.from = from;
		this.to = to;
	}

	public boolean isFrom(MapNode node) {
		return node == from;
	}

	public boolean isTo(MapNode node) {
		return node == to;
	}

	public MapNode getFrom() {
		return from;
	}

	public MapNode getTo() {
		return to;
	}
}
