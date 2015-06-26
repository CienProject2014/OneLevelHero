package com.mygdx.model;

public class Status {
	private int atk;
	private int ftg;
	private int exp;
	private int matk;
	private int def;
	private int mdef;
	private int hp;
	private int spd;
	private float fr;
	private float wr;
	private float er;
	private String job;

	public int getAtk() {
		return atk;
	}

	public void setAtk(int atk) {
		this.atk = atk;
	}

	public int getFtg() {
		return ftg;
	}

	public void setFtg(int ftg) {
		this.ftg = ftg;
	}

	public int getExp() {
		return exp;
	}

	public void setExp(int exp) {
		this.exp = exp;
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

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public float getFr() {
		return fr;
	}

	public void setFr(float fr) {
		this.fr = fr;
	}

	public float getWr() {
		return wr;
	}

	public void setWr(float wr) {
		this.wr = wr;
	}

	public float getEr() {
		return er;
	}

	public void setEr(float er) {
		this.er = er;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String[] getStatusList() {
		String[] array = { String.valueOf(atk), String.valueOf(ftg), String.valueOf(exp), String.valueOf(atk), String.valueOf(matk), String.valueOf(def), String.valueOf(mdef), String.valueOf(hp), String.valueOf(spd), String.valueOf(fr), String.valueOf(wr), String.valueOf(er), job };

		return array;
	}
}
