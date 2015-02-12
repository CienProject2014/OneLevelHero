package com.mygdx.currentState;

import java.util.List;

import com.mygdx.model.Monster;

public class MovingInfo {
	private String startNode; //무빙 시작 노드		
	private String destinationNode; //무빙 목표 노드		
	private int roadLength; // 총 길 개수
	private int leftRoadLength; //남은 길의 개수
	private List<String> roadMonsterList; //해당 길에 서식하는 몬스터 리스트
	private Monster selectedMonster; // 추첨된 몬스터
	private String arrowName;

	public String getArrowName() {
		return arrowName;
	}

	public void setArrowName(String arrowName) {
		this.arrowName = arrowName;
	}

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
