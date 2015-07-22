package com.mygdx.assets;

import java.util.Map;

import com.mygdx.enums.JsonEnum;
import com.mygdx.model.Skill;
import com.mygdx.util.JsonParser;

public class SkillAssets implements JsonAssetsInitializable {
	private Map<String, Skill> skillMap;

	@Override
	public void set(Map<String, String> jsonStringMap) {
		skillMap = JsonParser.parseMap(Skill.class,
				jsonStringMap.get(JsonEnum.SKILL_JSON.toString()));
	}

	public Skill getSkill(String skillName) {
		return skillMap.get(skillName);
	}
}
