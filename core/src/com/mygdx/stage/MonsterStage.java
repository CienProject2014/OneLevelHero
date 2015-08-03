package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.BattleManager;
import com.mygdx.model.Monster;
import com.mygdx.ui.StatusBarUi;

public class MonsterStage extends BaseOneLevelStage {
	@Autowired
	private BattleManager battleManager;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("MonsterStage");
	private Monster monster;
	private MonsterStatus monsterHPStatus;
	@Autowired
	private UiComponentAssets uiComponentAssets;

	// private Stack tableStack; // 전체 화면에 들어가는 테이블
	private Table outerTable; // 몬스터 테이블의 바깥 테이블
	private Table monsterTable; // 몬스터가 들어가는 테이블
	private Table uiTable;

	@Override
	public Stage makeStage() {
		super.makeStage();

		monster = battleManager.getSelectedMonster();

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
		outerTable.add(monsterTable)
				.padTop(uiConstantsMap.get("monsterPadTop"))
				.width(uiConstantsMap.get("monsterTableWidth"))
				.height(uiConstantsMap.get("monsterTableHeight")).row();
		MonsterStatus status = new MonsterStatus(monster);
		Table hpTable = monsterHpTable(status);
		uiTable.bottom();
		uiTable.add(hpTable).padBottom(uiConstantsMap.get("hpTablePadBottom"));
		tableStack.add(outerTable);
		tableStack.add(uiTable);
	}

	private Table monsterHpTable(MonsterStatus status) {
		Table hpTable = new Table();

		hpTable.add(status.getHpBar())
				.width(uiConstantsMap.get("hpTableWidth")).row();
		hpTable.add(new Label(status.getHp() + "/" + status.getMaxHp(),
				uiComponentAssets.getSkin()));

		return hpTable;
	}

	private Image getMonsterImage() {
		Texture monsterTexture = monster.getBodyTexture();
		return new Image(monsterTexture);
	}

	private TextureRegionDrawable getBackgroundTRD() {
		// FIXME 현재 그냥 로딩하는걸로 되어 있음.
		if (battleManager.getSelectedMonster().getName().equals("mawang")) {
			return new TextureRegionDrawable(new TextureRegion(
					StaticAssets.backgroundTextureMap.get("bg_devilcastle_01")));
		}
		return new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("texture/background/forest.png"))));
	}

	public HashMap<String, Float> getUiConstantsMap() {
		return uiConstantsMap;
	}

	public void setUiConstantsMap(HashMap<String, Float> uiConstantsMap) {
		this.uiConstantsMap = uiConstantsMap;
	}

	class MonsterStatus {
		private Monster monster;
		private StatusBarUi hpBar;

		public MonsterStatus(Monster monster) {
			this.monster = monster;
			hpBar = new StatusBarUi("hp", 0, 100, 1, false,
					uiComponentAssets.getSkin());
			hpBar.setValue(getHpPercent());

		}

		public void update() {
			hpBar.setValue(getHpPercent());

		}

		public int getHp() {
			return monster.getStatus().getHp();
		}

		public int getMaxHp() {
			return monster.getStatus().getMaxHp();
		}

		public int getHpPercent() {
			float factor = (float) monster.getStatus().getHp()
					/ monster.getStatus().getMaxHp();
			return (int) (factor * 100);
		}

		public Monster getmonster() {
			return monster;
		}

		public void setMonster(Monster monster) {
			this.monster = monster;
		}

		public StatusBarUi getHpBar() {
			return hpBar;
		}

		public void setHpBar(StatusBarUi hpBar) {
			this.hpBar = hpBar;
		}

	}

}
