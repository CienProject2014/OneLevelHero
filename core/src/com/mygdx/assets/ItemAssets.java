package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.item.Item;
import com.mygdx.util.JsonParser;

public class ItemAssets implements JsonAssetsInitializable {
	private Map<String, Item> itemMap;

	@Override
	public void set(Map<String, String> jsonStringMap) {
		itemMap = JsonParser.parseMap(Item.class,
				jsonStringMap.get(JsonEnum.ITEM_JSON.toString()));
	}

	public Item getItem(String itemName) {
		return itemMap.get(itemName);
	}
}
