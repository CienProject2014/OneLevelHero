package com.mygdx.model;

public class Status {
	private int atk;
	private int matk;
	private int def;
	private int mdef;
	private int hp; // 현재 체력
	private int maxHp; // 최대 체력
	private int spd; // speed
	private int ftg; // fatigue(피로도)
	private int exp; // 경험치
	private int maxExp; // 최대 경험치(레벨업)
	private float fr; // fire resistance
	private float wr; // water
	private float er; // earths
	private String job;

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

	public int getMaxHp() {
		return maxHp;
	}

	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
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

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
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
		String[] array = { String.valueOf(atk), String.valueOf(ftg),
				String.valueOf(exp), String.valueOf(atk), String.valueOf(matk),
				String.valueOf(def), String.valueOf(mdef), String.valueOf(hp),
				String.valueOf(spd), String.valueOf(fr), String.valueOf(wr),
				String.valueOf(er), job };

		return array;
	}
}
