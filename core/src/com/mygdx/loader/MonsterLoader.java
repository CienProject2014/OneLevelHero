package com.mygdx.loader;

import com.badlogic.gdx.utils.Json;
import com.mygdx.resource.Assets;
import com.mygdx.unit.Bag;
import com.mygdx.unit.Monster;
import com.mygdx.unit.Status;

public class MonsterLoader {
	Status monsterStatus;
	Bag monsterBag;			// 몬스터의 가방은 아직 구현 안함.
	
	public MonsterLoader() {
		
	}
	
	public Monster load(String monsterID) {
		Json json = new Json();
		Object getMonsterStatus = (Object) Assets.monster_json.get(monsterID);
		monsterStatus = json.fromJson(Status.class, String.valueOf(getMonsterStatus));
		
		return new Monster(monsterID, monsterStatus, null);
	}
}
