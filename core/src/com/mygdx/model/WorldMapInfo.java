package com.mygdx.model;

import java.util.List;
import java.util.Map;

public class WorldMapInfo {

	private Map<String, NodeInfo> nodeInfo;
	private Map<String, RodeInfo> rodeInfo;

	public Map<String, RodeInfo> getRodeInfo() {
		return rodeInfo;
	}

	public void setRodeInfo(Map<String, RodeInfo> rodeInfo) {
		this.rodeInfo = rodeInfo;
	}

	public Map<String, NodeInfo> getNodeInfo() {
		return nodeInfo;
	}

	public void setNodeInfo(Map<String, NodeInfo> nodeInfo) {
		this.nodeInfo = nodeInfo;
	}

	public class NodeInfo {

		private String key;
		private String name;
		private String type;
		private int positionX;
		private int positionY;
		private List<Connection> connection;

		public String getKey() {
			return key;
		}

		public void setKey(String key) {
			this.key = key;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public List<Connection> getConnection() {
			return connection;
		}

		public void setConnection(List<Connection> connection) {
			this.connection = connection;
		}

		public int getPositionX() {
			return positionX;
		}

		public void setPositionX(int positionX) {
			this.positionX = positionX;
		}

		public int getPositionY() {
			return positionY;
		}

		public void setPositionY(int positionY) {
			this.positionY = positionY;
		}

		public class Connection {
			private String roadKey;
			private String destination;
			private int direction;

			public String getRoadKey() {
				return roadKey;
			}

			public void setRoadKey(String roadKey) {
				this.roadKey = roadKey;
			}

			public String getDestination() {
				return destination;
			}

			public void setDestination(String destination) {
				this.destination = destination;
			}

			public int getDirection() {
				return direction;
			}

			public void setDirection(int direction) {
				this.direction = direction;
			}
		}

	}

	public class RodeInfo {
		private String name;
		private String background;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getBackground() {
			return background;
		}

		public void setBackground(String background) {
			this.background = background;
		}

		public int getLength() {
			return length;
		}

		public void setLength(int length) {
			this.length = length;
		}

		private int length;

	}
}