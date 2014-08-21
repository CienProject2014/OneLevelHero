package com.mygdx.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.resource.Assets;

public class StatusUi extends Stage {
	// 사용될 변수 선언
	Table uiTable;
	Table characterTable;
	Table statusTable;
	Table leftTable;
	Table rightTable;

	Button characterChangeButton;
	Button statusButton;
	Button bagButton;
	Button skillButton;
	Button exitButton;
	Button[] characterButton;

	Image character;
	Label[] status;

	float realheight;
	float realwidth;

	public StatusUi() {

		// Table 초기화
		characterTable = new Table(Assets.skin);
		leftTable = new Table(Assets.skin);
		rightTable = new Table(Assets.skin);
		uiTable = new Table(Assets.skin);
		statusTable = new Table();
		// Assets에서 필요한 파일 로드
		Assets.gameUILoad();
		Assets.fontLoad();
		// 실제 해상도 변수에 저장
		realheight = Assets.realHeight;
		realwidth = Assets.realWidth;
		// Label들 초기화
		status = new Label[13];
		// Image 로드
		character = new Image(new Texture(
				Gdx.files.internal("texture/char1.jpg")));
		// Button 생성
		characterChangeButton = new Button(Assets.skin);
		statusButton = new Button();
		bagButton = new Button(Assets.skin);
		skillButton = new Button(Assets.skin);
		exitButton = new Button(Assets.skin);
		characterButton = new Button[3];

		for (int i = 0; i < 3; i++) {
			characterButton[i] = new Button();
			characterButton[i].setSkin(Assets.skin);
		}
		// 리스너 추가
		addListener();
		// 테이블 완성
		makeuiTable();
		addActor(uiTable);

	}

	// 테이블 디자인
	public void makeLeftTable() {
		leftTable.setSize(realwidth / 8, (realheight / 12) * 11);
		// leftTable.add(statusButton);
		// leftTable.row();
		// leftTable.add(bagButton);
		// leftTable.row();
		// leftTable.add(skillButton);
		// leftTable.row();
		// leftTable.add(exitButton);
	}

	public void makeStatusTable() {
		statusTable.setSize((rightTable.getWidth() / 3) * 2,
				(rightTable.getHeight() / 6) * 5);
		statusTable.add(character);
		// statusTable.row();
		// statusTable.add(characterChangeButton);
	}

	public void makeCharacterTable() {
		characterTable.setSize(rightTable.getWidth(),
				rightTable.getHeight() / 6);
		// characterTable.add(characterButton[0]);
		// characterTable.add(characterButton[1]);
		// characterTable.add(characterButton[2]);
	}

	public void makeRightTable() {
		rightTable.setSize((realwidth / 8) * 7, (realheight / 12) * 11);
		makeCharacterTable();
		makeStatusTable();
		rightTable.add(characterTable);
		rightTable.add(statusTable);
	}

	public void makeuiTable() {

		makeLeftTable();
		makeRightTable();
		uiTable.add(leftTable);
		uiTable.add(rightTable);

	}

	// 리스너 할당
	public void addListener() {

	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
