package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.EventElementEnum;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.ChoiceGameObjectEventListener;
import com.mygdx.listener.ChoiceNpcEventListener;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.model.event.Event;
import com.mygdx.model.event.EventElement;

public class ChoiceEventStage extends BaseOneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private ListenerFactory listenerFactory;
	private EventElement eventElement;
	private EventElementEnum currentElementType;
	private List<TextButton> chatButtons;
	private List<TextButtonStyle> chatStyles;
	private final int MAX_EVENT_LENGTH = 6;
	private int eventSize;

	private void addActors() {
		for (TextButton chatButton : chatButtons)
			this.addActor(chatButton);
	}

	private void addListener() {
		int visibleEventIndex = 0;
		for (int eventNumber = 0; eventNumber < eventElement.getEvents().size(); eventNumber++) {
			Event selectedEvent = eventElement.getEvent(eventNumber + 1);
			if (selectedEvent == null) {
				continue;
			}
			switch (selectedEvent.getEventState()) {
				case ALWAYS_OPEN :
				case OPENED :
					if (currentElementType.equals(EventElementEnum.NPC)) {
						ChoiceNpcEventListener choiceNpcEventListener = listenerFactory.getChoiceNpcEventListener();
						choiceNpcEventListener.setIndex(eventNumber + 1);
						chatButtons.get(visibleEventIndex).addListener(choiceNpcEventListener);
					} else {
						ChoiceGameObjectEventListener choiceGameObjectEventListener = listenerFactory
								.getChoiceGameObjectEventListener();
						choiceGameObjectEventListener.setIndex(eventNumber + 1);
						chatButtons.get(visibleEventIndex).addListener(choiceGameObjectEventListener);
					}
					visibleEventIndex++;
					break;
				case CLEARED :
					chatButtons.get(visibleEventIndex).setColor(Color.DARK_GRAY);
					chatButtons.get(visibleEventIndex).setTouchable(Touchable.disabled);
					break;
				case CLOSED :
				case NOT_OPENED :
					break;
				default :
					Gdx.app.log("ChoiceEventStage", "EventState 정보 오류");
					break;
			}
		}
	}

	public Stage makeStage() {
		chatButtons = new ArrayList<TextButton>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<TextButtonStyle>();
		setCurrentElementType(positionManager);
		showEventButton();
		setSize();
		setButtonPosition();
		addActors();
		addListener();
		setexitButton();

		return this;
	}

	private void setCurrentElementType(PositionManager positionManager) {
		switch (positionManager.getCurrentEventPositionType()) {
			case NPC :
				currentElementType = EventElementEnum.NPC;
				break;
			case GAME_OBJECT :
				currentElementType = EventElementEnum.GAME_OBJECT;
				break;
			default :
				Gdx.app.log("ChoiceEventStage", "잘못된 EventElement정보" + positionManager.getCurrentEventPositionType());
				break;
		}
		eventElement = eventManager.getCurrentEventElement(currentElementType);
		eventSize = eventElement.getVisibleEventSize();
	}

	// StaticAssets.windowHeight * 0.185f
	private void setButtonPosition() {
		final float buttonPosition[][] = {{StaticAssets.windowWidth * 0.109375f, StaticAssets.windowHeight * 0.74f},
				{StaticAssets.windowWidth * 0.109375f, StaticAssets.windowHeight * 0.555f},
				{StaticAssets.windowWidth * 0.109375f, StaticAssets.windowHeight * 0.37f},
				{StaticAssets.windowWidth * 0.68f, StaticAssets.windowHeight * 0.74f},
				{StaticAssets.windowWidth * 0.68f, StaticAssets.windowHeight * 0.555f},
				{StaticAssets.windowWidth * 0.68f, StaticAssets.windowHeight * 0.37f}};
		for (int i = 0; i < eventSize; i++)
			chatButtons.get(i).setPosition(buttonPosition[i][0], buttonPosition[i][1]);
	}

	private void setexitButton() {
	}

	private void setSize() {
		final float buttonSize[] = {StaticAssets.windowWidth * 0.208f, StaticAssets.windowHeight * 0.185f};
		for (TextButton chatButton : chatButtons)
			chatButton.setSize(buttonSize[0], buttonSize[1]);
	}

	private void showEventButton() {
		int visibleEventCount = 0;
		for (int eventNumber = 0; eventNumber < eventElement.getEvents().size(); eventNumber++) {
			if (eventElement.getEvent(eventNumber + 1) == null) {
				continue;
			}
			if (EventManager.isEventVisible(eventElement.getEvent(eventNumber + 1))) {
				chatStyles.add(new TextButtonStyle(uiComponentAssets.getEventButton(), uiComponentAssets
						.getEventButton(), uiComponentAssets.getEventButton(), uiComponentAssets.getFont()));
				chatButtons.add(new TextButton(eventElement.getEvent(eventNumber + 1).getEventTitle(), chatStyles
						.get(visibleEventCount)));
				visibleEventCount++;
			}
		}
	}
}
