package com.mygdx.assets;

import java.util.Map;

import com.mygdx.battle.Buff;
import com.mygdx.battle.Skill;
import com.mygdx.enums.JsonEnum;
import com.mygdx.util.JsonParser;

public class SkillAssets implements JsonAssetsInitializable {
	private Map<String, Skill> skillMap;
	private Map<String, Buff> buffMap;

	@Override
	public void set(Map<String, String> jsonStringMap) {
		skillMap = JsonParser.parseMap(Skill.class, jsonStringMap.get(JsonEnum.SKILL_JSON.toString()));
		buffMap = JsonParser.parseMap(Buff.class, jsonStringMap.get(JsonEnum.BUFF_JSON.toString()));
	}

	public Skill getSkill(String skillName) {
		return skillMap.get(skillName);
	}

	public Buff getBuff(String buff) {
		return buffMap.get(buff);
	}
}
