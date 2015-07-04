package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.model.Monster;

public class MonsterStage extends BaseOneLevelStage {
	private int windowWidth;
	private int windowHeight;

	private Monster monster;

	private Stack tableStack; // 전체 화면에 들어가는 테이블
	private Table outerTable; // 몬스터 테이블의 바깥 테이블
	private Table monsterTable; // 몬스터가 들어가는 테이블

	private float topPadValue; // 몬스터 테이블의 세로축 위치
	private float monsterTableWidth; // 몬스터 테이블의 최대 가로 크기
	private float monsterTableHeight; // 몬스터 테이블의 최대 세로 크기
	private float monsterWidth;
	private float monsterHeight;
	private float gridTableWidth; // grid 테이블의 가로 크기
	private float gridTableHeight; // grid 테이블의 세로 크기

	public Stage makeStage() {
		windowWidth = Gdx.graphics.getWidth();
		windowHeight = Gdx.graphics.getHeight();

		monster = movingInfo.getSelectedMonster();

		topPadValue = windowHeight * 0.125f;

		monsterTableWidth = windowWidth * 0.75f;
		monsterTableHeight = windowHeight * 0.625f;

		int textureWidth = monster.getBattleTexture().getWidth();
		int textureHeight = monster.getBattleTexture().getHeight();
		if (textureWidth > monsterTableWidth
				&& textureHeight > monsterTableHeight) {
			float widthRatio = textureWidth / monsterTableWidth;
			float heightRatio = textureHeight / monsterTableHeight;
			if (widthRatio > heightRatio) {
				monsterWidth = textureWidth / widthRatio;
				monsterHeight = textureHeight / widthRatio;
			} else {
				monsterWidth = textureWidth / heightRatio;
				monsterHeight = textureHeight / heightRatio;
			}

		} else if (textureWidth > monsterTableWidth) {
			float widthRatio = textureWidth / monsterTableWidth;
			monsterWidth = textureWidth / widthRatio;
			monsterHeight = textureHeight / widthRatio;

		} else if (textureHeight > monsterTableHeight) {
			float heightRatio = textureHeight / monsterTableHeight;
			monsterWidth = textureWidth / heightRatio;
			monsterHeight = textureHeight / heightRatio;

		}

		// FIXME 상수 대신 monster.getTypeFactor() 사용해야 함
		gridTableWidth = windowWidth * 0.333333f;
		gridTableHeight = gridTableWidth;

		setMonsterTable();

		return this;
	}

	// 불러온 몬스터의 이미지를 테이블에 넣는다.
	private void setMonsterTable() {
		outerTable = new Table();
		monsterTable = new Table();
		tableStack = new Stack();

		// innerTable.setBackground(getBackgroundTRD()); // 몬스터 테이블의 배경
		monsterTable.add(getMonsterImage()); // table 안의 innerTable에 몬스터 이미지
												// 넣는다.

		outerTable.setFillParent(true); // table의 크기를 화면의 크기와 같게
		outerTable.setBackground(getBackgroundTRD()); // 전체화면의 배경
		outerTable.align(Align.top); // table을 위로 정렬
		outerTable.add(monsterTable) // table안에 innerTable 넣는다.
				.padTop(topPadValue) // 상단바에 겹치지 않게 위쪽 Padding(1/8)
				.width(monsterWidth) // 조정된 몬스터의 가로 크기
				.height(monsterHeight); // 조정된 몬스터의 세로 크기

		tableStack.setFillParent(true);
		tableStack.add(outerTable);

		this.addActor(tableStack);
	}

	private Image getMonsterImage() {
		Texture monsterTexture = monster.getBattleTexture();
		return new Image(monsterTexture);
	}

	private TextureRegionDrawable getBackgroundTRD() {
		// FIXME 현재 그냥 로딩하는걸로 되어 있음.
		return new TextureRegionDrawable(new TextureRegion(new Texture(
				Gdx.files.internal("texture/battle/forest.png"))));
	}

}
