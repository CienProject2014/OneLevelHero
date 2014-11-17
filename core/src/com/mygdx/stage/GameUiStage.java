package com.mygdx.stage;

import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.utils.DragAndDrop;
import com.mygdx.controller.ScreenController;
import com.mygdx.enums.ScreenEnum;
import com.mygdx.game.OneLevelHero;
import com.mygdx.inventory.Inventory;
import com.mygdx.inventory.InventoryActor;
import com.mygdx.manager.CurrentManager;
import com.mygdx.model.Hero;
import com.mygdx.resource.Assets;
import com.mygdx.screen.BattleScreen;
import com.mygdx.ui.StatusBarUi;

public class GameUiStage extends Stage {
	Table uiTable;
	OneLevelHero game;
	InventoryActor inventoryActor;
	DragAndDrop dragAndDrop;
	//Message alertMessage; 메시지 필드 임시 보류

	ImageButton downArrowButton;
	ImageButton bagButton;
	ImageButton helpButton;
	ImageButton optionButton;
	TextButton worldMapButton;
	TextButton leftTimeButton;
	TextButton battleButton;
	public static Stage inventoryStage;
	private int battleMemberNumber;

	Image[] characterImage;
	Table toptable;
	Table bottomtable;

	Table[] statusbartable;
	Table[] charatertable;

	StatusBarUi[] hpbar;
	StatusBarUi[] expbar;
	StatusBarUi[] turnbar;

	float realheight;
	float realwidth;

	public GameUiStage() {
		// 초기화
		uiTable = new Table();
		realheight = Assets.realHeight;
		realwidth = Assets.realWidth;
		hpbar = new StatusBarUi[3];
		expbar = new StatusBarUi[3];
		turnbar = new StatusBarUi[3];
		characterImage = new Image[3];
		statusbartable = new Table[3];
		charatertable = new Table[3];
		battleMemberNumber = CurrentManager.getInstance().getParty()
				.getBattleMemberList().size();

		for (int i = 0; i < battleMemberNumber; i++) {
			hpbar[i] = new StatusBarUi("hp", 0f, 100f, 1f, false, Assets.skin);
			expbar[i] = new StatusBarUi("exp", 0f, 100f, 1f, false, Assets.skin);
			turnbar[i] = new StatusBarUi("turn", 0f, 100f, 1f, false,
					Assets.skin);
			statusbartable[i] = new Table(Assets.skin);
			charatertable[i] = new Table(Assets.skin);

		}
		//캐릭터 이미지 세팅
		List<Hero> currentBattleMemberList = CurrentManager.getInstance()
				.getParty().getBattleMemberList();
		Gdx.app.log(
				"currentBattleMemberList",
				String.valueOf(currentBattleMemberList.get(0).getFaceImage()
						.getStage()));
		Gdx.app.log("currentImage",
				String.valueOf(Assets.imageFileList.get("yongsa")));
		for (int i = 0; i < currentBattleMemberList.size(); i++) {
			characterImage[i] = currentBattleMemberList.get(i).getFaceImage();
		}

		toptable = new Table(Assets.skin);
		bottomtable = new Table(Assets.skin);

		TextButtonStyle style = new TextButtonStyle(Assets.nameAndTime,
				Assets.nameAndTime, Assets.nameAndTime, Assets.font);
		downArrowButton = new ImageButton(Assets.downArrowButton,
				Assets.downArrowButton);
		bagButton = new ImageButton(Assets.bagButton, Assets.bagButton);
		worldMapButton = new TextButton("worldMap", style);
		leftTimeButton = new TextButton("12h30m", style);
		helpButton = new ImageButton(Assets.helpButton, Assets.helpButton);
		optionButton = new ImageButton(Assets.optionButton, Assets.optionButton);
		battleButton = new TextButton("Battle", Assets.skin);

		// 인벤토리 Actor 만들기
		dragAndDrop = new DragAndDrop();
		Skin skin = Assets.skin;
		inventoryActor = new InventoryActor(new Inventory(), dragAndDrop, skin);

		//알림 메시지
		/*
		alertMessage = new AlertMessage("[ 보상 ]", Assets.skin).text(
				RewardManager.getInstance().getRewardMessage()).button("확인",
				new InputListener() {
					// button to exit app
					public boolean touchDown(InputEvent event, float x,
							float y, int pointer, int button) {
						RewardManager.getInstance().setCurrentReward(false);

						alertMessage.setVisible(false);
						return true;
					}
				});
		 */
		addListener();
		makeTable();
		addActor(uiTable);
		addActor(inventoryActor); // 인벤토리 Actor 추가
		//addActor(alertMessage);

		//이벤트 보상 메시지가 있는지 확인 (임시 보류)
		/*
		if (RewardManager.getInstance().isCurrentReward()) {
			alertMessage.setVisible(true);
		}
		*/
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
		for (int i = 0; i < battleMemberNumber; i++) {
			charatertable[i].add(characterImage[i]).width(realwidth / 4)
					.height(realheight / 4);
		}
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
				inventoryActor.setVisible(true);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
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
				// TODO Auto-generated method stub
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
				// TODO Auto-generated method stub
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				game.setScreen(new BattleScreen(game));
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
				new ScreenController(ScreenEnum.WORLD_MAP);
			}
		});
		helpButton.addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				//alertMessage.setVisible(true);
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {

			}
		});

		characterImage[0].addListener(new InputListener() {

			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				new ScreenController(ScreenEnum.STATUS);
			}
		});
	}

	@Override
	public void dispose() {
		inventoryActor.remove();
		//alertMessage.remove();
		super.dispose();
	}
}
