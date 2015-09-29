package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.assets.AtlasUiAssets;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.enums.DungeonEnum;
import com.mygdx.enums.DungeonEnum.Direction;
import com.mygdx.enums.PositionEnum.EventPosition;
import com.mygdx.factory.ListenerFactory;
import com.mygdx.listener.DungeonDoorButtonListener;
import com.mygdx.listener.DungeonStairButtonListener;
import com.mygdx.listener.LeaveDungeonButtonListener;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.EventManager;
import com.mygdx.manager.PositionManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.manager.TimeManager;

/**
 * @author Velmont
 * 
 */

public class DungeonStage extends BaseOneLevelStage {
	private static final String BG_DOOR[] = {"02", "03", "04", "05"};
	private static final String BG_DOOR_GATE = "gate";
	private static final String BG_DOOR_UP_STAIR = "up";
	private static final String BG_DOOR_DOWN_STAIR = "down";
	private static final int DOOR_POSITION[][] = {{80, 440}, {898, 440}, {1688, 440}};
	private static final int TOTAL_DOOR_SIZE = 3;
	private static final int INDEX_OF_LEFT = 0;
	private static final int INDEX_OF_MID = 1;
	private static final int INDEX_OF_RIGHT = 2;
	private String currentRoomLabel = new String();
	private Direction currentDirection;

	@Autowired
	private AtlasUiAssets atlasUiAssets;
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private TimeManager timeManager;
	@Autowired
	private ConstantsAssets constantsAssets;
	@Autowired
	private PositionManager positionManager;
	@Autowired
	private transient TextureManager textureManager;
	@Autowired
	private ListenerFactory listenerFactory;
	@Autowired
	private EventManager eventManager;
	private ImageButton[] goDoorButton = new ImageButton[3];
	private Table doorTable[] = new Table[3];
	private Table outerTable = new Table();
	private Table otherButtonTable = new Table();

	public Stage makeStage() {
		super.makeStage();
		setDungeonInfo(dungeonManager);

		showBackground(dungeonManager);
		setChangeDirectionButton(dungeonManager);
		setDoorButton(dungeonManager);
		showDoorButton(dungeonManager);
		setOtherButtons(dungeonManager);
		return this;
	}

	private void setDungeonInfo(DungeonManager dungeonManager) {
		if (positionManager.getCurrentEventPositionType().equals(EventPosition.DUNGEON)) {
			dungeonManager.setDungeonEventInfo();
			positionManager.setCurrentEventPositionType(EventPosition.NONE);
		} else {
			setInitialDungeonInfo(dungeonManager);
		}
	}

	@Override
	public void act(float delta) {
		super.act(delta);
		showBackground(dungeonManager);
		showDoorButton(dungeonManager);
		setOtherButtons(dungeonManager);
	}

	private void setInitialDungeonInfo(DungeonManager dungeonManager) {
		if (positionManager.getCurrentSubNodePath() != null) {
			dungeonManager.setInitialDungeonInfo(positionManager.getCurrentSubNodePath());
		} else {
			dungeonManager.setInitialDungeonInfo("devil_castle");
		}
	}

