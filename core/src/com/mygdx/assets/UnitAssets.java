package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.Hero;
import com.mygdx.model.Monster;
import com.mygdx.model.NPC;
import com.mygdx.util.JsonParser;

public class UnitAssets implements JsonAssetsInitializable {
	private Map<String, Hero> heroMap;
	private Map<String, NPC> npcMap;
	private Map<String, Monster> monsterMap;

	public void set(Map<String, String> jsonStringMap) {
		heroMap = JsonParser.parseMap(Hero.class,
				jsonStringMap.get(JsonEnum.HERO_JSON.toString()));
		npcMap = JsonParser.parseMap(NPC.class,
				jsonStringMap.get(JsonEnum.NPC_JSON.toString()));
		monsterMap = JsonParser.parseMap(Monster.class,
				jsonStringMap.get(JsonEnum.MONSTER_JSON.toString()));
	}

	public NPC getNpc(String npcString) {
		return npcMap.get(npcString);
	}

	public Hero getHero(String unitString) {
		return heroMap.get(unitString);
	}

	public Monster getMonster(String monsterString) {
		return monsterMap.get(monsterString);
	}
}
