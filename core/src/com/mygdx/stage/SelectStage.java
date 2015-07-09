package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.currentState.EventInfo;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PlaceManager;
import com.mygdx.manager.StorySectionManager;

public class SelectStage extends OneLevelStage {
	@Autowired
	protected EventManager eventManager;
	@Autowired
	protected PlaceManager placeManager;
	@Autowired
	protected StorySectionManager storySectionManager;
	@Autowired
	protected EventInfo eventInfo;

	protected List<TextButton> chatButtons;
	protected List<TextButtonStyle> chatStyles;
	protected int eventCount;
	protected final int MAX_EVENT_LENGTH = 6;
	protected TextButton exitButton;

	protected void addActors() {
		for (TextButton chatButton : chatButtons)
			this.addActor(chatButton);
	}

	public Stage makeStage() {
		chatButtons = new ArrayList<TextButton>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<TextButtonStyle>();
		setButtonSize();
		addActors();
		return this;
	}

	protected void setExitButton() {
		exitButton = new TextButton("나가기", assets.skin);
		exitButton.center();
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				placeManager.goPreviousPlace();
				event.getListenerActor().setVisible(false);
			}
		});

		addActor(exitButton);
	}

	protected void setButtonSize() {
		final float buttonSize[] = { assets.windowWidth * 0.208f,
				assets.windowHeight * 0.185f };
		for (TextButton chatButton : chatButtons)
			chatButton.setSize(buttonSize[0], buttonSize[1]);
	}

	public EventManager getEventManager() {
		return eventManager;
	}

	public void setEventManager(EventManager eventManager) {
		this.eventManager = eventManager;
	}

	public PlaceManager getPlaceManager() {
		return placeManager;
	}

	public void setPlaceManager(PlaceManager placeManager) {
		this.placeManager = placeManager;
	}

	public EventInfo getEventInfo() {
		return eventInfo;
	}

	public void setEventInfo(EventInfo eventInfo) {
		this.eventInfo = eventInfo;
	}

	public StorySectionManager getStorySectionManager() {
		return storySectionManager;
	}

	public void setStorySectionManager(StorySectionManager storySectionManager) {
		this.storySectionManager = storySectionManager;
	}
}
