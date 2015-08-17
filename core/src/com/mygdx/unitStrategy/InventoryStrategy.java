package com.mygdx.unitStrategy;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.ItemAssets;
import com.mygdx.manager.BagManager;
import com.mygdx.manager.UnitManager;
import com.mygdx.model.item.Accessory;
import com.mygdx.model.item.Clothes;
import com.mygdx.model.item.HandGrip;
import com.mygdx.model.unit.Hero;

public class InventoryStrategy {
	@Autowired
	private ItemAssets itemAssets;
	@Autowired
	private UnitManager unitManager;
	@Autowired
	private BagManager bagManager;
	public final String EMPTY_ITEM = "empty_item";

	public void equipRightHandGrip(Hero hero, String rightHandGripName) {
		HandGrip rightHandGrip = itemAssets.getHandGrip(rightHandGripName);
		hero.getInventory().setRightHandGrip(rightHandGrip);
		Gdx.app.log("UnitManager",
				hero.getName() + "은(는) " + rightHandGrip.getName()
						+ "을(를) 장착하였다.");
		unitManager.addStatus(hero, rightHandGrip.getEffectStatus());
	}

	public void unEquipRightHandGrip(Hero hero) {
		HandGrip emptyRightHandGrip = itemAssets.getHandGrip(EMPTY_ITEM);
		hero.getInventory().setRightHandGrip(emptyRightHandGrip);
		Gdx.app.log("UnitManager", hero.getName() + "은(는)"
				+ hero.getInventory().getRightHandGrip().getName()
				+ "을(를) 장착해제 하였다.");
		unitManager.removeStatus(hero, hero.getInventory().getRightHandGrip()
				.getEffectStatus());
	}
	public void equipLeftHandGrip(Hero hero, String leftHandGripName) {
		HandGrip leftHandGrip = itemAssets.getHandGrip(leftHandGripName);
		hero.getInventory().setLeftHandGrip(leftHandGrip);
		Gdx.app.log("UnitManager",
				hero.getName() + "은(는) " + leftHandGrip.getName()
						+ "을(를) 장착하였다.");
		unitManager.addStatus(hero, leftHandGrip.getEffectStatus());
	}

	public void unEquipLeftHandGrip(Hero hero) {
		HandGrip emptyLeftHandGrip = itemAssets.getHandGrip(EMPTY_ITEM);
		hero.getInventory().setLeftHandGrip(emptyLeftHandGrip);
		Gdx.app.log("UnitManager", hero.getName() + "은(는)"
				+ hero.getInventory().getLeftHandGrip().getName()
				+ "을(를) 장착해제 하였다.");
		unitManager.removeStatus(hero, hero.getInventory().getLeftHandGrip()
				.getEffectStatus());
	}

	public void equipClothes(Hero hero, String clothesName) {
		Clothes clothes = itemAssets.getClothes(clothesName);
		hero.getInventory().setClothes(clothes);
		Gdx.app.log("UnitManager", hero.getName() + "은(는) " + clothes.getName()
				+ "을(를) 입었다.");
		unitManager.addStatus(hero, clothes.getEffectStatus());
	}

	public void unEquipClothes(Hero hero) {
		Clothes emptyClothes = itemAssets.getClothes(EMPTY_ITEM);
		hero.getInventory().setClothes(emptyClothes);
		Gdx.app.log("UnitManager", hero.getName() + "은(는)"
				+ hero.getInventory().getClothes().getName() + "을(를) 벗엇다.");
		unitManager.removeStatus(hero, hero.getInventory().getClothes()
				.getEffectStatus());
	}

	public void equipAccessory(Hero hero, String accessoryName) {
		Accessory accessory = itemAssets.getAccessory(accessoryName);
		hero.getInventory().setAccessory(accessory);
		Gdx.app.log("UnitManager",
				hero.getName() + "은(는) " + accessory.getName() + "을(를) 장착하였다.");
		unitManager.addStatus(hero, accessory.getEffectStatus());
	}

	public void unEquipAccessory(Hero hero) {
		Accessory emptyAccessory = itemAssets.getAccessory(EMPTY_ITEM);
		hero.getInventory().setAccessory(emptyAccessory);
		Gdx.app.log("UnitManager", hero.getName() + "은(는)"
				+ hero.getInventory().getAccessory().getName()
				+ "을(를) 장착해제 하였다.");
		unitManager.removeStatus(hero, hero.getInventory().getAccessory()
				.getEffectStatus());
	}
}
