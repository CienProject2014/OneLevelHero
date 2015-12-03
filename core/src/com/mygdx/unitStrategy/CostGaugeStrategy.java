package com.mygdx.unitStrategy;

import com.mygdx.model.unit.Unit;

public class CostGaugeStrategy {
	private int costGauge;

	public void applyCostGaugeToUnit(Unit unit, int typeOfAction) {
		unit.setPreGauge(unit.getGauge());
		costGauge = (int) (((double) (150 - unit.getActingPower()) / 50) * typeOfAction);
		unit.setGauge(unit.getGauge() - costGauge);
	}

	public int getCostGauge() {
		return costGauge;
	}

	public void setCostGauge(int costGauge) {
		this.costGauge = costGauge;
	}
}
