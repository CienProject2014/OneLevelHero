package com.mygdx.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Dungeon {
	public ArrayList<Node> nodes;
	public ArrayList<Connection> connections;

	public class Node {
		public final static int FLG_NULL = 0;
		public final static int FLG_ENTRANCE = (1 << 0);
		public final static int FLG_ROAD = (1 << 1);
		public final static int FLG_ENCOUNT = (1 << 2);
		public final static int FLG_TRESURE = (1 << 3) | FLG_ROAD;
		public final static int FLG_BOSS = (1 << 4) | FLG_ENCOUNT;

		private final static String TAG_LABEL = "label";
		private final static String TAG_FLAG = "flag";

		public HashMap<String, Object> data;

		public boolean chkFlag(int flg) {
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

	public class Connection {
		private final static String TAG_LABEL = "label";
		private final static String TAG_FROM = "from";
		private final static String TAG_TO = "to";

		public HashMap<String, Object> data;

		public boolean isFrom(Node node) {
			return node == getFrom();
		}

		public boolean isTo(Node node) {
			return node == getTo();
		}

		public String getLabel() {
			return (String) this.data.get(TAG_LABEL);
		}

		public void setLabel(String label) {
			this.data.put(TAG_LABEL, label);
		}

		public Node getFrom() {
			return (Node) this.data.get(TAG_FROM);
		}

		public void setFrom(Node from) {
			this.data.put(TAG_FROM, from);
		}

		public Node getTo() {
			return (Node) this.data.get(TAG_TO);
		}

		public void setTo(Node to) {
			this.data.put(TAG_TO, to);
		}
	}
}
