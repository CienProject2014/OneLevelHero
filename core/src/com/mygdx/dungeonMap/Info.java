package com.mygdx.dungeonMap;

import java.util.ArrayList;

public class Info {
	public ArrayList<Node> nodes;
	public ArrayList<Connection> connections;

	public ArrayList<ArrayList<Node>> graph;

	private int chkDiffDepthFromRoot(Node node, int count) {
		int tmp = count;
		for (Connection e : connections)
			if (e.isTo(node) && !e.isFrom(null)) {
				int p = chkDiffDepthFromRoot(e.getFrom(), count - 1), c = chkDiffDepthFromRoot(
						e.getTo(), count + 1);
				if (tmp > (p < c ? p : c))
					tmp = p < c ? p : c;
			}

		return tmp;
	}

	private Node searchRoot() {
		Node n = null;
		for (Node e : nodes)
			if (chkDiffDepthFromRoot(e, 0) == 0)
				n = e;

		return n;
	}

	private int chkDepth(Node rootNode, int count) {
		int tmp = count;
		for (Connection e : connections) {
			if (e.isFrom(rootNode) && !e.isTo(null)) {
				int t = chkDepth(e.getTo(), count + 1);
				if (tmp < t)
					tmp = t;
			}
		}

		return tmp;
	}

	// NOTE 입력된 맵데이터가 반드시 좌에서 우로 위에서 아래로 정렬되어 있다는 가정하에 작성
	public void genGraph() {
		graph = new ArrayList<ArrayList<Node>>();

		for (int i = 0; i < chkDepth(searchRoot(), 0); i++) {
			ArrayList<Node> layer = new ArrayList<Node>();
			for (Node e : nodes)
				if (chkDiffDepthFromRoot(e, i) == 0)
					layer.add(e);
			graph.add(layer);
		}
	}
}
