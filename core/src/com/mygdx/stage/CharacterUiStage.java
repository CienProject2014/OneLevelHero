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

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("CharacterUiStage");
		initializeList();
		Table uiTable;
		uiTable = makeUiTable();
		tableStack.add(uiTable);
		return this;
	}

	// 정보 업데이트
	@Override
	public void act(float delta) {
		super.act(delta);
		for (int i = 0; i < heroStatusBarList.size(); i++) {
			heroStatusBarList.get(i).update();
			hpLabelList.get(i).setText(heroStatusBarList.get(i).getHp() + "/" + heroStatusBarList.get(i).getMaxHp());
		}
	}

	private void initializeList() {
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
							battleManager.useSkill(battleManager.getCurrentAttackUnit(),
									partyManager.getCurrentSelectedHero(),
									battleManager.getCurrentSelectedSkill().getSkillPath());
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
