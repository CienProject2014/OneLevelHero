package com.mygdx.stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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
		Iterator<StatusBar> heroStatusBarIterator = heroStatusBarList.iterator();
		while (heroStatusBarIterator.hasNext()) {
			Table heroTable = makeHeroTable(heroStatusBarIterator.next());
			table.add(heroTable).padBottom(uiConstantsMap.get("heroTablePadBottom"));
			table.row();
		}
		return table;
	}

	private Table makeHeroTable(final StatusBar statusBar) {
		Table heroTable = new Table();
		Image heroImage = new Image(textureManager.getFaceImage(statusBar.getUnit().getFacePath()));
		heroImage.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				partyManager.setCurrentSelectedHero((Hero) statusBar.getUnit());
				screenFactory.show(ScreenEnum.STATUS);
			}
		});
		heroTable.add(heroImage).padRight(uiConstantsMap.get("heroTablePadLeft"))
				.width(uiConstantsMap.get("heroImageWidth")).height(uiConstantsMap.get("heroImageHeight"));

		barTable = new Table();
		Label hpLabel = new Label(statusBar.getHp() + "/" + statusBar.getMaxHp(), uiComponentAssets.getSkin());
		hpLabelList.add(hpLabel);
		barTable.add(hpLabel).padBottom(uiConstantsMap.get("heroBarSpace")).row();
		barTable.add(statusBar.getHpBar()).padBottom(uiConstantsMap.get("heroBarSpace"))
				.width(uiConstantsMap.get("barTableWidth")).row();
		barTable.add(statusBar.getGaugeBar()).padBottom(uiConstantsMap.get("heroBarSpace"))
				.width(uiConstantsMap.get("barTableWidth")).row();
		heroTable.add(barTable);
		return heroTable;
	}
}
