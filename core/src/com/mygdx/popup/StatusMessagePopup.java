package com.mygdx.popup;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.assets.StaticAssets;
import com.mygdx.manager.PartyManager;
import com.mygdx.model.unit.Hero;

public class StatusMessagePopup extends MessagePopup {
	@Autowired
	private PartyManager partyManager;

	public StatusMessagePopup(String title, Skin skin,
			List<Hero> battleMemberList) {
		super(title, StaticAssets.skin);
		setPosition(400, 300);
		defaults().space(8);
		row().fill().expandX();

		initialize(battleMemberList);
		setVisible(false);
	}

	private void initialize(List<Hero> battleMemberList) {
		getButtonTable();
		padTop(60); // set padding on top of the dialog title
		getContentTable().defaults(); // set buttons height
		setResizable(false);

		Table table = new Table();
		text("파티원");
		table.row();

		Iterator<Hero> iterator = battleMemberList.iterator();
		while (iterator.hasNext()) {
			Hero nextIterator = iterator.next();
			text("이름 : " + nextIterator.getName());
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
