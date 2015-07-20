package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.model.Hero;
import com.mygdx.ui.StatusBarUi;

public class CharacterUiStage extends BaseOneLevelStage {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("CharacterUiStage");
<<<<<<< HEAD
=======

>>>>>>> remote/#181_title+moving_UI
	private Table statusTable;
	private int battleMemberNumber;
	private List<Hero> battleMemberList;
	private List<HeroStatus> heroStatusList;
	private final String BUFF_DE_FAINT = "buff_de_07";
	private final String BUFF_DE_FIRE = "buff_de_02";
	private final String BUFF_DE_ICE = "buff_de_04";
	private final String BUFF_DE_SATAN = "buff_de_devil";

	public Stage makeStage() {
		super.makeStage();

		listInitialize();

		Table uiTable;
		uiTable = makeUiTable();
		tableStack.add(uiTable);

		return this;
	}

	private void listInitialize() {
		battleMemberList = partyManager.getBattleMemberList();
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

		statusTable = makeStatusTable();

		table.add(statusTable).expandX().left();

		return table;

	}

	private Table makeStatusTable() {
		Table table = new Table();

		for (Iterator<HeroStatus> i = heroStatusList.iterator(); i.hasNext();) {
			HeroStatus status = (HeroStatus) i.next();
			Table heroTable = makeHeroTable(status);

			table.add(heroTable).padBottom(
					uiConstantsMap.get("heroTablePadBottom"));
			table.row();
		}

		return table;
	}

	private Table makeHeroTable(HeroStatus status) {
		Table heroTable = new Table();

		heroTable.add(new Image(status.getHero().getFaceTexture()))
				.padRight(uiConstantsMap.get("heroTablePadLeft"))
				.width(uiConstantsMap.get("heroImageWidth"))
				.height(uiConstantsMap.get("heroImageHeight"));

		Table barTable = new Table();
		HorizontalGroup buffGroup = new HorizontalGroup();
		buffGroup.space(uiConstantsMap.get("heroBarHorizontalSpace"));
		buffGroup.addActor(new Image(StaticAssets.battleUiTextureMap
<<<<<<< HEAD
				.get(BUFF_DE_FAINT)));
		buffGroup.addActor(new Image(StaticAssets.battleUiTextureMap
				.get(BUFF_DE_SATAN)));
		buffGroup.addActor(new Image(StaticAssets.battleUiTextureMap
				.get(BUFF_DE_ICE)));
		buffGroup.addActor(new Image(StaticAssets.battleUiTextureMap
				.get(BUFF_DE_FIRE)));
=======
				.get("802_faint")));
		buffGroup.addActor(new Image(StaticAssets.battleUiTextureMap
				.get("802_satan")));
		buffGroup.addActor(new Image(StaticAssets.battleUiTextureMap
				.get("802_ice")));
		buffGroup.addActor(new Image(StaticAssets.battleUiTextureMap
				.get("802_fire")));
>>>>>>> remote/#181_title+moving_UI

		barTable.add(
				new Label(status.getHp() + "/" + status.getMaxHp(),
						uiComponentAssets.getSkin()))
				.padBottom(uiConstantsMap.get("heroBarSpace")).row();
		barTable.add(status.getHpBar())
				.padBottom(uiConstantsMap.get("heroBarSpace"))
				.width(uiConstantsMap.get("barTableWidth")).row();
		barTable.add(status.getGaugeBar())
				.padBottom(uiConstantsMap.get("heroBarSpace"))
				.width(uiConstantsMap.get("barTableWidth")).row();
		barTable.add(buffGroup).width(uiConstantsMap.get("buffTableWidth"))
				.height(uiConstantsMap.get("buffTableHeight"));

		heroTable.add(barTable);

		return heroTable;
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
}
