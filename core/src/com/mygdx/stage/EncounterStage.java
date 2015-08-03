package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.ScreenEnum;

public class EncounterStage extends BaseOneLevelStage {
	@Autowired
	private UiComponentAssets uiComponentAssets;
	private TextButton fightButton;
	private TextButton fleeButton;

	private Table selTable;

	public Stage makeStage() {
		super.makeStage();
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
				screenFactory.show(ScreenEnum.BATTLE);
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
				screenFactory.show(ScreenEnum.FIELD);
			}
		});
	}

}
