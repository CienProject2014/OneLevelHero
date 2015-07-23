package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.manager.EventCheckManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.StorySectionPacket;

public class SelectComponentStage extends BaseOneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private EventCheckManager eventCheckManager;
	@Autowired
	private StorySectionManager storySectionManager;

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
			SelectComponentClickListener selectListener = new SelectComponentClickListener();
			selectListener.setIndex(i);
			chatButtons.get(i).addListener(selectListener);
		}
	}

	public Stage makeStage() {
		super.makeStage();
		chatButtons = new ArrayList<TextButton>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<TextButtonStyle>();
		eventSize = eventManager.getCurrentEvent().getEventComponent().size();
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
				{ StaticAssets.BASE_WINDOW_WIDTH * 0.109375f,
						StaticAssets.BASE_WINDOW_HEIGHT * 0.74f },
				{ StaticAssets.BASE_WINDOW_WIDTH * 0.109375f,
						StaticAssets.BASE_WINDOW_HEIGHT * 0.555f },
				{ StaticAssets.BASE_WINDOW_WIDTH * 0.109375f,
						StaticAssets.BASE_WINDOW_HEIGHT * 0.37f },
				{ StaticAssets.BASE_WINDOW_WIDTH * 0.68f,
						StaticAssets.BASE_WINDOW_HEIGHT * 0.74f },
				{ StaticAssets.BASE_WINDOW_WIDTH * 0.68f,
						StaticAssets.BASE_WINDOW_HEIGHT * 0.555f },
				{ StaticAssets.BASE_WINDOW_WIDTH * 0.68f,
						StaticAssets.BASE_WINDOW_HEIGHT * 0.37f } };
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
				positionManager.goCurrentPlace();
				event.getListenerActor().setVisible(false);
			}
		});

		addActor(exitButton);
	}

	private void setSize() {
		final float buttonSize[] = { StaticAssets.BASE_WINDOW_WIDTH * 0.208f,
				StaticAssets.BASE_WINDOW_HEIGHT * 0.185f };
		for (TextButton chatButton : chatButtons)
			chatButton.setSize(buttonSize[0], buttonSize[1]);
	}

	private void showEventButton() {
		for (int i = 0; i < eventSize; i++) {
			chatStyles.add(new TextButtonStyle(uiComponentAssets
					.getEventButton(), uiComponentAssets.getEventButton(),
					uiComponentAssets.getEventButton(), uiComponentAssets
							.getFont()));
			chatButtons.add(new TextButton(eventManager.getCurrentEvent()
					.getEventComponent().get(i), chatStyles.get(i)));
		}
	}

	public int getEventSize() {
		return eventSize;
	}

	public void setEventSize(int eventSize) {
		this.eventSize = eventSize;
	}

	public class SelectComponentClickListener extends ClickListener {
		private int index;

		@Override
		public void clicked(InputEvent event, float x, float y) {

			for (StorySectionPacket nextStorySectionPacket : storySectionManager
					.getNextSections()) {
				if (eventCheckManager.checkSelectComponent(getIndex(),
						nextStorySectionPacket)) {
					storySectionManager
							.setNewStorySectionAndPlay(nextStorySectionPacket
									.getNextSectionNumber());
					break;
				}
			}
		}

		public int getIndex() {
			return index;
		}

		public void setIndex(int index) {
			this.index = index;
		}

	}
}
