package com.mygdx.assets;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.badlogic.gdx.Gdx;
import com.mygdx.enums.JsonEnum;
import com.mygdx.model.item.Accessory;
import com.mygdx.model.item.Clothes;
import com.mygdx.model.item.Consumables;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.item.Item;
import com.mygdx.model.item.Shield;
import com.mygdx.model.item.Weapon;
import com.mygdx.util.JsonParser;

public class ItemAssets implements JsonAssetsInitializable {
	private static final String EMPTY_ITEM = "empty_item";
	private Map<String, Weapon> weaponMap;
	private Map<String, Accessory> accessoryMap;
	private Map<String, Clothes> clothesMap;
	private Map<String, Shield> shieldMap;
	private Map<String, Consumables> consumablesMap;
	private Map<String, Item> etcItemMap;

	@Override
	public void set(Map<String, String> jsonStringMap) {
		weaponMap = JsonParser.parseMap(Weapon.class, jsonStringMap.get(String.valueOf(JsonEnum.WEAPON_JSON)));
		accessoryMap = JsonParser.parseMap(Accessory.class, jsonStringMap.get(String.valueOf(JsonEnum.ACCESSORY_JSON)));
		clothesMap = JsonParser.parseMap(Clothes.class, jsonStringMap.get(String.valueOf(JsonEnum.CLOTHES_JSON)));
		shieldMap = JsonParser.parseMap(Shield.class, jsonStringMap.get(String.valueOf(JsonEnum.SHIELD_JSON)));
		consumablesMap = JsonParser.parseMap(Consumables.class,
				jsonStringMap.get(String.valueOf(JsonEnum.CONSUMABLES_JSON)));
		etcItemMap = JsonParser.parseMap(Item.class, jsonStringMap.get(String.valueOf(JsonEnum.ETC_ITEM_JSON)));
	}

	public Consumables getConsumables(String consumableName) {
		return consumablesMap.get(consumableName);
	}

	public Item getEtcItem(String etcItemName) {
		return etcItemMap.get(etcItemName);
	}

	public Weapon getWeapon(String weaponName) {
		return weaponMap.get(weaponName);
	}

	public Accessory getAccessory(String accessoryName) {
		return accessoryMap.get(accessoryName);
	}

	public Equipment getHandGrip(String handGripName) {
		Iterator<Entry<String, Weapon>> weaponIterator = weaponMap.entrySet().iterator();
		Iterator<Entry<String, Shield>> shieldIterator = shieldMap.entrySet().iterator();
		while (weaponIterator.hasNext()) {
			Entry<String, Weapon> nextWeaponEntry = weaponIterator.next();
			if (nextWeaponEntry.getKey().equals(handGripName)) {
				return nextWeaponEntry.getValue();
			}
		}
		while (shieldIterator.hasNext()) {
			Entry<String, Shield> nextShieldEntry = shieldIterator.next();
			if (nextShieldEntry.getKey().equals(handGripName)) {
				return nextShieldEntry.getValue();
			}
		}
		Gdx.app.log("ItemAssets", "적합한 HandGrip 탐색 실패");
		return getHandGrip(EMPTY_ITEM);
	}

	public Shield getShield(String shieldName) {
		return shieldMap.get(shieldName);
	}

	public Clothes getClothes(String clothesName) {
		return clothesMap.get(clothesName);
	}
}
