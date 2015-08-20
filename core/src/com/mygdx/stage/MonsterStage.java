package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.FieldManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.unit.Monster;
import com.mygdx.model.unit.StatusBar;

public class MonsterStage extends BaseOneLevelStage {
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private BattleManager battleManager;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("MonsterStage");
	private Monster monster;
	@Autowired
	private UiComponentAssets uiComponentAssets;

	// private Stack tableStack; // 전체 화면에 들어가는 테이블
	private Table outerTable; // 몬스터 테이블의 바깥 테이블
	private Table monsterTable; // 몬스터가 들어가는 테이블
	private Table uiTable;
	private Table monsterHpTable;
	private Label monsterHpLabel;
	private StatusBar monsterStatusBar;

	@Override
	public Stage makeStage() {
		super.makeStage();
		monster = battleManager.getSelectedMonster();
		monsterStatusBar = new StatusBar(monster, uiComponentAssets.getSkin());
		setMonsterTable();
		return this;
	}

	// 불러온 몬스터의 이미지를 테이블에 넣는다.
	private void setMonsterTable() {
		outerTable = new Table();
		monsterTable = new Table();
		uiTable = new Table();
		// innerTable.setBackground(getBackgroundTRD()); // 몬스터 테이블의 배경
		monsterTable.add(getMonsterImage());
		outerTable.setBackground(getBackgroundTRD(), false);
		outerTable.top(); // table을 위로 정렬
		outerTable.add(monsterTable).padTop(uiConstantsMap.get("monsterPadTop"))
				.width(uiConstantsMap.get("monsterTableWidth")).height(uiConstantsMap.get("monsterTableHeight")).row();

		Table hpTable = monsterHpTable(monsterStatusBar);
		uiTable.bottom();
		uiTable.add(hpTable).padBottom(uiConstantsMap.get("hpTablePadBottom"));
		tableStack.add(outerTable);
		tableStack.add(uiTable);
		addAction();
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		monsterHpLabel.setText(monsterStatusBar.getHp() + "/" + monsterStatusBar.getMaxHp());
		monsterStatusBar.update();
	}

	private void addAction() {
		monsterTable.setVisible(false);
		monsterTable.addAction(Actions.fadeOut(0));
		monsterTable.setVisible(true);
		monsterTable.addAction(Actions.fadeIn(1.3f));

		monsterHpLabel.setVisible(false);
		monsterHpLabel.addAction(Actions.fadeOut(0));
		monsterHpLabel.addAction(Actions.moveTo(250, 0));
		monsterHpLabel.setVisible(true);
		monsterHpLabel.addAction(Actions.fadeIn(1));
		monsterHpLabel.addAction(Actions.moveTo(250, 10, 1));
	}

	private Table monsterHpTable(StatusBar monsterStatusBar) {
		monsterHpTable = new Table();
		monsterHpTable.add(monsterStatusBar.getHpBar()).width(uiConstantsMap.get("hpTableWidth")).row();

		monsterHpLabel = new Label(monsterStatusBar.getHp() + "/" + monsterStatusBar.getMaxHp(),
				uiComponentAssets.getSkin());
		monsterHpTable.add(monsterHpLabel);

		return monsterHpTable;
	}

	private Image getMonsterImage() {
		Texture monsterTexture = monster.getBodyTexture();
		Image monsterImage = new Image(monsterTexture);
		return monsterImage;

	}

	private TextureRegionDrawable getBackgroundTRD() {
		// FIXME 현재 그냥 로딩하는걸로 되어 있음.
		if (battleManager.getSelectedMonster().getFacePath().equals("mawang_01")) {
			return new TextureRegionDrawable(new TextureRegion(
					StaticAssets.assetManager.get(StaticAssets.textureMap.get("bg_devilcastle_01"), Texture.class)));
		} else {
			return new TextureRegionDrawable(
					new TextureRegion(TextureManager.getBackgroundTexture(fieldManager.getFieldType().toString())));
		}
	}

	public HashMap<String, Float> getUiConstantsMap() {
		return uiConstantsMap;
	}

	public void setUiConstantsMap(HashMap<String, Float> uiConstantsMap) {
		this.uiConstantsMap = uiConstantsMap;
	}

}
