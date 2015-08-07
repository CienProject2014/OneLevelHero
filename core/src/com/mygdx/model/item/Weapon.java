package com.mygdx.model.item;

import java.util.ArrayList;

import com.mygdx.enums.WeaponEnum;
import com.mygdx.enums.WeaponEnum.AttackType;

public class Weapon extends HandGrip {
	private WeaponEnum.WeaponType weaponType;
	private String elementType;
	private int hitboxSize;
	private ArrayList<WeaponEnum.AttackType> attackTypeList;

	public int getHitboxSize() {
		return hitboxSize;
	}

	public void setHitboxSize(int hitboxSize) {
		this.hitboxSize = hitboxSize;
	}

	public WeaponEnum.WeaponType getWeaponType() {
		return weaponType;
	}

	public void setWeaponType(WeaponEnum.WeaponType weaponType) {
		this.weaponType = weaponType;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public ArrayList<AttackType> getAttackTypeList() {
		return attackTypeList;
	}

	public void setAttackTypeList(ArrayList<AttackType> attackTypeList) {
		this.attackTypeList = attackTypeList;
	}
}
