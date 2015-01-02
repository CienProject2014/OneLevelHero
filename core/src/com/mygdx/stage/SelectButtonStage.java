package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.manager.EventManager;
import com.mygdx.model.EventInfo;
import com.mygdx.model.NPC;
import com.mygdx.state.Assets;

public class SelectButtonStage extends Stage {

	private List<TextButton> chatButtons;
	private List<TextButtonStyle> chatStyles;
	private int eventCount;
	private NPC eventNpc;
	private static final int MAX_EVENT_LENGTH = 6;
	private static final int BUTTON_WIDTH = 0;
	private static final int BUTTON_HEIGHT = 1;
	private final float buttonPosition[][] = {
			{ Assets.realWidth * 0.109375f, Assets.realHeight * 0.74f },
			{ Assets.realWidth * 0.109375f, Assets.realHeight * 0.555f },
			{ Assets.realWidth * 0.109375f, Assets.realHeight * 0.37f },
			{ Assets.realWidth * 0.68f, Assets.realHeight * 0.74f },
			{ Assets.realWidth * 0.68f, Assets.realHeight * 0.555f },
			{ Assets.realWidth * 0.68f, Assets.realHeight * 0.37f } };
	private final float buttonSize[] = { Assets.realWidth * 0.208f,
			Assets.realHeight * 0.185f };

	public SelectButtonStage() {
		EventInfo eventInfo = EventManager.getEventInfo();
		eventNpc = eventInfo.getNpc();
		eventCount = eventNpc.getEventCount();
		chatButtons = new ArrayList<>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<>(MAX_EVENT_LENGTH);

		showEventButton();
		setSize();
		setButtonPosition();
		addActors();
		addListener();

	}

	private void showEventButton() {
		for (int i = 0; i < eventCount; i++) {
			chatStyles.add(new TextButtonStyle(Assets.chatButton[i],
					Assets.chatButton[i], Assets.chatButton[i], Assets.font));
			chatButtons.add(new TextButton("", chatStyles.get(i)));
		}
	}

	private void setSize() {
		for (TextButton chatButton : chatButtons) {
			chatButton.setSize(buttonSize[BUTTON_WIDTH],
					buttonSize[BUTTON_HEIGHT]);
		}
	}

	// Assets.realHeight * 0.185f
	private void setButtonPosition() {
		for (int i = 0; i < eventCount; i++) {
			chatButtons.get(i).setPosition(buttonPosition[i][BUTTON_WIDTH],
					buttonPosition[i][BUTTON_HEIGHT]);
		}
	}

	private void addListener() {
		for (int i = 0; i < eventCount; i++) {
			//이벤트가 달성되었는지 검사(현재는 리워드)
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
						EventInfo eventInfo = EventManager.getEventInfo();
						EventManager.setEventInfo(eventInfo.getNpc(), 0, false);
						new ScreenController(ScreenEnum.EVENT);
					}
				});
			}
		}
	}

	private void addActors() {
		for (TextButton chatButton : chatButtons) {
			this.addActor(chatButton);
		}
	}
}
