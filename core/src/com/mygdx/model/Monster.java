package com.mygdx.model;

import com.mygdx.enums.MonsterEnum;

public class Monster extends LivingUnit implements Fightable {
	private MonsterEnum.TypeEnum type;

	public MonsterEnum.TypeEnum getType() {
		return type;
	}

	public void setType(MonsterEnum.TypeEnum type) {
		this.type = type;
	}

}
