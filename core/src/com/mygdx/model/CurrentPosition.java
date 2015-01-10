package com.mygdx.model;

public class CurrentPosition {
	private String currentNode; //현재 마을/던전/교차로 이름	
	transient private CurrentMoving currentMoving; //가장 최근의 움직임 이력

	public CurrentPosition() {
		currentMoving = new CurrentMoving();
	}

	public class CurrentMoving {
		private String startNode; //무빙 시작 노드		
		private String destinationNode; //무빙 목표 노드		
		private int roadNumber; // 움직인 길의 번호

		public String getStartNode() {
			return startNode;
		}

		public void setStartNode(String startNode) {
			this.startNode = startNode;
		}

		public String getDestinationNode() {
			return destinationNode;
		}

		public void setDestinationNode(String destinationNode) {
			this.destinationNode = destinationNode;
		}

		public int getRoadNumber() {
			return roadNumber;
		}

		public void setRoadNumber(int roadNumber) {
			this.roadNumber = roadNumber;
		}

	}

	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

	public CurrentMoving getCurrentMoving() {
		return currentMoving;
	}

	public void setCurrentMoving(CurrentMoving currentMoving) {
		this.currentMoving = currentMoving;
	}
}
