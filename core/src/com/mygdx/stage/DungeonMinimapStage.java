package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.enums.DungeonEnum.Direction;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.location.DungeonFloor;
import com.mygdx.model.location.DungeonRoom;

public class DungeonMinimapStage extends BaseOneLevelStage {
	@Autowired
	private DungeonManager dungeonManager;
	@Autowired
	private transient TextureManager textureManager;
	private Texture map;
	private TextureRegion[][] maptile;
	private Table minimapTable = new Table();
	private Table arrowTable = new Table();
	private Texture blacktile;
	private Image minimapBackgroundImage;
	private Image directionArrow;
	private String currentRoomLabel = new String();

	public Stage makeStage() {
		super.makeStage();
		setBackground();
		showMapTile(dungeonManager);
		showDirectionArrow(dungeonManager);
		return this;
	}

	public void act(float delta) {
		super.act(delta);
		tableStack.act(delta);
		showMapTile(dungeonManager);
		minimapTable.debug();
		showDirectionArrow(dungeonManager);
	}

	private void showDirectionArrow(DungeonManager dungeonManager) {
		arrowTable.clear();
		directionArrow = new Image(new Texture(Gdx.files.internal("texture/dungeon_minimap/minimap_arrow.png")));
		directionArrow.setPosition(1395 + 255, 585 + 155);
		directionArrow.setOrigin(directionArrow.getWidth() / 2, directionArrow.getHeight() / 2);
		rotateArrow(directionArrow, dungeonManager);
		arrowTable.add(directionArrow);
		arrowTable.top().right();
		arrowTable.padRight(250).padTop(300);
		tableStack.add(arrowTable);
	}

	private void rotateArrow(Image directionArrow, DungeonManager dungeonManager) {
		int rotationDegree;
		switch (dungeonManager.getDungeonInfo().getCurrentRoom().getForwardAngle()) {
		case TOP:
			rotationDegree = 90;
			break;
		case TOP_LEFT:
			rotationDegree = 135;
			break;
		case TOP_RIGHT:
			rotationDegree = 45;
			break;
		case RIGHT:
			rotationDegree = 0;
			break;
		case BOTTOM_RIGHT:
			rotationDegree = -45;
			break;
		case BOTTOM:
			rotationDegree = -90;
			break;
		case BOTTOM_LEFT:
			rotationDegree = -135;
			break;
		case LEFT:
			rotationDegree = 180;
			break;
		default:
			Gdx.app.log("DungeonManager", "ForwardAngle정보 오류");
			rotationDegree = 0;
			break;
		}
		if (dungeonManager.getDungeonInfo().getCurrentDirection().equals(Direction.BACKWARD)) {
			rotationDegree += 180;
		}
		directionArrow.setRotation(rotationDegree);
	}

	private void setBackground() {
		Table minimapBackgroundTable = new Table();
		minimapBackgroundImage = new Image(
				new Texture(Gdx.files.internal("texture/dungeon_minimap/minimap_background.png")));
		minimapBackgroundTable.add(minimapBackgroundImage);
		minimapBackgroundTable.top().right();
		minimapBackgroundTable.padRight(10).padTop(170);
		tableStack.add(minimapBackgroundTable);
	}

	private void showMapTile(DungeonManager dungeonManager) {
		blacktile = new Texture(Gdx.files.internal("texture/dungeon_minimap/black_tile.png"));
		map = textureManager.getMinimapTexture(dungeonManager.getDungeonInfo().getCurrentFloor().getFloorPath());

		maptile = TextureRegion.split(map,
				map.getWidth() / (dungeonManager.getDungeonInfo().getCurrentFloor().getMapWidth() + 2),
				map.getHeight() / (dungeonManager.getDungeonInfo().getCurrentFloor().getMapHeight()));
		DungeonRoom currentRoom = dungeonManager.getDungeonInfo().getCurrentRoom();
		DungeonFloor currentFloor = dungeonManager.getDungeonInfo().getCurrentFloor();
		if (!currentRoomLabel.equals(currentRoom.getRoomLabel())) {
			minimapTable.remove();
			minimapTable.clear();
			minimapTable.top();
			minimapTable.right();
			minimapTable.padRight(10).padTop(170);
			setCurrentRoomLabel(currentRoom.getRoomLabel());
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 5; j++) {
					int indexX = currentRoom.getRoomPosX() - 3 + j;
					int indexY = currentRoom.getRoomPosY() - 2 + i;
					if (indexX < 0 || indexY < 0) {
						minimapTable.add(new Image(blacktile));
					} else {
						if (indexX < (currentFloor.getMapWidth()) && indexY < currentFloor.getMapHeight()) {
							if (currentFloor.getMiniMap()[indexY][indexX] == true) {
								minimapTable.add(new Image(maptile[indexY][indexX]));
							} else {
								minimapTable.add(new Image(blacktile));
							}
						} else {
							minimapTable.add(new Image(blacktile));
						}
					}
				}
				minimapTable.row();
			}
		}
		tableStack.add(minimapTable);
	}

	public String getCurrentRoomLabel() {
		return currentRoomLabel;
	}

	public void setCurrentRoomLabel(String currentRoomLabel) {
		this.currentRoomLabel = currentRoomLabel;
	}
}
