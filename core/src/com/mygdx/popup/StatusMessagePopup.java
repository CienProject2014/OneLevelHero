package com.mygdx.popup;

import java.util.Iterator;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.manager.CurrentManager;
import com.mygdx.model.Hero;
import com.mygdx.resource.Assets;

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

		text("파티원 : ");
		Iterator<Hero> iterator = CurrentManager.getInstance().getParty()
				.getPartyList().iterator();
		while (iterator.hasNext()) {
			text(iterator.next().getUnitName());
		}

		setMovable(true); // 드래그로 이동가능

		// button("HelloWorld", new InputListener());
		// 다이얼로그 구현
		// 다이얼로그조절

		final Slider volume = new Slider(0f, 100f, 1f, false, Assets.skin);
		volume.setValue(Assets.musicVolume * 100);
		String volumeLabel = String.valueOf(Assets.musicVolume * 100);
		final Label volumeValue = new Label(volumeLabel, Assets.skin);
		Table table = new Table();
		final Slider pan = new Slider(-1f, 1f, 0.1f, false, Assets.skin);
		pan.setValue(0);
		final Label panValue = new Label("0.0", Assets.skin);

		table.add(volume);
		table.add(volumeValue);
		table.row();

		table.add(pan);
		table.add(panValue);

		// table.setFillParent(true);
		table.top();

	}
}
