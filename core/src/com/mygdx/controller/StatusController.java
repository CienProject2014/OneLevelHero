package com.mygdx.controller;

import com.mygdx.loader.StatusLoader;

public class StatusController {
	private int attack;
	private int defense;
	private int critical;
	private int evade;
	private int accuracy;
	private int healthPoint;
	private int stamina;
	private int debuff;
	StatusLoader statusLoader;

	StatusController(StatusLoader loader) {
		this.statusLoader = statusLoader();
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getCritical() {
		return critical;
	}

	public void setCritical(int critical) {
		this.critical = critical;
	}

	public int getEvade() {
		return evade;
	}

	public void setEvade(int evade) {
		this.evade = evade;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getDebuff() {
		return debuff;
	}

	public void setDebuff(int debuff) {
		this.debuff = debuff;
	}

}
