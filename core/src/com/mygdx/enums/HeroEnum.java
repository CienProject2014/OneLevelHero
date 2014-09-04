package com.mygdx.enums;

import com.mygdx.unit.Hero;

public enum HeroEnum {
	YONGSA("yongsa"), PARATH("parath");
	String heroName = "";

	private HeroEnum(String heroName) {
		this.heroName = heroName;
	}

	Hero newHero() {
		Hero hero = new Hero(heroName);
		return hero;
	}
}