	private void showBackground(DungeonManager dungeonManager) {
		String backgroundPath;
		String subNodePath = dungeonManager.getDungeonInfo().getCurrentDungeon().getSubNodePath();
		switch (dungeonManager.getCurrentDoorSize()) {
			case 0 :
				backgroundPath = getBackgroundPathByType(dungeonManager);
				break;
			case 1 :
				backgroundPath = subNodePath + "_" + BG_DOOR[1];
				break;
			case 2 :
				backgroundPath = subNodePath + "_" + BG_DOOR[2];
				break;
			case 3 :
				backgroundPath = subNodePath + "_" + BG_DOOR[3];
				break;
			default :
				Gdx.app.log("DungeonStage", "DoorSize 정보 오류");
				backgroundPath = null;
				break;
		}
		outerTable.remove();
		outerTable.setBackground(new TextureRegionDrawable(new TextureRegion(textureManager
				.getBackgroundTexture(backgroundPath))));
		dungeonManager.getDungeonInfo().setBackground(backgroundPath);
		tableStack.add(outerTable);
	}
	private String getBackgroundPathByType(DungeonManager dungeonManager) {
		if (dungeonManager.getDungeonInfo().getCurrentDirection().equals(Direction.BACKWARD)) {
			String subNodePath = positionManager.getCurrentSubNodePath();
			switch (dungeonManager.getDungeonInfo().getCurrentRoom().getRoomType()) {
				case GATE :
					return subNodePath + BG_DOOR_GATE;
				case DOWN_STAIR :
					return subNodePath + BG_DOOR_DOWN_STAIR;
				case UP_STAIR :
					return subNodePath + BG_DOOR_UP_STAIR;
				default :
					return subNodePath + BG_DOOR[0];
			}
		} else {
			return dungeonManager.getDungeonInfo().getCurrentDungeon().getSubNodePath() + "_" + BG_DOOR[0];
		}
	}

	private void showDoorButton(DungeonManager dungeonManager) {
		if (!currentRoomLabel.equals(dungeonManager.getDungeonInfo().getCurrentRoom().getRoomLabel())
				|| !currentDirection.equals(dungeonManager.getDungeonInfo().getCurrentDirection())) {
			int currentDoorSize = dungeonManager.getCurrentDoorSize();
			switch (currentDoorSize) {
				case 0 :
					removeDoorListener(INDEX_OF_LEFT);
					removeDoorListener(INDEX_OF_MID);
					removeDoorListener(INDEX_OF_RIGHT);
					break;
				case 1 :
					addDoorListener(INDEX_OF_MID, 0);
					removeDoorListener(INDEX_OF_LEFT);
					removeDoorListener(INDEX_OF_RIGHT);
					break;
				case 2 :
					addDoorListener(INDEX_OF_LEFT, 0);
					removeDoorListener(INDEX_OF_MID);
					addDoorListener(INDEX_OF_RIGHT, 1);
					break;
				case 3 :
					addDoorListener(INDEX_OF_LEFT, 0);
					addDoorListener(INDEX_OF_MID, 1);
					addDoorListener(INDEX_OF_RIGHT, 2);
					break;
				default :
					break;
			}
		}
	}

	private void addDoorListener(int doorIndex, int listenerIndex) {
		setDoorTable(doorIndex);
		DungeonDoorButtonListener doorButtonListener = listenerFactory.getDungeonDoorButtonListener();
		doorButtonListener.setIndex(listenerIndex);
		doorTable[doorIndex].addListener(doorButtonListener);
	}

	private void removeDoorListener(int index) {
		doorTable[index].remove();
	}

	private void setDoorTable(int index) {
		doorTable[index].clear();
		doorTable[index].add(goDoorButton[index]);
		doorTable[index].bottom().left();
		doorTable[index].addAction(Actions.moveTo(DOOR_POSITION[index][0], DOOR_POSITION[index][1]));
		addActor(doorTable[index]);
	}

	private void setDoorButton(DungeonManager dungeonManager) {
		for (int i = 0; i < TOTAL_DOOR_SIZE; i++) {
			goDoorButton[i] = new ImageButton(atlasUiAssets.getAtlasUiFile("dungeonui_button_go"));
			doorTable[i] = new Table();
			setDoorTable(i);
		}
	}

