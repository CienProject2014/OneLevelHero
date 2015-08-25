package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.util.JsonParser;

public class UnitAssets implements JsonAssetsInitializable {
	private Map<String, Hero> heroMap;
	private Map<String, Monster> monsterMap;

	public void set(Map<String, String> jsonStringMap) {
		heroMap = JsonParser.parseMap(Hero.class, jsonStringMap.get(JsonEnum.HERO_JSON.toString()));
		monsterMap = JsonParser.parseMap(Monster.class, jsonStringMap.get(JsonEnum.MONSTER_JSON.toString()));
	}

	public Hero getHero(String unitString) {
		return heroMap.get(unitString);
	}

	public Monster getMonster(String monsterString) {
		return monsterMap.get(monsterString);
	}
}
