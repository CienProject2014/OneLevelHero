package com.mygdx.model.unit;

public class Status {
	private int attack;
	private int magicAttack;
	private int defense;
	private int magicDefense;
	private int level;
	private int healthPoint; // 현재 체력
	private int maxHealthPoint; // 최대 체력
	private int speed; // speed
	private int fatigue; // fatigue(피로도)
	private int experience; // 경험치
	private int maxExperience; // 최대 경험치(레벨업)
	private float fireResistance; // fire resistance
	private float waterResistance; // water
	private float electricResistance; // electric

	public String getHealthPointState() {
		return healthPoint + "/" + maxHealthPoint;
	}

	public String getExperiencePointState() {
		return experience + "/" + maxExperience;
	}

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

	public int getHp() {
		return healthPoint;
	}

	public void setHp(int healthPoint) {
		this.healthPoint = healthPoint;
	}

	public int getMaxHp() {
		return maxHealthPoint;
	}

	public void setMaxHp(int maxHealthPoint) {
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

	public float getFireResistance() {
		return fireResistance;
	}

	public void setFireResistance(float fireResistance) {
		this.fireResistance = fireResistance;
	}

	public float getWaterResistance() {
		return waterResistance;
	}

	public void setWaterResistance(float waterResistance) {
		this.waterResistance = waterResistance;
	}

	public float getElectricResistance() {
		return electricResistance;
	}

	public void setElectricResistance(float electricResistance) {
		this.electricResistance = electricResistance;
	}

	//alphabet order
	public String[] getStatusList() {
		String[] array = { String.valueOf(attack), String.valueOf(defense),
				String.valueOf(electricResistance), getExperiencePointState(),
				String.valueOf(fatigue), String.valueOf(fireResistance),
				getHealthPointState(), String.valueOf(level),
				String.valueOf(magicAttack), String.valueOf(magicDefense),
				String.valueOf(speed), String.valueOf(waterResistance) };
		return array;
	}
}
