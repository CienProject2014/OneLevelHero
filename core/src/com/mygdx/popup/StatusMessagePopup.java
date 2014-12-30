package com.mygdx.popup;

import java.util.Iterator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.model.Hero;
import com.mygdx.state.Assets;
import com.mygdx.state.CurrentState;

public class StatusMessagePopup extends MessagePopup {
	Stage stage;

	public StatusMessagePopup(String title, Skin skin) {
		super(title, Assets.skin);

		setPosition(400, 300);
		defaults().space(8);
		row().fill().expandX();

		initialize();
		setVisible(false);
	}

	private void initialize() {
		getButtonTable();
		padTop(60); // set padding on top of the dialog title
		getContentTable().defaults(); // set buttons height
		setResizable(false);

		Table table = new Table();
		text("파티원");
		table.row();
		Iterator<Hero> iterator = CurrentState.getInstance().getParty()
				.getBattleMemberList().iterator();
		while (iterator.hasNext()) {
			Hero nextIterator = iterator.next();
			text("이름 : " + nextIterator.getUnitName());
			text(", " + nextIterator.getStatus().toString());
		}

		setMovable(true); // 드래그로 이동가능

		// button("HelloWorld", new InputListener());
		// 다이얼로그 구현
		// 다이얼로그조절

		table.row();

		// table.setFillParent(true);
		table.top();

	}
}
