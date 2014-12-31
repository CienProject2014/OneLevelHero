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

	private List<TextButton> chatButton;
	private List<TextButtonStyle> chatStyle;
	private int eventCount;
	private NPC eventNpc;

	public SelectButtonStage() {
		EventInfo eventInfo = EventManager.getEventInfo();
		eventNpc = eventInfo.getNpc();
		eventCount = eventNpc.getEventCount();
		chatButton = new ArrayList<TextButton>();
		chatStyle = new ArrayList<TextButtonStyle>();

		showEventButton();
		setSize();
		setPosition();
		addActors();
		addListener();

	}

	private void showEventButton() {
		for (int i = 0; i < eventCount; i++) {
			chatStyle.add(new TextButtonStyle(Assets.chatButton[i],
					Assets.chatButton[i], Assets.chatButton[i], Assets.font));
			chatButton.add(new TextButton("", chatStyle.get(i)));
		}
	}

	private void setSize() {
		for (int i = 0; i < eventCount; i++) {
			chatButton.get(i).setSize(Assets.realWidth * 0.208f,
					Assets.realHeight * 0.185f);
		}
	}

	// Assets.realHeight * 0.185f
	private void setPosition() {
		switch (eventCount) {
			case 1:
				chatButton.get(0).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.74f);
				break;
			case 2:
				chatButton.get(0).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.74f);
				chatButton.get(1).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.555f);
				break;
			case 3:
				chatButton.get(0).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.74f);
				chatButton.get(1).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.555f);
				chatButton.get(2).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.37f);
				break;
			case 4:
				chatButton.get(0).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.74f);
				chatButton.get(1).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.555f);
				chatButton.get(2).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.37f);
				chatButton.get(3).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.74f);
				break;
			case 5:
				chatButton.get(0).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.74f);
				chatButton.get(1).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.555f);
				chatButton.get(2).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.37f);
				chatButton.get(3).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.74f);
				chatButton.get(4).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.555f);
				break;
			case 6:
				chatButton.get(0).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.74f);
				chatButton.get(1).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.555f);
				chatButton.get(2).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.37f);
				chatButton.get(3).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.74f);
				chatButton.get(4).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.555f);
				chatButton.get(5).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.37f);
				break;
			default:
				chatButton.get(0).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.74f);
				chatButton.get(1).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.555f);
				chatButton.get(2).setPosition(Assets.realWidth * 0.109375f,
						Assets.realHeight * 0.37f);
				chatButton.get(3).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.74f);
				chatButton.get(4).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.555f);
				chatButton.get(5).setPosition(Assets.realWidth * 0.68f,
						Assets.realHeight * 0.37f);

				break;
		}

	}

	private void addListener() {
		for (int i = 0; i < eventCount; i++) {
			//이벤트가 달성되었는지 검사(현재는 리워드)
			if (eventNpc.getEvent(i).getEventState() == EventStateEnum.CLEARED)
				chatButton.get(0).setColor(Color.DARK_GRAY);
			else {
				chatButton.get(i).addListener(new InputListener() {
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
		for (int i = 0; i < eventCount; i++) {
			this.addActor(chatButton.get(i));
		}
	}
}
