package com.mygdx.manager;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.ItemAssets;
import com.mygdx.assets.SkillAssets;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.item.Item;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Unit;

public class UnitManager {
	@Autowired
	private ItemAssets itemAssets;
	@Autowired
	private SkillAssets skillAssets;

	public void initiateHero(Hero hero) {
		setItems(hero);
		setSkills(hero);
	}

	public void initiateMonster(Monster monster) {
		setSkills(monster);
	}

	private void setItems(Hero hero) {
		if (hero.getItems() == null && hero.getItemList() != null) {
			Map<String, Item> items = new HashMap<>();
			for (String itemName : hero.getItemList()) {
				items.put(itemName, itemAssets.getItem(itemName));
			}
			hero.setItems(items);
		}
	}

	private void setSkills(Unit unit) {
		if (unit.getSkills() == null && unit.getSkillList() != null) {
			Map<String, Skill> skills = new HashMap<>();
			for (String skillName : unit.getSkillList()) {
				skills.put(skillName, skillAssets.getSkill(skillName));
			}
			unit.setSkills(skills);
		}

	}
}
