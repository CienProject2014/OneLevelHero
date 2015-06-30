package com.mygdx.stage;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.currentState.RewardInfo;
import com.mygdx.enums.RewardStateEnum;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.inventory.Inventory;
import com.mygdx.inventory.InventoryPopup;
import com.mygdx.manager.RewardManager;
import com.mygdx.model.Hero;
import com.mygdx.popup.AlertMessagePopup;
import com.mygdx.popup.MessagePopup;
import com.mygdx.popup.StatusMessagePopup;
import com.mygdx.state.StaticAssets;
import com.mygdx.ui.StatusBarUi;

public class GameUiStage extends BaseOneLevelStage {
	@Autowired
	private RewardManager rewardManager;

	private Table uiTable;
	private InventoryPopup inventoryActor;
	private StatusMessagePopup statusMessagePopup;
	private DragAndDrop dragAndDrop;
	private Stack<MessagePopup> alertMessage;

	private ImageButton downArrowButton;
	private ImageButton bagButton;
	private ImageButton helpButton;
	private ImageButton optionButton;
	private TextButton worldMapButton;
	private TextButton leftTimeButton;
	private TextButton battleButton;
	public static Stage inventoryStage;
	private int battleMemberNumber;

	private Image[] characterImage;
	private Table toptable;
	private Table bottomtable;

	private Table[] statusbartable;
	private Table[] charatertable;

	private StatusBarUi[] hpbar;
	private StatusBarUi[] expbar;
	private StatusBarUi[] turnbar;

	private float realheight;
	private float realwidth;

	public Stage makeStage() {
		// 초기화
		uiTable = new Table();
		realheight = StaticAssets.windowHeight;
		realwidth = StaticAssets.windowWidth;
		hpbar = new StatusBarUi[3];
		expbar = new StatusBarUi[3];
		turnbar = new StatusBarUi[3];
		characterImage = new Image[3];
		statusbartable = new Table[3];
		charatertable = new Table[3];
		battleMemberNumber = partyInfo.getBattleMemberList().size();
		alertMessage = new Stack<MessagePopup>();

		for (int i = 0; i < battleMemberNumber; i++) {
			hpbar[i] = new StatusBarUi("hp", 0f, 100f, 1f, false, assets.skin);
			expbar[i] = new StatusBarUi("exp", 0f, 100f, 1f, false, assets.skin);
			turnbar[i] = new StatusBarUi("turn", 0f, 100f, 1f, false,
					assets.skin);
			statusbartable[i] = new Table(assets.skin);
			charatertable[i] = new Table(assets.skin);
		}
		// 캐릭터 이미지 세팅
		List<Hero> currentBattleMemberList = partyInfo.getBattleMemberList();

		for (int i = 0; i < currentBattleMemberList.size(); i++)
			characterImage[i] = new Image(currentBattleMemberList.get(i)
					.getStatusTexture());

		toptable = new Table(assets.skin);
		bottomtable = new Table(assets.skin);

		TextButtonStyle style = new TextButtonStyle(
				assets.atlasUiMap.get("nameAndTime"),
				assets.atlasUiMap.get("nameAndTime"),
				assets.atlasUiMap.get("nameAndTime"), assets.font);
		downArrowButton = new ImageButton(
				assets.atlasUiMap.get("downArrowButton"),
				assets.atlasUiMap.get("downArrowButton"));
		bagButton = new ImageButton(assets.atlasUiMap.get("bagButton"),
				assets.atlasUiMap.get("bagButton"));
		worldMapButton = new TextButton("worldMap", style);
		leftTimeButton = new TextButton("12h30m", style);
		helpButton = new ImageButton(assets.atlasUiMap.get("helpButton"),
				assets.atlasUiMap.get("helpButton"));
		optionButton = new ImageButton(assets.atlasUiMap.get("optionButton"),
				assets.atlasUiMap.get("optionButton"));
		battleButton = new TextButton("Battle", assets.skin);

		// 인벤토리 Actor 만들기
		dragAndDrop = new DragAndDrop();
		inventoryActor = new InventoryPopup(new Inventory(), dragAndDrop,
				assets.skin);

		// 보상 이벤트 처리
		Iterator<RewardInfo> iterator = rewardManager.getRewardQueue()
				.iterator();
		while (iterator.hasNext()) {
			RewardInfo nextIterator = iterator.next();
			if (nextIterator.getRewardState().equals(RewardStateEnum.ING))
				alertMessage.add(new AlertMessagePopup("[ 보상 ]", assets.skin)
						.text(rewardManager.getRewardMessage(nextIterator)));
			Gdx.app.log("리워드정보", nextIterator.getRewardTarget() + ", "
					+ nextIterator.getRewardType());
		}
		// 알림 메시지
		statusMessagePopup = new StatusMessagePopup("[ 스테이터스  ]", assets.skin,
				partyInfo);
		addListener();
		makeTable();
		addActor(uiTable);
		addActor(inventoryActor);
		addActor(statusMessagePopup);

		Iterator<MessagePopup> alertMessageIterator = alertMessage.iterator();
		while (alertMessageIterator.hasNext()) {
			MessagePopup nextIterator = alertMessageIterator.next();
			addActor(nextIterator);
			nextIterator.setVisible(true);
			rewardManager.pollRewardQueue();
		}

		return this;
	}

