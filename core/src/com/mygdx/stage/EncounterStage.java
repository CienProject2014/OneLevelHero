package com.mygdx.stage;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.state.Assets;

public class EncounterStage extends Stage {
	private TextButton fightButton;
	private TextButton fleeButton;

	private Table selTable;

	public EncounterStage() {
		fightButton = new TextButton("싸운다", Assets.skin);
		fleeButton = new TextButton("도망친다", Assets.skin);

		selTable = new Table(Assets.skin);
		selTable.setFillParent(true);
		//selTable.row();
		selTable.add(fightButton);
		selTable.add(fleeButton);

		selTable.bottom();
		addActor(selTable); // show selTable 

		addListener(); // 리스너 할당
	}

	private void addListener() {
		fightButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				// BattleScreen으로 넘어가는 것이 전투를 시작하는 것을 의미
				new ScreenController(ScreenEnum.BATTLE);
			}
		});
		fleeButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

				new ScreenController(ScreenEnum.MOVING);
			}
		});

	}
}