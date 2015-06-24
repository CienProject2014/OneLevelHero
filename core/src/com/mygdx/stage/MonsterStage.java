package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.model.Monster;

public class MonsterStage extends RootStage {
	@Autowired
	private MovingInfo movingInfo;

	private int windowWidth;
	private int windowHeight;

	private Table table;
	private Table innerTable;
	private float topPadValue; // 몬스터 테이블의 세로축 위치
	private float innerTableWidth; // 몬스터 테이블의 최대 가로 크기
	private float innerTableHeight; // 몬스터 테이블의 최대 세로 크기
	private Monster monster;

	public Stage makeStage() {
		windowWidth = Gdx.graphics.getWidth();
		windowHeight = Gdx.graphics.getHeight();

		topPadValue = windowHeight * 0.125f;
		innerTableWidth = windowWidth * 0.75f;
		innerTableHeight = windowHeight * 0.625f;
		monster = movingInfo.getSelectedMonster();
		setMonsterTable();
		return this;
	}

	// 불러온 몬스터의 이미지를 테이블에 넣는다.
	private void setMonsterTable() {
		table = new Table();
		table.setFillParent(true); // table의 크기를 화면의 크기와 같게
		table.setBackground(getBackgroundTRD());

		innerTable = new Table();
		innerTable.setBackground(getBackgroundTRD());
		innerTable.add(getMonsterImage()); // table 안의 innerTable에 몬스터 이미지 넣는다.

		table.align(Align.top); // table을 위로 정렬
		table.add(innerTable) // table안에 innerTable 넣는다.
			.padTop(topPadValue) // 상단바에 겹치지 않게 위쪽 Padding(1/8)
			.width(innerTableWidth) // 최대 가로 크기(3/4)
			.height(innerTableHeight); // 최대 세로 크기(5/8)

		this.addActor(table);
	}

	private Image getMonsterImage() {
		Texture monsterTexture = monster.getBattleTexture();
		return new Image(monsterTexture);
	}

	private TextureRegionDrawable getBackgroundTRD() {
		return new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("texture/battle/forest.png"))));
	}

	public MovingInfo getMovingInfo() {
		return movingInfo;
	}

	public void setMovingInfo(MovingInfo movingInfo) {
		this.movingInfo = movingInfo;
	}
}
