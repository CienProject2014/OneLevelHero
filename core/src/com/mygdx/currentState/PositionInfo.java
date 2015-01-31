package com.mygdx.currentState;

import org.springframework.stereotype.Component;

@Component
public class PositionInfo {

	private String currentNode; //현재 마을/던전/교차로 이름	

	public String getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(String currentNode) {
		this.currentNode = currentNode;
	}

}
