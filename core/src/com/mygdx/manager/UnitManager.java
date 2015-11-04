package com.mygdx.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.mygdx.assets.ItemAssets;
import com.mygdx.assets.SkillAssets;
import com.mygdx.enums.ItemEnum;
import com.mygdx.model.battle.Skill;
import com.mygdx.model.item.Item;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Inventory;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.Status;
import com.mygdx.model.unit.Unit;
import com.mygdx.unitStrategy.HeroBattleStrategy;
import com.mygdx.unitStrategy.InventoryStrategy;
import com.mygdx.unitStrategy.MonsterBattleStrategy;

public class UnitManager {
	private static final String EMPTY_ITEM = "empty_item";
	@Autowired
	private SkillAssets skillAssets;
	@Autowired
	private InventoryStrategy inventoryStrategy;
	@Autowired
	private HeroBattleStrategy heroBattleStrategy;
	@Autowired
	private MonsterBattleStrategy monsterBattleStrategy;
	@Autowired
	private ItemAssets itemAssets;

	public void initiateHero(Hero hero) {
		intiallyEquipAllItems(hero);
		setSkills(hero);
		setAttackStrategy(hero);
	}

	public void setAttackStrategy(Unit unit) {
		if (unit instanceof Hero) {
			unit.setBattleStrategy(heroBattleStrategy);
		} else {
			unit.setBattleStrategy(monsterBattleStrategy);
		}
	}

	public void initiateMonster(Monster monster) {
		setAttackStrategy(monster);
		setDropItemList(monster);
	}

	private void setDropItemList(Monster monster) {
		Iterator<Entry<String, ItemEnum>> dropItemListIterator = monster.getDropItemList().entrySet().iterator();
		ArrayList<Item> items = new ArrayList<>();
		while (dropItemListIterator.hasNext()) {
			Entry<String, ItemEnum> dropItemEntry = dropItemListIterator.next();
			Item item;
			switch (dropItemEntry.getValue()) {
				case HANDGRIP :
					item = itemAssets.getHandGrip(dropItemEntry.getKey());
					break;
				case CLOTHES :
					item = itemAssets.getClothes(dropItemEntry.getKey());
					break;
				case ACCESSORY :
					item = itemAssets.getAccessory(dropItemEntry.getKey());
					break;
				case ETC_ITEM :
					item = itemAssets.getEtcItem(dropItemEntry.getKey());
					break;
				default :
					Gdx.app.log("UnitManager", "item정보 오류 - " + dropItemEntry.getKey());
					item = null;
					break;
			}
			items.add(item);
		}
		monster.setDropItems(items);
	}

	public void intiallyEquipAllItems(Hero hero) {
		hero.setInventory(new Inventory());
		hero.setInventoryStrategy(inventoryStrategy);
		Map<String, String> inventories = hero.getInitialInventoryList();

		if (inventories.get("accessory") != null) {
			hero.equipAccessory(inventories.get("accessory"));
		} else {
			hero.equipAccessory(EMPTY_ITEM);
		}
		if (inventories.get("clothes") != null) {
			hero.equipClothes(inventories.get("clothes"));
		} else {
			hero.equipClothes(EMPTY_ITEM);
		}
		if (inventories.get("rightHandGrip") != null) {
			hero.equipRightHandGrip(inventories.get("rightHandGrip"));
		} else {
			hero.equipRightHandGrip(EMPTY_ITEM);
		}
		if (inventories.get("leftHandGrip") != null) {
			hero.equipLeftHandGrip(inventories.get("leftHandGrip"));
		} else {
			hero.equipLeftHandGrip(EMPTY_ITEM);
		}
	}

	private void setSkills(Unit unit) {
		if (unit.getSkills() == null && unit.getSkillList() != null) {
			List<Skill> skills = new ArrayList<>();
			for (String skillName : unit.getSkillList()) {
				Skill skill = skillAssets.getSkill(skillName);
				skill.setSkillPath(skillName);
				skills.add(skill);
			}
			unit.setSkills(skills);
		}
	}

	public void addStatus(Hero hero, Status plusStatus) {
		Status heroStatus = hero.getStatus();
		if (plusStatus != null) {
			heroStatus.setAttack(heroStatus.getAttack() + plusStatus.getAttack());
			heroStatus.setDefense(heroStatus.getDefense() + plusStatus.getDefense());
			heroStatus.setElectricResistance(heroStatus.getElectricResistance() + plusStatus.getElectricResistance());
			heroStatus.setFireResistance(heroStatus.getFireResistance() + plusStatus.getFireResistance());
			heroStatus.setHp(heroStatus.getHp() + plusStatus.getHp());
			heroStatus.setMagicAttack(heroStatus.getMagicAttack() + plusStatus.getMagicAttack());
			heroStatus.setMagicDefense(heroStatus.getMagicDefense() + plusStatus.getMagicDefense());
			heroStatus.setSpeed(heroStatus.getSpeed() + plusStatus.getSpeed());
		}
	}

	public void removeStatus(Hero hero, Status removeStatus) {
		Status heroStatus = hero.getStatus();
		heroStatus.setAttack(heroStatus.getAttack() - removeStatus.getAttack());
		heroStatus.setDefense(heroStatus.getDefense() - removeStatus.getDefense());
		heroStatus.setElectricResistance(heroStatus.getElectricResistance() - removeStatus.getElectricResistance());
		heroStatus.setFireResistance(heroStatus.getFireResistance() - removeStatus.getFireResistance());
		heroStatus.setHp(heroStatus.getHp() - removeStatus.getHp());
		heroStatus.setMagicAttack(heroStatus.getMagicAttack() - removeStatus.getMagicAttack());
		heroStatus.setMagicDefense(heroStatus.getMagicDefense() + removeStatus.getMagicDefense());
		heroStatus.setSpeed(heroStatus.getSpeed() - removeStatus.getSpeed());
	}
}
