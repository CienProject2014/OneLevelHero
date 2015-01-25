package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.state.Assets;

@Component
@Scope(value = "prototype")
public class EncounterStage extends Stage {
	@Autowired
	private ScreenFactory screenFactory;
	private TextButton fightButton;
	private TextButton fleeButton;

	private Table selTable;

	public Stage init() {
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

				screenFactory.show(ScreenEnum.MOVING);
			}
		});

	}
}