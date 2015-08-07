package com.mygdx.model.unit;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.mygdx.ui.StatusBarUi;

public class StatusBar {
	private Unit unit;
	private StatusBarUi hpBar;
	private StatusBarUi gaugeBar;

	public StatusBar(Unit unit, Skin skin) {
		this.unit = unit;
		hpBar = new StatusBarUi("hp", skin);
		hpBar.setValue(getHpPercent());
	}

	public void update() {
		hpBar.setValue(getHpPercent());
	}

	public int getHp() {
		return unit.getStatus().getHp();
	}

	public int getMaxHp() {
		return unit.getStatus().getMaxHp();
	}

	public int getHpPercent() {
		float factor = (float) unit.getStatus().getHp()
				/ unit.getStatus().getMaxHp();
		return (int) (factor * 100);
	}

	public Unit getUnit() {
		return unit;
	}

	public void setMonster(Monster monster) {
		this.unit = monster;
	}

	public StatusBarUi getHpBar() {
		return hpBar;
	}

	public void setHpBar(StatusBarUi hpBar) {
		this.hpBar = hpBar;
	}

	public StatusBarUi getGaugeBar() {
		return gaugeBar;
	}

	public void setGaugeBar(StatusBarUi gaugeBar) {
		this.gaugeBar = gaugeBar;
	}
}
