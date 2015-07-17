package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PlaceManager;
import com.mygdx.model.NPC;

public class SelectEventStage extends BaseOneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;

	private List<TextButton> chatButtons;
	private List<TextButtonStyle> chatStyles;
	private final int MAX_EVENT_LENGTH = 6;
	private TextButton exitButton;
	private int eventSize;
	private NPC eventNpc;

	private void addActors() {
		for (TextButton chatButton : chatButtons)
			this.addActor(chatButton);
	}

	private void addListener() {
		for (int i = 0; i < eventSize; i++) {
			// 이벤트가 달성되었는지 검사(현재는 리워드)
			if (eventNpc.getEvent(i).getEventState() == EventStateEnum.CLEARED)
				chatButtons.get(i).setColor(Color.DARK_GRAY);
			else {
				chatButtons.get(i).addListener(new InputListener() {
					@Override
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						return true;
					}

					@Override
					public void touchUp(InputEvent event, float x, float y,
							int pointer, int button) {
						eventManager.setCurrentEventNumber(0);
						screenFactory.show(ScreenEnum.EVENT);
					}
				});
			}
		}
	}

	public Stage makeStage() {
		chatButtons = new ArrayList<TextButton>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<TextButtonStyle>();
		eventNpc = eventManager.getCurrentNpc();
		eventSize = eventNpc.getEvents().size();
		showEventButton();
		setSize();
		setButtonPosition();
		addActors();
		addListener();
		setexitButton();

		return this;
	}

	// StaticAssets.windowHeight * 0.185f
	private void setButtonPosition() {
		final float buttonPosition[][] = {
				{ StaticAssets.windowWidth * 0.109375f,
						StaticAssets.windowHeight * 0.74f },
				{ StaticAssets.windowWidth * 0.109375f,
						StaticAssets.windowHeight * 0.555f },
				{ StaticAssets.windowWidth * 0.109375f,
						StaticAssets.windowHeight * 0.37f },
				{ StaticAssets.windowWidth * 0.68f,
						StaticAssets.windowHeight * 0.74f },
				{ StaticAssets.windowWidth * 0.68f,
						StaticAssets.windowHeight * 0.555f },
				{ StaticAssets.windowWidth * 0.68f,
						StaticAssets.windowHeight * 0.37f } };
		for (int i = 0; i < eventSize; i++)
			chatButtons.get(i).setPosition(buttonPosition[i][0],
					buttonPosition[i][1]);
	}

	private void setexitButton() {
		exitButton = new TextButton("나가기", uiComponentAssets.getSkin());
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

	private void setSize() {
		final float buttonSize[] = { StaticAssets.windowWidth * 0.208f,
				StaticAssets.windowHeight * 0.185f };
		for (TextButton chatButton : chatButtons)
			chatButton.setSize(buttonSize[0], buttonSize[1]);
	}

	private void showEventButton() {
		for (int i = 0; i < eventSize; i++) {
			chatStyles.add(new TextButtonStyle(uiComponentAssets
					.getChatButton()[i], uiComponentAssets.getChatButton()[i],
					uiComponentAssets.getChatButton()[i], uiComponentAssets
							.getFont()));
			chatButtons.add(new TextButton("", chatStyles.get(i)));
		}
	}

	public int getEventSize() {
		return eventSize;
	}

	public void setEventSize(int eventSize) {
		this.eventSize = eventSize;
	}
}
