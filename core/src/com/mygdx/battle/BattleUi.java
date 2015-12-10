package com.mygdx.battle;

import java.util.ArrayList;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.popup.SkillRunPopup;
import com.mygdx.stage.BattleStage;
import com.mygdx.ui.GridHitbox;

public class BattleUi {
	private GridHitbox gridHitbox; // grid hitbox 테이블
	private ArrayList<ImageButton> battleCommandButtonList;
	private Table battleCommandButtonTable;
	private BattleStage battleStage;
	private SkillRunPopup skillRunPopup;
	private Table gridTable;

	public BattleUi() {
		gridHitbox = new GridHitbox();
	}

	public GridHitbox getGridHitbox() {
		return gridHitbox;
	}

	public void setGridHitbox(GridHitbox gridHitbox) {
		this.gridHitbox = gridHitbox;
	}

	public ArrayList<ImageButton> getBattleCommandButtonList() {
		return battleCommandButtonList;
	}

	public void setBattleCommandButtonList(ArrayList<ImageButton> battleCommandButtonList) {
		this.battleCommandButtonList = battleCommandButtonList;
	}

	public Table getBattleCommandButtonTable() {
		return battleCommandButtonTable;
	}

	public void setBattleCommandButtonTable(Table battleCommandButtonTable) {
		this.battleCommandButtonTable = battleCommandButtonTable;
	}

	public BattleStage getBattleStage() {
		return battleStage;
	}

	public void setBattleStage(BattleStage battleStage) {
		this.battleStage = battleStage;
	}

	public SkillRunPopup getSkillRunPopup() {
		return skillRunPopup;
	}

	public void setSkillRunPopup(SkillRunPopup skillRunPopup) {
		this.skillRunPopup = skillRunPopup;
	}

	public Table getGridTable() {
		return gridTable;
	}

	public void setGridTable(Table gridTable) {
		this.gridTable = gridTable;

	}
}
