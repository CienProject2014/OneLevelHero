package com.mygdx.model;

public class Status {
	private int atk;	// attack
	private int matk;	// magic attack
	private int def;	// defense
	private int mdef;	// magic defense
	private int hp;		// health point
	private int stm;	// stamina
	private int spd;	// speed
	private String job;	// job
	// minimum 0 ~ maximum 80
	private int fr;	// fire resistance
	private int wr; // water 
	private int lr; // lightning
	
	private int[] stack;

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getMatk() {
		return matk;
	}

	public void setMatk(int matk) {
		this.matk = matk;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getMdef() {
		return mdef;
	}

	public void setMdef(int mdef) {
		this.mdef = mdef;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getStm() {
		return stm;
	}

	public void setStm(int stm) {
		this.stm = stm;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public int getFr() {
		return fr;
	}

	public void setFr(int fr) {
		this.fr = fr;
	}

	public int getWr() {
		return wr;
	}

	public void setWr(int wr) {
		this.wr = wr;
	}

	public int getLr() {
		return lr;
	}

	public void setLr(int lr) {
		this.lr = lr;
	}
	
}