	private void setChangeDirectionButton(final DungeonManager dungeonManager) {
		ImageButton changeDirectionButton = new ImageButton(
				atlasUiAssets.getAtlasUiFile("dungeonui_button_change_direction"));
		Table changeDirectionbuttonTable = new Table();
		changeDirectionbuttonTable.bottom().left();
		changeDirectionbuttonTable.add(changeDirectionButton);
		changeDirectionbuttonTable.addAction(Actions.moveTo(808, 80));
		changeDirectionbuttonTable.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dungeonManager.changeDirection();
				timeManager.plusMinute(5);
			}
		});
		addActor(changeDirectionbuttonTable);
	}

	private void setOtherButtons(DungeonManager dungeonManager) {
		if (!currentRoomLabel.equals(dungeonManager.getDungeonInfo().getCurrentRoom().getRoomLabel())
				|| !currentDirection.equals(dungeonManager.getDungeonInfo().getCurrentDirection())) {
			setCurrentRoomLabel(dungeonManager.getDungeonInfo().getCurrentRoom().getRoomLabel());
			setCurrentDirection(dungeonManager.getDungeonInfo().getCurrentDirection());
			otherButtonTable.clear();
			switch (dungeonManager.getDungeonInfo().getCurrentRoom().getRoomType()) {
				case BOSS :
					break;
				case DOWN_STAIR :
					makeDownStairRoomButton();
					break;
				case ELITE :
					break;
				case GATE :
					makeGateRoomButton();
					break;
				case NORMAL :
					break;
				case OBJECT :
					// makeObjectRoomButton();
					break;
				case UP_STAIR :
					makeUpStairRoomButton();
					break;
				default :
					Gdx.app.log("DungeonStage", "RoomType정보 오류");
					break;
			}
		}
	}

	public Direction getCurrentDirection() {
		return currentDirection;
	}

	public void setCurrentDirection(Direction currentDirection) {
		this.currentDirection = currentDirection;
	}

	private void makeUpStairRoomButton() {
		otherButtonTable.clear();
		if (dungeonManager.getDungeonInfo().getCurrentDirection().equals(Direction.BACKWARD)) {
			ImageButton upButton = new ImageButton(atlasUiAssets.getAtlasUiFile("dungeonui_button_up"));
			otherButtonTable.left().bottom();
			otherButtonTable.padLeft(808).padBottom(412);
			otherButtonTable.add(upButton);
			DungeonStairButtonListener dungeonStairButtonListener = listenerFactory.getDungeonStairButtonListener();
			dungeonStairButtonListener.setStairType(DungeonEnum.Type.UP_STAIR);
			dungeonStairButtonListener.setLink(dungeonManager.getDungeonInfo().getCurrentRoom().getLink());
			upButton.addListener(dungeonStairButtonListener);
			addActor(otherButtonTable);
			addActor(otherButtonTable);
		}
	}

	private void makeGateRoomButton() {
		if (dungeonManager.getDungeonInfo().getCurrentDirection().equals(Direction.BACKWARD)) {
			ImageButton exitButton = new ImageButton(atlasUiAssets.getAtlasUiFile("dungeonui_button_exit"));
			otherButtonTable.left().bottom();
			otherButtonTable.padLeft(808).padBottom(521);
			otherButtonTable.add(exitButton);
			LeaveDungeonButtonListener leaveDungeonButtonListener = listenerFactory.getLeaveDungeonButtonListener();
			otherButtonTable.addListener(leaveDungeonButtonListener);
			addActor(otherButtonTable);
		}
	}

	private void makeDownStairRoomButton() {
		otherButtonTable.clear();
		if (dungeonManager.getDungeonInfo().getCurrentDirection().equals(Direction.BACKWARD)) {
			ImageButton downButton = new ImageButton(atlasUiAssets.getAtlasUiFile("dungeonui_button_down"));
			otherButtonTable.left().bottom();
			otherButtonTable.padLeft(808).padBottom(412);
			otherButtonTable.add(downButton);
			DungeonStairButtonListener dungeonStairButtonListener = listenerFactory.getDungeonStairButtonListener();
			dungeonStairButtonListener.setStairType(DungeonEnum.Type.DOWN_STAIR);
			dungeonStairButtonListener.setLink(dungeonManager.getDungeonInfo().getCurrentRoom().getLink());
			downButton.addListener(dungeonStairButtonListener);
			addActor(otherButtonTable);
		}
	}

	public String getCurrentRoomLabel() {
		return currentRoomLabel;
	}

	public void setCurrentRoomLabel(String currentRoomLabel) {
		this.currentRoomLabel = currentRoomLabel;
	}
}
