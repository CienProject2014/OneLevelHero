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
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.StatusBar;
import com.mygdx.model.unit.Unit;

public class CharacterUiStage extends BaseOneLevelStage {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;
	private Table statusTable;
	private Table barTable;
	private List<Hero> battleMemberList;
	private List<StatusBar> heroStatusBarList;
	private List<Label> hpLabelList;
	private Image heroImage;
	private Image[] highlightImage;
	private Table highlightTable;
	private Table uiTable;

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
		if (battleManager.isInBattle()){
			for(int i=0;i<battleMemberList.size();i++){
				if(battleManager.getCurrentActor() == partyManager.getBattleMemberList().get(i)){
					highlightImage[i%3].setVisible(true);
					highlightImage[(i+1)%3].setVisible(false);
					highlightImage[(i+2)%3].setVisible(false);
				}
			}
		}

		for (int i = 0; i < heroStatusBarList.size(); i++) {
			heroStatusBarList.get(i).update();
			hpLabelList.get(i).setText(heroStatusBarList.get(i).getHp() + "/" + heroStatusBarList.get(i).getMaxHp());
		}
	}

	private void initializeList() {
		highlightImage = new Image[3];
		if (battleManager.getBattleState().equals(BattleStateEnum.ENCOUNTER)){
			highlightTable = makeHighlightTable();
			tableStack.add(highlightTable);
			for(int i=0;i<partyManager.getBattleMemberList().size();i++)
				highlightImage[i].setVisible(false);
		}
		battleMemberList = partyManager.getBattleMemberList();
		hpLabelList = new ArrayList<Label>(battleMemberList.size());
		heroStatusBarList = new ArrayList<StatusBar>(battleMemberList.size());
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
					.padLeft(uiConstantsMap.get("heroTablePadLeft")-1750).width(uiConstantsMap.get("heroImageWidth")+350)
					.height(uiConstantsMap.get("heroImageHeight"));
			table.row();
		}
		return table;
	}

	private Table makeHeroTable(final Unit unit, int index) {
		Table heroTable = new Table();
		heroImage = new Image(textureManager.getFaceImage(unit.getFacePath()));

		heroTable.add(heroImage).padRight(uiConstantsMap.get("heroTablePadLeft"))
				.width(uiConstantsMap.get("heroImageWidth")).height(uiConstantsMap.get("heroImageHeight"));

		barTable = new Table();
		Label hpLabel = new Label(unit.getStatus().getHp() + "/" + unit.getStatus().getMaxHp(),
				uiComponentAssets.getSkin());
		hpLabelList.add(hpLabel);
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
		if (battleManager.getBattleState().equals(BattleStateEnum.IN_GAME)) {
			heroImage.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					if (battleManager.isSkill() == true) {
						if (battleManager.isShowGrid()) {
							partyManager.setCurrentSelectedHero(null);
						} else {
							partyManager.setCurrentSelectedHero(battleMemberList.get(index));
							battleManager.useSkill(battleManager.getCurrentAttackUnit(),
									partyManager.getCurrentSelectedHero(),
									battleManager.getCurrentSelectedSkill().getSkillPath());
							battleManager.setSkill(false);
						}
					}
				}
			});
		} else {
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
