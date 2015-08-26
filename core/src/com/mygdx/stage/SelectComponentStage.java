package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.SelectComponentListener;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.MovingManager;
import com.mygdx.manager.StorySectionManager;

public class SelectComponentStage extends BaseOneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private MovingManager movingManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private EventCheckManager eventCheckManager;
	@Autowired
	private StorySectionManager storySectionManager;
	@Autowired
	private ListenerFactory listenerFactory;

	private List<TextButton> chatButtons;
	private List<TextButtonStyle> chatStyles;
	private final int MAX_EVENT_LENGTH = 6;
	private TextButton exitButton;
	private int eventSize;

	private void addActors() {
		for (TextButton chatButton : chatButtons)
			this.addActor(chatButton);
	}

	private void addListener() {
		for (int i = 0; i < eventSize; i++) {
			SelectComponentListener selectComponentListener = listenerFactory.getSelectComponentListener();
			selectComponentListener.setIndex(i);
			chatButtons.get(i).addListener(selectComponentListener);
		}
	}

	public Stage makeStage() {
		super.makeStage();
		chatButtons = new ArrayList<TextButton>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<TextButtonStyle>();
		eventSize = eventManager.getCurrentNpcEvent().getEventComponent().size();
		showEventButton();
		setSize();
		setButtonPosition();
		addActors();
		addListener();
		setexitButton();

		return this;
	}

	// StaticAssets.BASE_WINDOW_HEIGHT * 0.185f
	private void setButtonPosition() {
		final float buttonPosition[][] = {
				{StaticAssets.BASE_WINDOW_WIDTH * 0.109375f, StaticAssets.BASE_WINDOW_HEIGHT * 0.74f},
				{StaticAssets.BASE_WINDOW_WIDTH * 0.109375f, StaticAssets.BASE_WINDOW_HEIGHT * 0.555f},
				{StaticAssets.BASE_WINDOW_WIDTH * 0.109375f, StaticAssets.BASE_WINDOW_HEIGHT * 0.37f},
				{StaticAssets.BASE_WINDOW_WIDTH * 0.68f, StaticAssets.BASE_WINDOW_HEIGHT * 0.74f},
				{StaticAssets.BASE_WINDOW_WIDTH * 0.68f, StaticAssets.BASE_WINDOW_HEIGHT * 0.555f},
				{StaticAssets.BASE_WINDOW_WIDTH * 0.68f, StaticAssets.BASE_WINDOW_HEIGHT * 0.37f}};
		for (int i = 0; i < eventSize; i++)
			chatButtons.get(i).setPosition(buttonPosition[i][0], buttonPosition[i][1]);
	}

	private void setexitButton() {
	}

	private void setSize() {
		final float buttonSize[] = {StaticAssets.BASE_WINDOW_WIDTH * 0.208f, StaticAssets.BASE_WINDOW_HEIGHT * 0.185f};
		for (TextButton chatButton : chatButtons)
			chatButton.setSize(buttonSize[0], buttonSize[1]);
	}

	private void showEventButton() {
		for (int i = 0; i < eventSize; i++) {
			chatStyles.add(new TextButtonStyle(uiComponentAssets.getEventButton(), uiComponentAssets.getEventButton(),
					uiComponentAssets.getEventButton(), uiComponentAssets.getFont()));
			chatButtons.add(new TextButton(eventManager.getCurrentNpcEvent().getEventComponent().get(i), chatStyles
					.get(i)));
		}
	}

	public int getEventSize() {
		return eventSize;
	}

	public void setEventSize(int eventSize) {
		this.eventSize = eventSize;
	}
}
