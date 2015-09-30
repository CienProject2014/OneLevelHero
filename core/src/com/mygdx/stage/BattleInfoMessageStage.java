package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.UiComponentAssets;
import com.mygdx.enums.EventTypeEnum;
import com.mygdx.manager.BattleManager;
import com.mygdx.manager.StorySectionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.screen.BattleScreen;

public class BattleInfoMessageStage extends BaseOneLevelStage {
	private final String START_BATTLE_MESSAGE = "전투 시작!";
	private final String PLAYER_WIN_MESSAGE = "몬스터를 물리쳤다!";
	@Autowired
	private TextureManager textureManager;
	@Autowired
	private UiComponentAssets uiComponentAssets;
	@Autowired
	private BattleManager battleManager;
	@Autowired
	private StorySectionManager storySectionManager;
	private Table battleMessageTable = new Table();
	private TextButton messageButton;
	private Table battleStatusTable;
	private TextButton statusMessageButton;

	public void act(float delta) {
		showButton();
	}

	private void showButton() {
		final String battleStatusMessage = battleManager.getBattleInfo().getBattleInfoMessage();
		messageButton.setText(battleStatusMessage);
		if (battleStatusMessage.equals(PLAYER_WIN_MESSAGE)) {
			if (battleManager.getSelectedMonster().getDropItemList().size() > 0) {
				statusMessageButton.setText("[" + battleManager.getBattleInfo().getDropItem().getName() + "] 을 얻었다.");
				battleStatusTable.setVisible(true);
				battleManager.getBattleInfo().setGetItem(false);
			} else {
				battleStatusTable.setVisible(false);
			}
		} else {
			battleStatusTable.setVisible(false);
		}

		messageButton.clearListeners();
		messageButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (battleStatusMessage.equals(PLAYER_WIN_MESSAGE)) {
					movingManager.goCurrentLocatePosition();
					storySectionManager.triggerNextSectionEvent(EventTypeEnum.END_BATTLE,
							battleManager.getSelectedMonster().getFacePath());
				} else if (battleStatusMessage.equals(START_BATTLE_MESSAGE)) {
					BattleScreen.showBattleInfoMessage = false;
				}
			}
		});
	}

	public Stage makeStage() {
		super.makeStage();
		TextureRegionDrawable textMenu = new TextureRegionDrawable(
				new TextureRegion(textureManager.getTexture("battleui_battle_message")));
		battleMessageTable.left().bottom();
		battleMessageTable.padLeft(400).padBottom(600);
		final String battleInfoMessage = battleManager.getBattleInfoMessage();
		TextButtonStyle messageStyle = new TextButtonStyle(textMenu, textMenu, textMenu, uiComponentAssets.getFont());
		messageButton = new TextButton(battleInfoMessage, messageStyle);
		battleMessageTable.add(messageButton);

		battleStatusTable = new Table();
		battleStatusTable.left().bottom();
		battleStatusTable.padLeft(400).padBottom(400);
		final String battleStatusMessage = battleManager.getBattleInfoMessage();
		statusMessageButton = new TextButton(battleStatusMessage, messageStyle);
		battleStatusTable.add(statusMessageButton);

		tableStack.add(battleStatusTable);

		tableStack.add(battleMessageTable);
		return this;
	}
}
