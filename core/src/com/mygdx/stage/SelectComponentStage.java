package com.mygdx.stage;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.currentState.StorySectionInfo;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.model.NextSectionPacket;

public class SelectComponentStage extends SelectStage {
	@Autowired
	private StorySectionInfo storySectionInfo;
	@Autowired
	private StorySectionManager storySectionManager;

	public Stage makeStage() {
		chatButtons = new ArrayList<TextButton>(MAX_EVENT_LENGTH);
		chatStyles = new ArrayList<TextButtonStyle>();
		showComponentButton();
		setComponentButtonPosition();
		addComponentButtonListener();
		setButtonSize();
		addActors();
		return this;
	}

	private void showComponentButton() {
		for (int i = 0; i < eventInfo.getCurrentEvent().getEventComponent()
				.size(); i++) {
			chatStyles.add(new TextButtonStyle(assets.chatButton[i],
					assets.chatButton[i], assets.chatButton[i], assets.font));
			chatButtons.add(new TextButton("", chatStyles.get(i)));
		}

	}

	private void setComponentButtonPosition() {
		final float buttonPosition[][] = {
				{ assets.windowWidth * 0.3f, assets.windowHeight * 0.6f },
				{ assets.windowWidth * 0.6f, assets.windowHeight * 0.6f } };
		for (int i = 0; i < eventInfo.getCurrentEvent().getEventComponent()
				.size(); i++) {
			chatButtons.get(i).setPosition(buttonPosition[i][0],
					buttonPosition[i][1]);

		}
	}

	private void addComponentButtonListener() {
		for (int i = 0; i < eventInfo.getCurrentEvent().getEventComponent()
				.size(); i++) {
			SelectComponentClickListener selectListener = new SelectComponentClickListener();
			selectListener.setIndex(i);
			chatButtons.get(i).addListener(selectListener);
		}
	}

	class SelectComponentClickListener extends ClickListener {
		private int index;

		@Override
		public void clicked(InputEvent event, float x, float y) {
			List<NextSectionPacket> nextStorySectionList = getStorySectionInfo()
					.getNextStorySectionList();

			//iterator 사용시 java.util.ConcurrentModificationException 에러 발생
			for (int i = 0; i < nextStorySectionList.size(); i++) {
				NextSectionPacket nextSectionPacket = nextStorySectionList
						.get(i);
				if (eventInfo.getCurrentEvent().getEventComponent()
						.get(getIndex())
						.equals(nextSectionPacket.getTargetComponent())) {
					storySectionManager.runNewSection(nextSectionPacket
							.getNextSectionNumber());
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

	public StorySectionInfo getStorySectionInfo() {
		return storySectionInfo;
	}

	public void setStorySectionInfo(StorySectionInfo storySectionInfo) {
		this.storySectionInfo = storySectionInfo;
	}

	public StorySectionManager getStorySectionManager() {
		return storySectionManager;
	}

	public void setStorySectionManager(StorySectionManager storySectionManager) {
		this.storySectionManager = storySectionManager;
	}

}
