package com.mygdx.manager;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.mygdx.assets.ItemAssets;
import com.mygdx.currentState.BagInfo;
import com.mygdx.enums.ItemEnum;
import com.mygdx.model.item.Consumables;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.item.Item;

public class BagManager {
	@Autowired
	private ItemAssets itemAssets;
	@Autowired
	private BagInfo bagInfo;

	public void possessItem(ItemEnum itemType, String itemName) {
		Item item = new Item();
		switch (itemType) {
			case HANDGRIP :
				item = itemAssets.getHandGrip(itemName);
				// item = (Equipment)
				// deepClone(itemAssets.getHandGrip(itemName));
				addEquipment((Equipment) item);
				break;
			case ACCESSORY :
				item = itemAssets.getAccessory(itemName);
				// item = (Equipment)
				// deepClone(itemAssets.getHandGrip(itemName));
				addEquipment((Equipment) item);
				break;
			case CLOTHES :
				item = itemAssets.getClothes(itemName);
				// item = (Equipment)
				// deepClone(itemAssets.getClothes(itemName));
				addEquipment((Equipment) item);
				break;
			case CONSUMABLES :
				item = itemAssets.getConsumables(itemName);
				addConsumables((Consumables) item);
				break;
			case ETC_ITEM :
				item = itemAssets.getEtcItem(itemName);
				// item = (Item) deepClone(itemAssets.getEtcItem(itemName));
				addEtcItem(item);
				break;
			default :
				Gdx.app.log("BagManager", "itemType 정보 오류");
				break;
		}
	}
	public void possessItem(ItemEnum itemType, String itemName, int amount) {
		Consumables item;
		switch (itemType) {
			case CONSUMABLES :
				item = itemAssets.getConsumables(itemName);
				item.setAmount(item.getAmount() + amount);
				possessItem(itemType, itemName);
				break;
			default :
				possessItem(itemType, itemName);
				break;
		}
	}

	public void dropItem(Item item) {
		Gdx.app.log("BagManager", item.getName() + "를 버렸다.");
		if (item instanceof Equipment) {
			removeEquipment((Equipment) item);
		} else if (item instanceof Consumables) {
			removeConsumables((Consumables) item);
		} else {
			removeEtcItem(item);
		}
	}

	public void addEquipment(Equipment equipment) {
		getEquipmentList().add(equipment);
	}

	public void removeEquipment(Equipment equipment) {
		getEquipmentList().remove(equipment);
	}

	public List<Equipment> getEquipmentList() {
		return bagInfo.getEquipmentList();
	}

	public List<Consumables> getConsumablesList() {
		return bagInfo.getConsumablesList();
	}

	public void removeConsumables(Consumables consumables) {
		getConsumablesList().remove(consumables);
	}

	public void addConsumables(Consumables consumables) {
		if (!getConsumablesList().contains(consumables)) {
			getConsumablesList().add(consumables);
		}
	}

	public List<Item> getEtcItemList() {
		return bagInfo.getEtcItemList();
	}

	public void removeEtcItem(Item item) {
		getEtcItemList().remove(item);
	}

	public void addEtcItem(Item item) {
		getEtcItemList().add(item);
	}

	public Object deepClone(Object object) {
		Kryo kryo = new Kryo();
		kryo.register(Item.class);
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(baos);
			Output output = new Output(oos);
			kryo.writeObject(output, object);
			output.close();
			ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
			Input input = new Input(new ObjectInputStream(bais));
			object = kryo.readObject(input, Item.class);
			input.close();
			return object;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public void putItemInBag(Item item) {
		switch (item.getItemType()) {
			case ACCESSORY :
			case CLOTHES :
			case HANDGRIP :
				getEquipmentList().add((Equipment) item);
				break;
			case CONSUMABLES :
				getConsumablesList().add((Consumables) item);
				break;
			case ETC_ITEM :
				getEtcItemList().add(item);
				break;
			default :
				Gdx.app.log("BattleManager", "itemType정보 오류 - " + item.getItemType());
				break;
		}
	}
}
