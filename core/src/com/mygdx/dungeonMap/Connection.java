package com.mygdx.dungeonMap;

public class Connection {
	private Node from, to;

	public Connection(Node from, Node to) {
		this.from = from;
		this.to = to;
	}

	public boolean chkReferExist(Node node) {
		return node == from | node == to;
	}

	public Node getFrom() {
		return from;
	}

	public Node getTo() {
		return to;
	}
}
