package com.mygdx.assets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mygdx.enums.FieldTypeEnum;
import com.mygdx.enums.JsonEnum;
import com.mygdx.model.surroundings.Dungeon;
import com.mygdx.model.surroundings.DungeonEntrance;
import com.mygdx.model.surroundings.Fork;
import com.mygdx.model.surroundings.MonsterField;
import com.mygdx.model.surroundings.Village;
import com.mygdx.util.JsonParser;

public class NodeAssets implements JsonAssetsInitializable {
	public Map<String, Village> villageMap;
	public Map<String, Dungeon> dungeonMap;
	public Map<FieldTypeEnum, ArrayList<String>> monsterFieldMap;
	public Map<String, Fork> forkMap;
	public Map<String, DungeonEntrance> dungeonEntranceMap;

	public void set(Map<String, String> jsonStringMap) {

		villageMap = JsonParser.parseMap(Village.class, jsonStringMap.get(JsonEnum.VILLAGE_JSON.toString()));

		dungeonMap = JsonParser.parseMap(Dungeon.class, jsonStringMap.get(JsonEnum.DUNGEON_JSON.toString()));
		dungeonEntranceMap = JsonParser.parseMap(DungeonEntrance.class,
				jsonStringMap.get(JsonEnum.DUNGEON_ENTRANCE_JSON.toString()));
		forkMap = JsonParser.parseMap(Fork.class, jsonStringMap.get(JsonEnum.FORK_JSON.toString()));

		ArrayList<MonsterField> monsterFieldList = JsonParser.parseList(MonsterField.class,
				jsonStringMap.get(JsonEnum.MONSTER_FIELD_JSON.toString()));
		monsterFieldMap = new HashMap<FieldTypeEnum, ArrayList<String>>();
		for (MonsterField monsterField : monsterFieldList) {
			monsterFieldMap.put(monsterField.getFieldType(), monsterField.getFieldMonsterList());
		}
	}

	public Map<String, DungeonEntrance> getDungeonEntranceMap() {
		return dungeonEntranceMap;
	}

	public DungeonEntrance getDungeonEntranceByName(String dungeonEntranceName) {
		return dungeonEntranceMap.get(dungeonEntranceName);
	}

	public Map<String, Village> getVillageMap() {
		return villageMap;
	}

	public Village getVillageByName(String villageString) {
		return villageMap.get(villageString);
	}

	public Dungeon getDungeonByName(String dungeonString) {
		return dungeonMap.get(dungeonString);
	}

	public List<String> getMonsterFieldListByFieldType(FieldTypeEnum fieldType) {
		return monsterFieldMap.get(fieldType);
	}

	public Map<String, Fork> getForkMap() {
		return forkMap;
	}

	public Fork getForkByName(String forkString) {
		return forkMap.get(forkString);
	}
}
