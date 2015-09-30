package com.mygdx.eventTrigger;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.ItemAssets;
import com.mygdx.manager.BagManager;
import com.mygdx.model.event.EventParameters;
import com.mygdx.model.item.Consumables;
import com.mygdx.model.item.Equipment;
import com.mygdx.model.item.Item;

public class GetItemEventTrigger implements EventTrigger {
	@Autowired
	private ItemAssets itemAssets;
	@Autowired
	private BagManager bagManager;
	@Override
	public void triggerEvent(EventParameters eventParameter) {
		switch (eventParameter.getItem().getItemType()) {
			case HANDGRIP :
				Equipment handgrip = itemAssets.getHandGrip(eventParameter.getItem().getItemPath());
				bagManager.addEquipment(handgrip);
				break;
			case ACCESSORY :
				Equipment accessory = itemAssets.getAccessory(eventParameter.getItem().getItemPath());
				bagManager.addEquipment(accessory);
				break;
			case CLOTHES :
				Equipment clothes = itemAssets.getClothes(eventParameter.getItem().getItemPath());
				bagManager.addEquipment(clothes);
				break;
			case CONSUMABLES :
				for (int i = 0; i < eventParameter.getItem().getItemCount(); i++) {
					Consumables item = itemAssets.getConsumables(eventParameter.getItem().getItemPath());
					bagManager.addConsumables(item);
				}
				break;
			case ETC_ITEM :
				for (int i = 0; i < eventParameter.getItem().getItemCount(); i++) {
					Item item = itemAssets.getEtcItem(eventParameter.getItem().getItemPath());
					bagManager.addEtcItem(item);
				}
				break;
			default :
				Gdx.app.log("GetItemEventTrigger", "그럴리가없잖아? ");
				break;
		}
	}
}
