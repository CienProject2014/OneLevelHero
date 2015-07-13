package com.mygdx.stage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.model.Hero;
import com.mygdx.ui.StatusBarUi;

public class CharacterUiStage extends BaseOneLevelStage {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	private float realWidth, realHeight;

	private Table uiTable; // 전체 화면을 차지하는 테이블
	private Table statusTable;

	private int battleMemberNumber;
	private List<Hero> battleMemberList;

	private List<HeroStatus> heroStatusList;

	public Stage makeStage() {
		uiTable = new Table();

		initialize();

		uiTable = makeUiTable();

		addActor(uiTable);

		return this;
	}

	private void initialize() {
		battleMemberList = partyInfo.getBattleMemberList();
		battleMemberNumber = battleMemberList.size();

		heroStatusList = new ArrayList<HeroStatus>(battleMemberNumber);
		for (int i = 0; i < battleMemberNumber; i++) {
			heroStatusList.add(new HeroStatus(battleMemberList.get(i)));
		}

		statusTable = new Table();
	}

	// CurrentState 에서 멤버를 가져와 Table 을 만든다.
	private Table makeUiTable() {
		Table table = new Table();

		table.setFillParent(true);

		statusTable = makeStatusTable();

		table.add(statusTable).expandX().left();

		return table;

	}

	private Table makeStatusTable() {
		Table table = new Table();

		for (Iterator<HeroStatus> i = heroStatusList.iterator(); i.hasNext();) {
			HeroStatus status = (HeroStatus) i.next();
			Table heroTable = makeHeroTable(status);

			table.add(heroTable).padBottom(58f * StaticAssets.resolutionFactor);
			table.row();
		}

		return table;
	}

	private Table makeHeroTable(HeroStatus status) {
		Table table = new Table();

		table.add(new Image(status.getHero().getStatusTexture())).padRight(
				13f * StaticAssets.resolutionFactor);

		VerticalGroup vg = new VerticalGroup();
		vg.space(4f * StaticAssets.resolutionFactor);

		vg.addActor(new Label(status.getHp() + "/" + status.getMaxHp(),
				uiComponentAssets.getSkin()));
		vg.addActor(status.getHpBar());
		vg.addActor(status.getGaugeBar());

		HorizontalGroup hg = new HorizontalGroup();
		hg.space(6f * StaticAssets.resolutionFactor);

		hg.addActor(new Image(StaticAssets.battleUiTextureMap.get("802_faint")));
		hg.addActor(new Image(StaticAssets.battleUiTextureMap.get("802_satan")));
		hg.addActor(new Image(StaticAssets.battleUiTextureMap.get("802_ice")));
		hg.addActor(new Image(StaticAssets.battleUiTextureMap.get("802_fire")));
		// FIXME 현재는 버프 테이블 사용하지 않음.
		// vg.addActor(hg);

		table.add(vg);

		return table;
	}

	// 정보 업데이트
	@Override
	public void act(float delta) {
		super.act(delta);

		for (int i = 0; i < heroStatusList.size(); i++) {
			heroStatusList.get(i).update();
		}
	}

	class HeroStatus {
		private Hero hero;
		private StatusBarUi hpBar;
		private StatusBarUi gaugeBar;

		public HeroStatus(Hero hero) {
			this.hero = hero;
			hpBar = new StatusBarUi("hp", 0, 100, 1, false,
					uiComponentAssets.getSkin());
			hpBar.setValue(getHpPercent());
			gaugeBar = new StatusBarUi("gauge", 0, 100, 1, false,
					uiComponentAssets.getSkin());
			gaugeBar.setValue(getGaugePercent());
		}

		public void update() {
			hpBar.setValue(getHpPercent());
			gaugeBar.setValue(getGaugePercent());
		}

		public int getHp() {
			return hero.getStatus().getHp();
		}

		public int getMaxHp() {
			return hero.getStatus().getMaxHp();
		}

		public int getHpPercent() {
			float factor = (float) hero.getStatus().getHp()
					/ hero.getStatus().getMaxHp();
			return (int) (factor * 100);
		}

		public int getGaugePercent() {
			// float factor = (float) hero.getGauge() / 100;
			// FIXME 게이지 구현이 안되었음
			return 100;
		}

		public Hero getHero() {
			return hero;
		}

		public void setHero(Hero hero) {
			this.hero = hero;
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

	public UiComponentAssets getUiComponentAssets() {
		return uiComponentAssets;
	}

	public void setUiComponentAssets(UiComponentAssets uiComponentAssets) {
		this.uiComponentAssets = uiComponentAssets;
	}
}
