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
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PlaceManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.StorySectionPacket;

public class SelectComponentStage extends BaseOneLevelStage {
	@Autowired
	private EventManager eventManager;
	@Autowired
	private PlaceManager placeManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
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

	class SelectComponentClickListener extends ClickListener {
		private int index;

		@Override
		public void clicked(InputEvent event, float x, float y) {
			List<StorySectionPacket> nextStorySections = storySectionManager
					.getNestSections();

			//iterator 사용시 java.util.ConcurrentModificationException 에러 발생
			for (int i = 0; i < nextStorySections.size(); i++) {
				StorySectionPacket nextSectionPacket = nextStorySections.get(i);
				if (eventManager.getCurrentEvent().getEventComponent()
						.get(getIndex())
						.equals(nextSectionPacket.getTargetComponent())) {
					storySectionManager.setNewStorySection(nextSectionPacket
							.getNextSectionNumber());
					storySectionManager.insertStorySequence();
					storySectionManager.runStorySequence();
					return;
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
