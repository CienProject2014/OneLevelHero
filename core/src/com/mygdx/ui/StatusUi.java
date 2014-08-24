package com.mygdx.ui;

import static com.mygdx.resource.Assets.fontLoad;
import static com.mygdx.resource.Assets.gameUILoad;
import static com.mygdx.resource.Assets.realHeight;
import static com.mygdx.resource.Assets.realWidth;
import static com.mygdx.resource.Assets.skin;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class StatusUi extends Stage {
	// 사용될 변수 선언
	Table uiTable;
	Table characterTable;
	Table statusTable;
	Table labelTable;
	Table leftTable;
	Table rightTable;
	Table rightTable2;

	TextButton characterChangeTextButton;
	TextButton statusTextButton;
	TextButton bagTextButton;
	TextButton skillTextButton;
	TextButton exitTextButton;
	TextButton[] characterTextButton;

	Image character;
	Label[] status;

	public StatusUi() {

		// Table 초기화
		characterTable = new Table(skin);
		leftTable = new Table(skin);
		rightTable = new Table(skin);
		rightTable2 = new Table(skin);
		statusTable = new Table(skin);
		labelTable = new Table(skin);
		// Assets에서 필요한 파일 로드
		gameUILoad();
		fontLoad();
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
		makeLeftTable();
		makeRightTable();
		makeRightTable2();
		addActor(leftTable);
		addActor(rightTable);
		addActor(rightTable2);

	}

	// 테이블 디자인
	public void makeLeftTable() {
		leftTable.setSize(realWidth / 8, (realHeight / 12) * 11);
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
		rightTable.setSize((realWidth / 8) * 7, (realHeight / 12) * 11);
		rightTable.right().top();
		makeStatusTable();
		makeLabelTable();
		rightTable.add(statusTable);
		rightTable.add(labelTable);
	}

	public void makeRightTable2() {

		makeCharacterTable();
		rightTable2.add(characterTable);
		rightTable2.right().bottom();
	}

	public void makeStatusTable() {
		statusTable.setSize((rightTable.getWidth() / 3),
				(rightTable.getHeight() / 6) * 5);
		statusTable.add(character);
		statusTable.row();
		statusTable.add(characterChangeTextButton);
	}

	public void makeCharacterTable() {
		characterTable.setSize(rightTable.getWidth(),
				rightTable.getHeight() / 6);
		characterTable.add(characterTextButton[0]);
		characterTable.add(characterTextButton[1]);
		characterTable.add(characterTextButton[2]);
	}

	public void makeLabelTable() {
		labelTable.setSize((rightTable.getWidth() * 2 / 3),
				(rightTable.getHeight() / 6) * 5);
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
