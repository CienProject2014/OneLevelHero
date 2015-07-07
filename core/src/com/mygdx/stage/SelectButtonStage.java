package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PlaceManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.NPC;

public class SelectButtonStage extends OneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private EventInfo eventInfo;

	private List<TextButton> chatButtons;
	private List<TextButtonStyle> chatStyles;
	private int eventCount;
	private NPC eventNpc;
	private final int MAX_EVENT_LENGTH = 6;
	private TextButton exitButton;

	private void addActors() {
		for (TextButton chatButton : chatButtons)
			this.addActor(chatButton);
	}

	private void addEventButtonListener() {
		for (int i = 0; i < eventCount; i++) {
			// 이벤트가 달성되었는지 검사(현재는 리워드)
			if (eventNpc.getEvent(i).getEventState() == EventStateEnum.CLEARED)
				chatButtons.get(i).setColor(Color.DARK_GRAY);
			else {
				chatButtons.get(i).addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent event, float x, float y) {
						screenFactory.show(ScreenEnum.EVENT);
					};
				});
			}
		}
	}

	private void addComponentButtonListener() {
		final List<String> componentString = new ArrayList<>();
		for (int i = 0; i < eventInfo.getCurrentEvent().getEventComponent()
				.size(); i++) {
			componentString.add(eventInfo.getCurrentEvent().getEventComponent()
					.get(i));
			final String currentString = componentString.get(i);
			chatButtons.get(i).addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					storySectionManager.goNextStorySection(currentString);
				};
			});
		}
	}

	public Stage makeStage() {
		chatButtons = new ArrayList<TextButton>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<TextButtonStyle>();

		switch (eventInfo.getEventQueue().peek().getEventType()) {
			case SELECT_EVENT:
				showEventButton();
				setEventButtonPosition();
				addEventButtonListener();
				setExitButton();
				break;
			case SELECT_COMPONENT:
				showComponentButton();
				setComponentButtonPosition();
				addComponentButtonListener();
				break;
			default:
				Gdx.app.log("SelectButtonStage", "eventType 주입 에러");
				break;
		}
		setButtonSize();
		addActors();
		return this;
	}

	private void showEventButton() {
		for (int i = 0; i < eventCount; i++) {
			chatStyles.add(new TextButtonStyle(assets.chatButton[i],
					assets.chatButton[i], assets.chatButton[i], assets.font));
			chatButtons.add(new TextButton("", chatStyles.get(i)));
		}

	}

	private void showComponentButton() {
		for (int i = 0; i < eventInfo.getEventQueue().peek()
				.getEventComponent().size(); i++) {
			chatStyles.add(new TextButtonStyle(assets.chatButton[i],
					assets.chatButton[i], assets.chatButton[i], assets.font));
			chatButtons.add(new TextButton("", chatStyles.get(i)));
		}

	}

	private void setEventButtonPosition() {
		final float buttonPosition[][] = {
				{ assets.windowWidth * 0.109375f, assets.windowHeight * 0.74f },
				{ assets.windowWidth * 0.109375f, assets.windowHeight * 0.555f },
				{ assets.windowWidth * 0.109375f, assets.windowHeight * 0.37f },
				{ assets.windowWidth * 0.68f, assets.windowHeight * 0.74f },
				{ assets.windowWidth * 0.68f, assets.windowHeight * 0.555f },
				{ assets.windowWidth * 0.68f, assets.windowHeight * 0.37f } };
		for (int i = 0; i < eventCount; i++) {
			chatButtons.get(i).setPosition(buttonPosition[i][0],
					buttonPosition[i][1]);
		}
	}

	private void setComponentButtonPosition() {
		final float buttonPosition[][] = {
				{ assets.windowWidth * 0.3f, assets.windowHeight * 0.6f },
				{ assets.windowWidth * 0.6f, assets.windowHeight * 0.6f } };
		for (int i = 0; i < eventInfo.getEventQueue().peek()
				.getEventComponent().size(); i++) {
			chatButtons.get(i).setPosition(buttonPosition[i][0],
					buttonPosition[i][1]);

		}
	}

	private void setExitButton() {
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

	private void setButtonSize() {
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
