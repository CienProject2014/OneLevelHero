package com.mygdx.model.unit;

import java.util.Arrays;
import java.util.List;

import com.badlogic.gdx.Gdx;

public class Status {
	private float attack;
	private float magicAttack;
	private float defense;
	private float magicDefense;
	private int level;
	private int hp; // 현재 체력
	private int maxHp; // 최대 체력
	private float speed; // speed
	private int experience; // 경험치
	private int maxExperience; // 최대 경험치(레벨업)
	private int fireResistance; // fire resistance
	private int waterResistance; // water
	private int electricResistance; // electric
	private int casting;

	public String getHpState() {
		return hp + "/" + maxHp;
	}

	public String getExperiencePointState() {
		return experience + "/" + maxExperience;
	}

	public float getAttack() {
		return attack;
	}

	public void setAttack(float attack) {
		this.attack = attack;
	}

	public float getMagicAttack() {
		return magicAttack;
	}

	public void setMagicAttack(float magicAttack) {
		this.magicAttack = magicAttack;
	}

	public float getDefense() {
		return defense;
	}

	public void setDefense(float defense) {
		this.defense = defense;
	}

	public float getMagicDefense() {
		return magicDefense;
	}

	public void setMagicDefense(float magicDefense) {
		this.magicDefense = magicDefense;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int healthPoint) {
		this.hp = healthPoint;
	}

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHealthPoint) {
		this.maxHp = maxHealthPoint;
	}

	public int getSpeed() {
		return (int) speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
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

	public int getFireResistance() {
		return fireResistance;
	}

	public int getWaterResistance() {
		return waterResistance;
	}

	public int getElectricResistance() {
		return electricResistance;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public void setFireResistance(int fireResistance) {
		this.fireResistance = fireResistance;
	}

	public void setWaterResistance(int waterResistance) {
		this.waterResistance = waterResistance;
	}

	public void setElectricResistance(int electricResistance) {
		this.electricResistance = electricResistance;
	}

	// alphabet order
	public List<String> getStatusList() {
		return Arrays.asList(new String[] { String.valueOf((int) attack), String.valueOf((int) defense),
				String.valueOf((int) electricResistance), getExperiencePointState(),
				String.valueOf((int) fireResistance), getHpState(), String.valueOf((int) level),
				String.valueOf((int) magicAttack), String.valueOf((int) magicDefense), String.valueOf((int) speed),
				String.valueOf((int) waterResistance) });
	}

	public String getStatusMarkByName(String statusType) {
		switch (statusType) {
		case "attack":
			return "공격력";
		case "defense":
			return "방어력";
		case "electricResistance":
			return "전기 저항";
		case "fireResistance":
			return "불 저항";
		case "level":
			return "레벨";
		case "magicAttack":
			return "주문력";
		case "magicDefense":
			return "항마력";
		case "speed":
			return "민첩성";
		case "waterResistance":
			return "물 저항";
		default:
			Gdx.app.log("Status", "StatusType정보 오류");
			return null;
		}
	}

	public int getStatusByName(String statusType) {
		switch (statusType) {
		case "attack":
			return (int) attack;
		case "defense":
			return (int) defense;
		case "electricResistance":
			return electricResistance;
		case "fireResistance":
			return fireResistance;
		case "level":
			return level;
		case "magicAttack":
			return (int) magicAttack;
		case "magicDefense":
			return (int) magicDefense;
		case "speed":
			return (int) speed;
		case "waterResistance":
			return waterResistance;
		default:
			Gdx.app.log("Status", "StatusType정보 오류");
			return 0;
		}
	}

	public int getCasting() {
		return casting;
	}

	public void setCasting(int casting) {
		this.casting = casting;
	}
}