	// 테이블 디자인
	public void makeTable() {
		uiTable.setFillParent(true);

		toptable.add(downArrowButton).expand().width(realwidth / 8)
				.height(realheight / 12).top().left();
		toptable.add(bagButton).width(realwidth / 8).height(realheight / 12)
				.top();
		toptable.add(worldMapButton).width(realwidth / 4)
				.height(realheight / 12).top();
		toptable.add(leftTimeButton).width(realwidth / 4)
				.height(realheight / 12).top();
		toptable.add(helpButton).width(realwidth / 8).height(realheight / 12)
				.top();
		toptable.add(optionButton).width(realwidth / 8).height(realheight / 12)
				.top();

		for (int i = 0; i < battleMemberNumber; i++) {
			statusbartable[i].add(hpbar[i]).width(realwidth / 12)
					.height(realheight / 12).bottom();
			statusbartable[i].row();
			statusbartable[i].add(expbar[i]).width(realwidth / 12)
					.height(realheight / 12).bottom();
			statusbartable[i].row();
			statusbartable[i].add(turnbar[i]).width(realwidth / 12)
					.height(realheight / 12).bottom();
			bottomtable.add(charatertable[i]);
			bottomtable.add(statusbartable[i]);
		}

		// GameUi의 캐릭터를 동적으로 부여해줌
		for (int i = 0; i < battleMemberNumber; i++)
			charatertable[i].add(characterImage[i]).width(realwidth / 4)
					.height(realheight / 4);

		uiTable.add(toptable).expand().top();
		uiTable.row();
		uiTable.add(bottomtable).bottom();
	}

	// 리스너 할당
	public void addListener() {
		bagButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				inventoryActor.setVisible(true);
				Gdx.app.log("정보", "inventoryPopUp창이 나타납니다.");
			}
		});
		optionButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("정보", "OptionScreen이 나타납니다.");
			}
		});

		downArrowButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.log("정보", "minimap창이 나타납니다.");
			}
		});

		battleButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.BATTLE);
				Gdx.app.log("정보", "전투가 시작됩니다");
			}
		});
		worldMapButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				screenFactory.show(ScreenEnum.WORLD_MAP);
			}
		});
		helpButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
			}
		});
		for (int i = 0; i < partyInfo.getBattleMemberList().size(); i++) {
			final int index = i;
			characterImage[i].addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y) {
					partyInfo.setSelectedInedex(index);
					screenFactory.show(ScreenEnum.STATUS);
				};
			});
		}
	}

	@Override
	public void dispose() {
		inventoryActor.remove();
		super.dispose();
	}

	public RewardManager getRewardManager() {
		return rewardManager;
	}

	public void setRewardManager(RewardManager rewardManager) {
		this.rewardManager = rewardManager;
	}
}
