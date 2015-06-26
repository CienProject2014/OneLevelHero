package com.mygdx.stage;

import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.model.Hero;
import com.mygdx.ui.StatusBarUi;

public class CharacterUiStage extends OneLevelStage {
	private float realWidth, realHeight;

	private Table uiTable; // 전체 화면을 차지하는 테이블
	private Table bottomTable; // 케릭터 관련 부분을 담고 있는 테이블
	private Table[] statusbarTable;
	private Table[] charaterTable;

	private int battleMemberNumber;
	private List<Hero> battleMemberList;
	private Image[] characterImage;

	private StatusBarUi[] hpbar;
	private StatusBarUi[] expbar;
	private StatusBarUi[] turnbar;
	private String[] hpbarName;

	public Stage makeStage() {
		uiTable = new Table();

		initialize();

		makeTable();

		uiTable.setFillParent(true);
		uiTable.align(Align.bottom);
		uiTable.add(bottomTable);

		addActor(uiTable);

		return this;
	}

	private void initialize() {
		realHeight = assets.windowHeight;
		realWidth = assets.windowWidth;

		bottomTable = new Table(assets.skin);
		statusbarTable = new Table[3];
		charaterTable = new Table[3];

		hpbar = new StatusBarUi[3];
		hpbarName = new String[3];
		expbar = new StatusBarUi[3];
		turnbar = new StatusBarUi[3];
		characterImage = new Image[3];
	}

	// CurrentState 에서 멤버를 가져와 Table 을 만든다.
	private void makeTable() {
		battleMemberList = partyInfo.getBattleMemberList();
		battleMemberNumber = battleMemberList.size();

		for (int i = 0; i < battleMemberNumber; i++) {
			hpbar[i] = new StatusBarUi("hp", 0f, 100f, 1f, false, assets.skin);
			expbar[i] = new StatusBarUi("exp", 0f, 100f, 1f, false, assets.skin);
			turnbar[i] = new StatusBarUi("turn", 0f, 100f, 1f, false,
					assets.skin);
			hpbar[i].setName("hpbar[" + i + "]");
			hpbarName[i] = hpbar[i].getName();
		}

		// 캐릭터 이미지 세팅
		for (int i = 0; i < battleMemberNumber; i++)
			characterImage[i] = new Image(battleMemberList.get(i)
					.getStatusTexture());

		for (int i = 0; i < battleMemberNumber; i++) {
			statusbarTable[i] = new Table(assets.skin);
			charaterTable[i] = new Table(assets.skin);
		}

		for (int i = 0; i < battleMemberNumber; i++) {
			statusbarTable[i].add(hpbar[i]).width(realWidth / 12)
					.height(realHeight / 12).bottom();
			statusbarTable[i].row();
			statusbarTable[i].add(expbar[i]).width(realWidth / 12)
					.height(realHeight / 12).bottom();
			statusbarTable[i].row();
			statusbarTable[i].add(turnbar[i]).width(realWidth / 12)
					.height(realHeight / 12).bottom();

			bottomTable.add(charaterTable[i]);
			bottomTable.add(statusbarTable[i]);
		}

		for (int i = 0; i < battleMemberNumber; i++)
			charaterTable[i].add(characterImage[i]).width(realWidth / 4)
					.height(realHeight / 4);
	}

	// 정보 업데이트
	@Override
	public void act(float delta) {
		super.act(delta);

		// Screen - act 에서 실행시킨다.
		for (int i = 0; i < battleMemberNumber; i++) {
			/*
			 * int hpValue = battleMemberList.get(i).getStatus().getHp();
			 * Gdx.app.log("이름?", hpbar[i].getName());
			 *
			 * if (!hpbar[i].setValue(hpValue))
			 * 	Gdx.app.log("GameUiStage", "체력 설정 실패");
			 */
			hpbar[i].act(delta);
		}
	}
}
