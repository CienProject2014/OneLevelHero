package com.mygdx.unitStrategy;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.ItemAssets;
import com.mygdx.enums.ItemEnum;
import com.mygdx.manager.BagManager;
import com.mygdx.manager.UnitManager;
import com.mygdx.model.item.Accessory;
import com.mygdx.model.item.Clothes;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.unit.Hero;

public class InventoryStrategy {
	@Autowired
	private ItemAssets itemAssets;
	@Autowired
	private UnitManager unitManager;
	@Autowired
	private BagManager bagManager;
	public final String EMPTY_ITEM = "empty_item";

	public void equip(Hero hero, ItemEnum equipmentType, String equipmentName) {
		switch (equipmentType) {
			case RIGHT_HANDGRIP :
				equipRightHandGrip(hero, equipmentName);
				break;
			case LEFT_HANDGRIP :
				equipLeftHandGrip(hero, equipmentName);
				break;
			case CLOTHES :
				equipClothes(hero, equipmentName);
				break;
			case ACCESSORY :
				equipAccessory(hero, equipmentName);
				break;
			default :
				Gdx.app.log("InventoryStage", "EquipmentType 정보 오류");
				break;
		}
	}

	public void equipRightHandGrip(Hero hero, String rightHandGripName) {
		Equipment rightHandGrip = itemAssets.getHandGrip(rightHandGripName);
		hero.getInventory().setRightHandGrip(rightHandGrip);
		bagManager.removeEquipment(rightHandGrip);
		Gdx.app.log("UnitManager", hero.getName() + "은(는) " + rightHandGrip.getName() + "을(를) 장착하였다.");
		unitManager.addStatus(hero, rightHandGrip.getEffectStatus());
	}

	public void unEquipRightHandGrip(Hero hero) {
		unitManager.removeStatus(hero, hero.getInventory().getRightHandGrip().getEffectStatus());
		bagManager.addEquipment(hero.getInventory().getRightHandGrip());
		Gdx.app.log("UnitManager", hero.getName() + "은(는)" + hero.getInventory().getRightHandGrip().getName()
				+ "을(를) 장착해제 하였다.");
		Equipment emptyRightHandGrip = itemAssets.getHandGrip(EMPTY_ITEM);
		hero.getInventory().setRightHandGrip(emptyRightHandGrip);
	}

	public void equipLeftHandGrip(Hero hero, String leftHandGripName) {
		Equipment leftHandGrip = itemAssets.getHandGrip(leftHandGripName);
		bagManager.removeEquipment(leftHandGrip);
		hero.getInventory().setLeftHandGrip(leftHandGrip);
		Gdx.app.log("UnitManager", hero.getName() + "은(는) " + leftHandGrip.getName() + "을(를) 장착하였다.");
		unitManager.addStatus(hero, leftHandGrip.getEffectStatus());
	}

	public void unEquipLeftHandGrip(Hero hero) {
		unitManager.removeStatus(hero, hero.getInventory().getLeftHandGrip().getEffectStatus());
		bagManager.addEquipment(hero.getInventory().getLeftHandGrip());
		Gdx.app.log("UnitManager", hero.getName() + "은(는)" + hero.getInventory().getLeftHandGrip().getName()
				+ "을(를) 장착해제 하였다.");
		Equipment emptyLeftHandGrip = itemAssets.getHandGrip(EMPTY_ITEM);
		hero.getInventory().setLeftHandGrip(emptyLeftHandGrip);
	}

	public void equipClothes(Hero hero, String clothesName) {
		Clothes clothes = itemAssets.getClothes(clothesName);
		bagManager.removeEquipment(clothes);
		hero.getInventory().setClothes(clothes);
		Gdx.app.log("UnitManager", hero.getName() + "은(는) " + clothes.getName() + "을(를) 입었다.");
		unitManager.addStatus(hero, clothes.getEffectStatus());
	}

	public void unEquipClothes(Hero hero) {
		unitManager.removeStatus(hero, hero.getInventory().getClothes().getEffectStatus());
		bagManager.addEquipment(hero.getInventory().getClothes());
		Gdx.app.log("UnitManager", hero.getName() + "은(는)" + hero.getInventory().getClothes().getName() + "을(를) 벗엇다.");
		Clothes emptyClothes = itemAssets.getClothes(EMPTY_ITEM);
		hero.getInventory().setClothes(emptyClothes);
	}

	public void equipAccessory(Hero hero, String accessoryName) {
		Accessory accessory = itemAssets.getAccessory(accessoryName);
		bagManager.removeEquipment(accessory);
		hero.getInventory().setAccessory(accessory);
		Gdx.app.log("UnitManager", hero.getName() + "은(는) " + accessory.getName() + "을(를) 장착하였다.");
		unitManager.addStatus(hero, accessory.getEffectStatus());
	}

	public void unEquipAccessory(Hero hero) {
		unitManager.removeStatus(hero, hero.getInventory().getAccessory().getEffectStatus());
		bagManager.addEquipment(hero.getInventory().getAccessory());
		Gdx.app.log("UnitManager", hero.getName() + "은(는)" + hero.getInventory().getAccessory().getName()
				+ "을(를) 장착해제 하였다.");
		Accessory emptyAccessory = itemAssets.getAccessory(EMPTY_ITEM);
		hero.getInventory().setAccessory(emptyAccessory);
	}
}
