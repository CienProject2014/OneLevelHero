package com.mygdx.model;

public class Status {
	private int attack;
	private int magicAttack;
	private int defense;
	private int magicDefense;
	private int healthPoint; // 현재 체력
	private int maxHealthPoint; // 최대 체력
	private int speed; // speed
	private int fatigue; // fatigue(피로도)
	private int experience; // 경험치
	private int maxExperience; // 최대 경험치(레벨업)
	private float fireRegistance; // fire resistance
	private float waterRegistance; // water
	private float earthRegistance; // earths
	private String job;

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getMagicAttack() {
		return magicAttack;
	}

	public void setMagicAttack(int magicAttack) {
		this.magicAttack = magicAttack;
	}

	public int getDefense() {
		return defense;
	}

	public void setDefense(int defense) {
		this.defense = defense;
	}

	public int getMagicDefense() {
		return magicDefense;
	}

	public void setMagicDefense(int magicDefense) {
		this.magicDefense = magicDefense;
	}

	public int getHealthPoint() {
		return healthPoint;
	}

	public void setHealthPoint(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public int getMaxHealthPoint() {
		return maxHealthPoint;
	}

	public void setMaxHealthPoint(int maxHealthPoint) {
		this.maxHealthPoint = maxHealthPoint;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getFatigue() {
		return fatigue;
	}

	public void setFatigue(int fatigue) {
		this.fatigue = fatigue;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getMaxExperience() {
		return maxExperience;
	}

	public void setMaxExperience(int maxExperience) {
		this.maxExperience = maxExperience;
	}

	public float getFireRegistance() {
		return fireRegistance;
	}

	public void setFireRegistance(float fireRegistance) {
		this.fireRegistance = fireRegistance;
	}

	public float getWaterRegistance() {
		return waterRegistance;
	}

	public void setWaterRegistance(float waterRegistance) {
		this.waterRegistance = waterRegistance;
	}

	public float getEarthRegistance() {
		return earthRegistance;
	}

	public void setEarthRegistance(float earthRegistance) {
		this.earthRegistance = earthRegistance;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String[] getStatusList() {
		String[] array = { String.valueOf(attack), String.valueOf(magicAttack),
				String.valueOf(defense), String.valueOf(magicDefense),
				String.valueOf(fatigue), String.valueOf(healthPoint),
				String.valueOf(speed), String.valueOf(experience),
				String.valueOf(fireRegistance),
				String.valueOf(waterRegistance),
				String.valueOf(earthRegistance), job };

		return array;
	}
}
