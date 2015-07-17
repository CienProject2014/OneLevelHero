package com.mygdx.model;

public class Skill {
	private String name; // 스킬 이름
	private int cost; // 행동력 소모량
	private String definition; // 스킬 설명

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDefinition() {
		return definition;
	}

	public void setDefinition(String definition) {
		this.definition = definition;
	}

	public String[] getSkillList() {
		String[] array = { name, String.valueOf(cost), definition };

		return array;
	}
}
