package com.mygdx.listener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.enums.EquipmentPositionEnum;
import com.mygdx.model.item.Accessory;
import com.mygdx.model.item.Clothes;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.unit.Hero;

public class EquipButtonListener extends ClickListener {
	private EquipmentPositionEnum equipmentPositionEnum;
	private Hero currentSelectedHero;
	private Equipment equipment;

	@Override
	public void clicked(InputEvent event, float x, float y) {
		switch (equipmentPositionEnum) {
			case LEFT_HAND :
				if (currentSelectedHero.getInventory().isLeftHandGripUsable(equipment)) {
					currentSelectedHero.getInventory().setLeftHandGrip(equipment);
				} else {

				}
				break;
			case RIGHT_HAND :
				if (currentSelectedHero.getInventory().isRightHandGripUsable(equipment)) {
					currentSelectedHero.getInventory().setRightHandGrip(equipment);
				} else {

				}
			case ACCESSORY :
				currentSelectedHero.getInventory().setAccessory((Accessory) equipment);
				break;
			case CLOTHES :
				currentSelectedHero.getInventory().setClothes((Clothes) equipment);
				break;
			default :
				Gdx.app.log("EquiptButtonPopup", "EquipmentPositionEnum 정보 오류");
				break;
		}
	}
	public EquipmentPositionEnum getEquipmentPositionEnum() {
		return equipmentPositionEnum;
	}

	public void setEquipmentPositionEnum(EquipmentPositionEnum equipmentPositionEnum) {
		this.equipmentPositionEnum = equipmentPositionEnum;
	}

	public Hero getCurrentSelectedHero() {
		return currentSelectedHero;
	}

	public void setCurrentSelectedHero(Hero currentSelectedHero) {
		this.currentSelectedHero = currentSelectedHero;
	}
}
