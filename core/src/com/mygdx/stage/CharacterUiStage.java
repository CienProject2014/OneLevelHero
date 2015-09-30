package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.battle.Buff;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.StatusBar;
import com.mygdx.model.unit.Unit;

public class CharacterUiStage extends BaseOneLevelStage {
	@Autowired
	private transient UiComponentAssets uiComponentAssets;
	@Autowired
	private transient ScreenFactory screenFactory;
	@Autowired
	private transient TextureManager textureManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private transient ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;
	private Table statusTable;
	private Table barTable;
	private Table buffTable;
	private List<Hero> battleMemberList;
	private List<StatusBar> heroStatusBarList;
	private List<Label> hpLabelList;
	private Image heroImage;
	private Image[] highlightImage;
	private Table highlightTable;
	private Table uiTable;
	private List<Table> buffTableList;

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("CharacterUiStage");
		initializeList();
		/*
		 * Table uiTable; uiTable = makeUiTable(); Table highlightTable;
		 * highlightTable = highlightTable(); tableStack.add(highlightTable);
		 * 
		 * tableStack.add(uiTable);
		 */
		return this;
	}

	// 정보 업데이트
	@Override
	public void act(float delta) {
		super.act(delta);
		if (battleManager.isInBattle()) {
			for (int i = 0; i < battleMemberList.size(); i++) {
				if (battleManager.getCurrentActor() == partyManager.getBattleMemberList().get(i)) {
					highlightImage[i % 3].setVisible(true);
					highlightImage[(i + 1) % 3].setVisible(false);
					highlightImage[(i + 2) % 3].setVisible(false);
				}
			}
		}

		for (int i = 0; i < heroStatusBarList.size(); i++) {
			buffTableList.get(i).clear();
			heroStatusBarList.get(i).update();
			hpLabelList.get(i).setText(heroStatusBarList.get(i).getHp() + "/" + heroStatusBarList.get(i).getMaxHp());
		}

		int i = 0;
		for (Unit hero : partyManager.getBattleMemberList()) {
			for (Buff buff : hero.getBuffList()) {
				if (buff.getBuffPath().equals("buff_en_casting_")) {
					if (hero.getStatus().getCasting() == 0) {

					} else {
						Image buffIcon = new Image(textureManager.getTexture(buff.getBuffPath()
								+ String.valueOf(hero.getStatus().getCasting())));
						buffTableList.get(i).add(buffIcon).width(35).height(35);
					}
				} else if (buff.getBuffPath().equals("buff_de_overload")) {
					if (hero.getOverload() == 0) {

					} else {
						Image buffIcon = new Image(textureManager.getTexture(buff.getBuffPath() + "_"
								+ String.valueOf(hero.getOverload())));
						buffTableList.get(i).add(buffIcon).width(35).height(35);
					}
				} else {
					Image buffIcon = new Image(textureManager.getTexture(buff.getBuffPath()));
					buffTableList.get(i).add(buffIcon).width(35).height(35);
				}
			}
			i++;
		}
	}

	private void initializeList() {
		highlightImage = new Image[3];
		highlightTable = makeHighlightTable();
		tableStack.add(highlightTable);
		for (int i = 0; i < partyManager.getBattleMemberList().size(); i++)
			highlightImage[i].setVisible(false);
		battleMemberList = partyManager.getBattleMemberList();
		hpLabelList = new ArrayList<Label>(battleMemberList.size());
		heroStatusBarList = new ArrayList<StatusBar>(battleMemberList.size());
		buffTableList = new ArrayList<Table>(battleMemberList.size());
		for (int i = 0; i < battleMemberList.size(); i++) {
			if (battleManager.getBattleState().equals(BattleStateEnum.ENCOUNTER)) {
				partyManager.setCurrentSelectedHero(null);
				heroStatusBarList.add(new StatusBar(battleMemberList.get(i), uiComponentAssets.getSkin(), true));
			} else {
				heroStatusBarList.add(new StatusBar(battleMemberList.get(i), uiComponentAssets.getSkin(), false));
			}
		}
		statusTable = new Table();
		uiTable = makeUiTable();
		tableStack.add(uiTable);
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

		for (int i = 0; i < battleMemberList.size(); i++) {

			Table heroTable = makeHeroTable(battleMemberList.get(i), i);
			table.add(heroTable).padBottom(uiConstantsMap.get("heroTablePadBottom"));
			table.row();

		}
		return table;
	}

	private Table makeHighlightTable() {
		Table table = new Table();

		for (int i = 0; i < partyManager.getBattleMemberList().size(); i++) {

			highlightImage[i] = new Image(textureManager.getTexture("battleui_character_turn"));
			table.add(highlightImage[i]).padBottom(uiConstantsMap.get("heroTablePadBottom"))
					.padLeft(uiConstantsMap.get("heroTablePadLeft") - 1750)
					.width(uiConstantsMap.get("heroImageWidth") + 350).height(uiConstantsMap.get("heroImageHeight"));
			table.row();
		}
		return table;
	}

	private Table makeHeroTable(final Unit unit, int index) {
		Table heroTable = new Table();
		heroImage = new Image(textureManager.getFaceImage(unit.getFacePath()));

		heroTable.add(heroImage).padLeft(uiConstantsMap.get("heroTablePadLeft"))
				.width(uiConstantsMap.get("heroImageWidth")).height(uiConstantsMap.get("heroImageHeight"));

		barTable = new Table();

		buffTable = new Table();

		buffTableList.add(buffTable);
		for (Buff buff : unit.getBuffList()) {
			Image buffIcon = new Image(textureManager.getTexture(buff.getBuffPath()));
			buffTableList.get(index).add(buffIcon).width(10).height(10);
		}

		Label hpLabel = new Label(unit.getStatus().getHp() + "/" + unit.getStatus().getMaxHp(),
				uiComponentAssets.getSkin());
		hpLabelList.add(hpLabel);
		barTable.add(buffTableList.get(index)).row();
		barTable.add(hpLabel).padBottom(uiConstantsMap.get("heroBarSpace")).row();
		barTable.add(heroStatusBarList.get(index).getHpBar()).padBottom(uiConstantsMap.get("heroBarSpace"))
				.width(uiConstantsMap.get("barTableWidth")).row();
		barTable.add(heroStatusBarList.get(index).getGaugeBar()).padBottom(uiConstantsMap.get("heroBarSpace"))
				.width(uiConstantsMap.get("barTableWidth")).row();
		heroTable.add(barTable);
		makeAddListener(index);
		return heroTable;
	}

	private void makeAddListener(final int index) {
		heroImage.clearListeners();
		if (battleManager.getBattleState().equals(BattleStateEnum.ENCOUNTER)) {
			// 전투 중엔 다른 식으로 작동한다.
			heroImage.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (battleManager.isSkill() == true) {
						// 스킬 사용시
						if (battleManager.isShowGrid()) {
							partyManager.setCurrentSelectedHero(null);
						} else {
							partyManager.setCurrentSelectedHero(battleMemberList.get(index));
							battleManager.useSkill(battleManager.getCurrentAttackUnit(), partyManager
									.getCurrentSelectedHero(), battleManager.getCurrentSelectedSkill().getSkillPath());
							battleManager.setSkill(false);
						}
					}
				}
			});
		} else {
			// 일상에서는 스테이터스 창이 보인다.
			heroImage.addListener(new ClickListener() {

				@Override
				public void clicked(InputEvent event, float x, float y) {
					partyManager.setCurrentSelectedHero(battleMemberList.get(index));
					screenFactory.show(ScreenEnum.STATUS);
				}
			});
		}
	}
}
