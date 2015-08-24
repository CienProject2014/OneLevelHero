package com.mygdx.stage;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.BattleStateEnum;
import com.mygdx.enums.PositionEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.FieldManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.unit.Monster;

public class EncounterStage extends BaseOneLevelStage {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private FieldManager fieldManager;
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;
	private Monster monster;
	private TextButton fightButton;
	private TextButton fleeButton;
	private Table outerTable; // 몬스터 테이블의 바깥 테이블
	private Table monsterTable; // 몬스터가 들어가는 테이블
	private Table uiTable;

	private Table selTable;

	public Stage makeStage() {
		super.makeStage();
		uiConstantsMap = constantsAssets.getUiConstants("MonsterStage");
		monster = battleManager.getSelectedMonster();
		setMonsterTable();

		fightButton = new TextButton("싸운다", uiComponentAssets.getSkin());
		fleeButton = new TextButton("도망친다", uiComponentAssets.getSkin());

		selTable = new Table(uiComponentAssets.getSkin());
		selTable.add(fightButton);
		selTable.add(fleeButton);

		selTable.bottom();
		tableStack.add(selTable);

		addListener(); // 리스너 할당

		return this;
	}

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

		tableStack.add(outerTable);
		tableStack.add(uiTable);
	}

	private Image getMonsterImage() {
		return new Image(textureManager.getMonsterTexture(monster.getFacePath()));
	}

	// FIXME
	private TextureRegionDrawable getBackgroundTRD() {
		if (battleManager.getSelectedMonster().getFacePath().equals("mawang_01")) {
			return new TextureRegionDrawable(new TextureRegion(textureManager.getEtcTexture("bg_devilcastle_01")));
		} else if (battleManager.getBeforePosition() == PositionEnum.DUNGEON) {
			return new TextureRegionDrawable(new TextureRegion(textureManager.getEtcTexture("bg_devilcastle_01")));
		} else {
			Gdx.app.log("EncounterStage", "fieldType - " + fieldManager.getFieldType());
			return new TextureRegionDrawable(new TextureRegion(textureManager.getBackgroundTexture(fieldManager
					.getFieldType().toString())));
		}
	}
	private void addListener() {
		fightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				// BattleScreen으로 넘어가는 것이 전투를 시작하는 것을 의미
				screenFactory.show(ScreenEnum.BATTLE);
			}
		});
		fleeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {

				if (battleManager.getBeforePosition() == PositionEnum.FIELD) {
					battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
					screenFactory.show(ScreenEnum.FIELD);
				} else if (battleManager.getBeforePosition() == PositionEnum.DUNGEON) {
					screenFactory.show(ScreenEnum.DUNGEON);
				} else {
					screenFactory.show(ScreenEnum.FIELD);

					battleManager.setBattleState(BattleStateEnum.NOT_IN_BATTLE);
					screenFactory.show(ScreenEnum.FIELD);
				}
			}
		});
	}
}
