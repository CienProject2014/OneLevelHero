package com.mygdx.unitStrategy;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.mygdx.model.item.Item;
import com.mygdx.model.unit.Monster;

public class DropItemStrategy {
	public Item makeDropItem(Monster selectedMonster) {
		Random random = new Random();
		if (selectedMonster.getDropItems().size() != 0) {
			Item item = selectedMonster.getDropItems().get(random.nextInt(selectedMonster.getDropItems().size()));
			Gdx.app.log("DropItemStrategy", "아이템 [" + item.getName() + "]를 획득하였다.");
			return item;
		} else {
			return null; // TODO Don't return null;
		}
	}
}
