package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.currentState.EventInfo;
import com.mygdx.enums.EventStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PlaceManager;
import com.mygdx.model.NPC;
import com.mygdx.state.Assets;

@Component
@Scope(value = "prototype")
public class SelectButtonStage extends Stage {
	@Autowired
	private Assets assets;
	@Autowired
	private ScreenFactory screenFactory;
	@Autowired
	private EventManager eventManager;
	@Autowired
	private EventInfo eventInfo;
	@Autowired
	private PlaceManager placeManager;
	private List<TextButton> chatButtons;
	private List<TextButtonStyle> chatStyles;
	private int eventCount;
	private NPC eventNpc;
	private final int MAX_EVENT_LENGTH = 6;
	private TextButton exitButton;

	public Stage makeStage() {

		eventNpc = eventInfo.getNpc();
		eventCount = eventNpc.getEventCount();
		chatButtons = new ArrayList<TextButton>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<TextButtonStyle>();

		showEventButton();
		setSize();
		setButtonPosition();
		addActors();
		addListener();
		setexitButton();
		return this;
	}

	private void setexitButton() {
		exitButton = new TextButton("나가기", assets.skin);
		exitButton.center();
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				// TODO Auto-generated method stub
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

	private void showEventButton() {
		for (int i = 0; i < eventCount; i++) {
			chatStyles.add(new TextButtonStyle(assets.chatButton[i],
					assets.chatButton[i], assets.chatButton[i], assets.font));
			chatButtons.add(new TextButton("", chatStyles.get(i)));
		}
	}

	private void setSize() {
		final float buttonSize[] = { assets.windowWidth * 0.208f,
				assets.windowHeight * 0.185f };
		for (TextButton chatButton : chatButtons) {
			chatButton.setSize(buttonSize[0], buttonSize[1]);
		}
	}

	// assets.windowHeight * 0.185f
	private void setButtonPosition() {
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
						eventManager.setEventInfo(eventInfo.getNpc(), 0, false);
						screenFactory.show(ScreenEnum.EVENT);
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
