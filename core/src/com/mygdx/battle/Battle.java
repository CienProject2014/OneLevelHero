package com.mygdx.battle;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.mygdx.model.Unit;

public class Battle {
	private Random random = new Random();
	private boolean outcome;
	private String dungeonID;

	public Battle() {
	}

	public Battle(String dungeonID) {
		this();
		this.dungeonID = dungeonID;
	}

	public void decideOutcome() {
		// TODO
	}

	private void end() {
		// TODO
	}

	public void victory() {
		Gdx.app.log("정보", "전투승리");
	}

	public void defeat() {
		Gdx.app.log("정보", "전투패배");
	}

	public void attack(Unit attacker, Unit defender) {
		int attackDmg = attacker.getStatus().getAtk();
		int defendDmg = defender.getStatus().getDef();
		int defendHp = defender.getStatus().getHp();
		defender.getStatus().setHp(defendHp + (defendDmg - attackDmg));
		Gdx.app.log("Battle", attacker.getName() + "가 " + defender.getName()
				+ "를 공격하였습니다!");
	}

	public void skillAttack(Unit unit, String skillID) {
		Gdx.app.log("Battle", unit.getName() + "가 " + skillID + "를 사용하였습니다!");
	}
}
