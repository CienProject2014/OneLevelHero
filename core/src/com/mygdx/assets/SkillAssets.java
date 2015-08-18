package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.battle.UnusualCondition;
import com.mygdx.util.JsonParser;

public class SkillAssets implements JsonAssetsInitializable {
	private Map<String, Skill> skillMap;
	private Map<String, UnusualCondition> unusualConditionMap;

	@Override
	public void set(Map<String, String> jsonStringMap) {
		skillMap = JsonParser.parseMap(Skill.class, jsonStringMap.get(JsonEnum.SKILL_JSON.toString()));
		unusualConditionMap = JsonParser.parseMap(UnusualCondition.class,
				jsonStringMap.get(JsonEnum.UNUSUAL_CONDITION_JSON.toString()));
	}

	public Skill getSkill(String skillName) {
		return skillMap.get(skillName);
	}

	public UnusualCondition getUnusualCondition(String unusualConditionName) {
		return unusualConditionMap.get(unusualConditionName);
	}
}
