package com.mygdx.model;

import java.util.List;

public class CurrentPosition {
	private String currentNode; // 현재 마을/던전/교차로 이름
	transient private CurrentMovingInfo currentMovingInfo; // 가장 최근의 움직임 이력

	public CurrentPosition() {
		currentMovingInfo = new CurrentMovingInfo();
	}

	public class CurrentMovingInfo {
		private String startNode; // 무빙 시작 노드
		private String destinationNode; // 무빙 목표 노드
		private int roadLength; // 총 길 개수
		private int leftRoadLength; // 남은 길의 개수
		private List<String> roadMonsterList; // 해당 길에 서식하는 몬스터 리스트
		private Monster selectedMonster; // 추첨된 몬스터

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

		public int getRoadLength() {
			return roadLength;
		}

		public void setRoadLength(int roadLength) {
			this.roadLength = roadLength;
		}

		public int getLeftRoadLength() {
			return leftRoadLength;
		}

		public void setLeftRoadLength(int leftRoadLength) {
			this.leftRoadLength = leftRoadLength;
		}

		public Monster getSelectedMonster() {
			return selectedMonster;
		}

		public void setSelectedMonster(Monster selectedMonster) {
			this.selectedMonster = selectedMonster;
		}

		public List<String> getRoadMonsterList() {
			return roadMonsterList;
		}

		public void setRoadMonsterList(List<String> roadMonsterList) {
			this.roadMonsterList = roadMonsterList;
		}
	}

	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

	public CurrentMovingInfo getCurrentMovingInfo() {
		return currentMovingInfo;
	}

	public void setCurrentMovingInfo(CurrentMovingInfo currentMovingInfo) {
		this.currentMovingInfo = currentMovingInfo;
	}
}
