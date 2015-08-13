package com.mygdx.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.ItemAssets;
import com.mygdx.assets.SkillAssets;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.item.Accessory;
import com.mygdx.model.item.Clothes;
import com.mygdx.model.item.HandGrip;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Inventory;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Status;
import com.mygdx.model.unit.Unit;

public class UnitManager {
	@Autowired
	private ItemAssets itemAssets;
	@Autowired
	private SkillAssets skillAssets;

	public void initiateHero(Hero hero) {
		equipAllItems(hero);
		setSkills(hero);
	}

	public void initiateMonster(Monster monster) {
		setSkills(monster);
	}

	public void equipAllItems(Hero hero) {
		if (hero.getInventory() == null
				&& hero.getInitialInventoryList() != null) {
			Map<String, String> inventoryMap = hero.getInitialInventoryList();
			hero.setInventory(new Inventory());
			if (inventoryMap.get("accessory") != null) {
				equipAccessory(hero, inventoryMap.get("accessory"));
			}
			if (inventoryMap.get("clothes") != null) {
				equipClothes(hero, inventoryMap.get("clothes"));
			}
			if (inventoryMap.get("rightHandGrip") != null) {
				equipRightHandGrip(hero, inventoryMap.get("rightHandGrip"));
			}
			if (inventoryMap.get("leftHandGrip") != null) {
				equipLeftHandGrip(hero, inventoryMap.get("leftHandGrip"));
			}

		}
	}

	private void equipRightHandGrip(Hero hero, String rightHandGripName) {
		HandGrip rightHandGrip = itemAssets.getHandGrip(rightHandGripName);
		hero.getInventory().setRightHandGrip(rightHandGrip);
		Gdx.app.log("UnitManager",
				hero.getName() + "은(는) " + rightHandGrip.getName()
						+ "을(를) 장착하였다.");
		addStatus(hero, rightHandGrip.getEffectStatus());
	}

	private void equipLeftHandGrip(Hero hero, String leftHandGripName) {
		HandGrip leftHandGrip = itemAssets.getHandGrip(leftHandGripName);
		hero.getInventory().setLeftHandGrip(leftHandGrip);
		Gdx.app.log("UnitManager",
				hero.getName() + "은(는) " + leftHandGrip.getName()
						+ "을(를) 장착하였다.");
		addStatus(hero, leftHandGrip.getEffectStatus());
	}

	private void equipClothes(Hero hero, String clothesName) {
		Clothes clothes = itemAssets.getClothes(clothesName);
		hero.getInventory().setClothes(clothes);
		Gdx.app.log("UnitManager", hero.getName() + "은(는) " + clothes.getName()
				+ "을(를) 입었다.");
		addStatus(hero, clothes.getEffectStatus());
	}

	private void equipAccessory(Hero hero, String accessoryName) {
		Accessory accessory = itemAssets.getAccessory(accessoryName);
		hero.getInventory().setAccessory(accessory);
		Gdx.app.log("UnitManager",
				hero.getName() + "은(는) " + accessory.getName() + "을(를) 장착하였다.");
		addStatus(hero, accessory.getEffectStatus());
	}

	private void setSkills(Unit unit) {
		if (unit.getSkills() == null && unit.getSkillList() != null) {
			Map<String, Skill> skills = new HashMap<>();
			for (String skillName : unit.getSkillList()) {
				skills.put(skillName, skillAssets.getSkill(skillName));
			}
			unit.setSkills(skills);
		}
	}

	public void addStatus(Hero hero, Status plusStatus) {
		Status heroStatus = hero.getStatus();
		heroStatus.setAttack(heroStatus.getAttack() + plusStatus.getAttack());
		heroStatus
				.setDefense(heroStatus.getDefense() + plusStatus.getDefense());
		heroStatus.setElectricResistance(heroStatus.getElectricResistance()
				+ plusStatus.getElectricResistance());
		heroStatus.setFireResistance(heroStatus.getFireResistance()
				+ plusStatus.getFireResistance());
		heroStatus.setHealthPoint(heroStatus.getHealthPoint()
				+ plusStatus.getHealthPoint());
		heroStatus.setMagicAttack(heroStatus.getMagicAttack()
				+ plusStatus.getMagicAttack());
		heroStatus.setMagicDefense(heroStatus.getMagicDefense()
				+ plusStatus.getMagicDefense());
		heroStatus.setSpeed(heroStatus.getSpeed() + plusStatus.getSpeed());
	}
}
