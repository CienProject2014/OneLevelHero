package com.mygdx.model.unit;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.model.battle.Skill;
import com.mygdx.unitStrategy.BattleStrategy;
import com.mygdx.unitStrategy.UnusualConditionStrategy;

public abstract class Unit implements Comparable<Unit> {
	private BattleStrategy battleStrategy;
	private UnusualConditionStrategy unusualConditionStrategy;
	private String facePath;
	private String name;
	protected Status status;
	private List<Skill> skills;
	private int gauge;
	private int subvalue;
	private int actingPower;
	private int preGague;

	public Unit() {
		gauge = 100;
	}

	public String getFacePath() {
		return facePath;
	}

	public void setFacePath(String facePath) {
		this.facePath = facePath;
	}

	/* For Json Work */
	private ArrayList<String> skillList;

	public String getName() {
		return name;
	}

	public ArrayList<String> getSkillList() {
		return skillList;
	}

	public void setSkillList(ArrayList<String> skillList) {
		this.skillList = skillList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public int getGauge() {
		return gauge;
	}

	public void setGauge(int gauge) {
		this.gauge = gauge;
	}

	public int getSubvalue() {
		return subvalue;
	}

	public void setSubvalue(int subvalue) {
		this.subvalue = subvalue;
	}

	public int getActingPower() {
		return actingPower;
	}

	public void setActingPower(int actingPower) {
		this.actingPower = actingPower;
	}

	public void beInUnusualCondition(String unusualConditionName) {
		unusualConditionStrategy.beInUnusualCondition(this, unusualConditionName);
	}

	@Override
	public int compareTo(Unit obj) {
		if (this.getGauge() == obj.getGauge()) {
			if (this.getSubvalue() == obj.getSubvalue()) {
				if (this.getStatus().getSpeed() == obj.getStatus().getSpeed()) {
					if (obj instanceof Monster) {
						// 몬스터는 꼴지!
						return 1;
					} else if (obj.getFacePath() == "yongsa") {
						// 용사는 일등!
						return -1;
					} else {
						// 나머지는 첨 들어갈때 그대로 있어 그냥 어차피 리스트는 순서대로 들어가니 적용댈듯
						return 0;
					}
				} else if (this.getStatus().getSpeed() > obj.getStatus().getSpeed()) {
					// 스피드가 더 크다
					return -1;
				} else {
					// 스피드가 더 작다
					return 1;
				}
			} else if (this.getSubvalue() > obj.getSubvalue()) {
				return 1;
			} else {
				return -1;
			}
		} else if (this.getGauge() > obj.getGauge()) {
			// 행동 게이지가 더 클 때
			return -1;
		} else {
			// 행동 게이지가 더 작을 때
			return 1;
		}
	}

	public int getPreGague() {
		return preGague;
	}

	public void setPreGague(int preGague) {
		this.preGague = preGague;
	}

	public void attack(Unit opponent, int[][] hitArea) {
		battleStrategy.attack(this, opponent, hitArea);
	}

	public void useSkill(ArrayList<Unit> targetList, Skill skill) {
		battleStrategy.skill(this, targetList, skill);
	}

	public BattleStrategy getAttackStrategy() {
		return battleStrategy;
	}

	public void setAttackStrategy(BattleStrategy attackStrategy) {
		this.battleStrategy = attackStrategy;
	}

	public UnusualConditionStrategy getUnusualConditionStrategy() {
		return unusualConditionStrategy;
	}

	public void setUnusualConditionStrategy(UnusualConditionStrategy unusualConditionStrategy) {
		this.unusualConditionStrategy = unusualConditionStrategy;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}
}
