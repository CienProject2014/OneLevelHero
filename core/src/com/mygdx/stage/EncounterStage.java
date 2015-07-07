package com.mygdx.stage;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.listener.TouchListener;

public class EncounterStage extends BaseOneLevelStage {
	private TextButton fightButton;
	private TextButton fleeButton;

	private Table selTable;

	public Stage makeStage() {
		fightButton = new TextButton("싸운다", assets.skin);
		fleeButton = new TextButton("도망친다", assets.skin);

		selTable = new Table(assets.skin);
		selTable.setFillParent(true);
		// selTable.row();
		selTable.add(fightButton);
		selTable.add(fleeButton);

		selTable.bottom();
		addActor(selTable); // show selTable

		addListener(); // 리스너 할당

		return this;
	}

	private void addListener() {
		fightButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.BATTLE);
			}
		}));
		fleeButton.addListener(new TouchListener(new Runnable() {
			@Override
			public void run() {
				screenFactory.show(ScreenEnum.MOVING);
			}
		}));
	}
}
