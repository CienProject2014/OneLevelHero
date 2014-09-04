package com.mygdx.unit;

public class Hero extends Unit {
	public Hero(String unitName, Status status, Bag bag) {
		super(unitName, status, bag);
	}

	public Hero(String unitName) {
		this(unitName, new Status(), new Bag());

	}

}
