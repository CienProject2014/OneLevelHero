package com.mygdx.model;

public class Status {
	private int attack;
	private int defense;
	private int critical;
	private int evade;
	private int accuracy;
	private int healthPoint;
	private int stamina;
	private int speed;
	private String job;
	private String debuff;

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
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDebuff() {
		return debuff;
	}

	public void setDebuff(String debuff) {
		this.debuff = debuff;
	}

	public String toString() {
		return "공격력 : " + getAttack() + " ,방어력 : " + getDefense() + ", 치명타 : "
				+ getCritical() + ", 회피율 : " + getCritical() + ", 명중률 "
				+ ", 체력 : " + getHealthPoint() + ", 스태미나 : " + getStamina()
				+ ", 직업 : " + getJob();
	}
}
