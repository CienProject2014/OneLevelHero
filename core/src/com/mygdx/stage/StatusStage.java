package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.state.Assets;

public class StatusStage extends Stage {
	// 사용될 변수 선언
	Table uiTable;
	Table rightTable;
	Table statusTable;
	Table labelTable;
	Table leftTable;
	Table rightTopTable;
	Table rightBottomTable;

	Skin skin;
	TextButton characterChangeTextButton;
	TextButton statusTextButton;
	TextButton bagTextButton;
	TextButton skillTextButton;
	TextButton exitTextButton;
	TextButton[] characterTextButton;

	Image character;
	Label[] status;

	public StatusStage() {
		skin = Assets.skin;
		// Table 초기화
		uiTable = new Table(skin);
		rightBottomTable = new Table(skin);
		leftTable = new Table(skin);
		rightTopTable = new Table(skin);
		statusTable = new Table(skin);
		labelTable = new Table(skin);
		rightTable = new Table(skin);
		// Label들 초기화
		status = new Label[13];
		// Image 로드
		character = new Image(new Texture(
				Gdx.files.internal("texture/char1.jpg")));
		// TextButton 생성
		characterChangeTextButton = new TextButton("Change", skin);
		statusTextButton = new TextButton("Status", skin);
		bagTextButton = new TextButton("가방", skin);
		skillTextButton = new TextButton("Skill&magic", skin);
		exitTextButton = new TextButton("닫기", skin);
		characterTextButton = new TextButton[3];

		for (int i = 0; i < 3; i++) {
			characterTextButton[i] = new TextButton("누르면 변환", skin);
		}
		for (int i = 0; i < 12; i++) {
			status[i] = new Label("Hello", skin);
		}

		// 리스너 추가
		addListener();
		// 테이블 완성
		makeuiTable();
		addActor(uiTable);

	}

	// 테이블 디자인
	public void makeuiTable() {
		uiTable.setFillParent(true);
		makeLeftTable();
		makeRightTable();
		uiTable.add(leftTable);
		uiTable.add(rightTable);
	}

	public void makeLeftTable() {
		leftTable.setSize(Assets.realWidth / 8, (Assets.realHeight / 12) * 11);
		leftTable.left();
		leftTable.add(statusTextButton);
		leftTable.row();
		leftTable.add(bagTextButton);
		leftTable.row();
		leftTable.add(skillTextButton);
		leftTable.row();
		leftTable.add(exitTextButton);
	}

	public void makeRightTable() {
		rightTable.setSize(Assets.realWidth * 7 / 8,
				Assets.realHeight * 11 / 12);
		makeRightTopTable();
		makeRightBottomTable();
		rightTable.add(rightTopTable);
		rightTable.row();
		rightTable.add(rightBottomTable);
	}

	public void makeRightTopTable() {
		rightTopTable.setSize((Assets.realWidth / 8) * 7,
				(Assets.realHeight / 12) * 11);
		makeStatusTable();
		makeLabelTable();
		rightTopTable.add(statusTable);
		rightTopTable.add(labelTable);
	}

	public void makeRightBottomTable() {
		rightBottomTable.setSize((Assets.realWidth / 8) * 7,
				(Assets.realHeight / 12) * 1);
		rightBottomTable.add(characterTextButton[0]);
		rightBottomTable.add(characterTextButton[1]);
		rightBottomTable.add(characterTextButton[2]);

	}

	public void makeStatusTable() {
		statusTable.setSize((rightTopTable.getWidth() / 3),
				(rightTopTable.getHeight() / 6) * 5);
		statusTable.add(character).width(100).height(100);
		statusTable.row();
		statusTable.add(characterChangeTextButton);
	}

	public void makeLabelTable() {
		labelTable.setSize((rightTopTable.getWidth() * 2 / 3),
				(rightTopTable.getHeight() / 6) * 5);
		labelTable.add(status[0]);
		labelTable.row();
		labelTable.add(status[1]);
		labelTable.add(status[2]);
		labelTable.add(status[3]);
		labelTable.row();
		labelTable.add(status[4]);
		labelTable.add(status[5]);
		labelTable.add(status[6]);
		labelTable.row();
		labelTable.add(status[7]);
		labelTable.add(status[8]);
		labelTable.add(status[9]);
		labelTable.row();
		labelTable.add(status[10]);
		labelTable.add(status[11]);
		labelTable.add(status[12]);

	}

	// 리스너 할당
	public void addListener() {

	}

	@Override
	public void dispose() {
		super.dispose();
	}
}
