package com.mygdx.model;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.MonsterEnum;

public class GridHitbox extends Table {
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap
			.get("BattleStage");

	private Tile[][] tiles;
	private float tableWidth;
	private float tableHeight;
	private int rows;
	private int columns;

	private boolean gridShow;

	public GridHitbox(MonsterEnum.SizeType sizeType) {
		switch (sizeType) {
		case SMALL:
			// TODO 추후 구현
			break;
		case MEDIUM:
			tableWidth = uiConstantsMap.get("gridTableWidthMedium");
			tableHeight = uiConstantsMap.get("gridTableHeightMedium");
			rows = 5;
			columns = 5;
			tiles = new Tile[rows][columns];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					tiles[i][j] = new Tile();
				}
			}
			break;
		case LARGE:
			// TODO 추후 구현
			break;
		}

		makeGridTable(sizeType);
	}

	public void makeGridTable(MonsterEnum.SizeType sizeType) {
		align(Align.top);
		padTop(uiConstantsMap.get("gridPadTop"));
		add(getGridImage(sizeType)) // gridTable에 gridImage 넣는다.
				// .padTop(topPadValue) // 상단바에 겹치지 않게 위쪽 Padding(1/8)
				.width(tableWidth) // 최대 가로 크기
				.height(tableHeight); // 최대 세로 크기
		setVisible(false); // 초기에는 보이지 않게 한다.
	}

	public void showGrid() {
		gridShow = true;
		setVisible(true);
	}

	public void hideGrid() {
		gridShow = false;
		setVisible(false);
	}

	private Tile findTile(int screenX, int screenY) {
		boolean found = false;
		int row = 0, column = 0;
		for (int i = 0; i < rows && !found; i++) {
			for (int j = 0; j < columns && !found; j++) {
				if (isInsideTile(i, j, screenX, screenY)) {
					row = j;
					column = i;
					found = true;
				}
			}
		}
		if (found) {
			return tiles[row][column];
		} else {
			return null;
		}
	}

	public void showTileWhereClicked(int screenX, int screenY) {
		Tile tile = findTile(screenX, screenY);
		if (tile != null) {
			tile.setVisible(true);
		}
	}

	public void showTileWhereMoved(int screenX, int screenY) {
		Tile tile = findTile(screenX, screenY);
		if (tile != null) {
			tile.setVisible(true);
		}
	}

	public void hideAllTiles() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tiles[i][j].setVisible(false);
			}
		}
	}

	private boolean isInside(float centerX, float centerY, float width,
			float height, int x, int y) {
		int revertedY = (int) (StaticAssets.windowHeight - y);

		if (x > (centerX - width / 2) && x < (centerX + width / 2)
				&& revertedY < (centerY + height / 2)
				&& revertedY > (centerY - height / 2)) {
			return true;
		}

		return false;
	}

	public boolean isInsideTile(int i, int j, int x, int y) {
		float centerX = tiles[i][j].getCenterX();
		float centerY = tiles[i][j].getCenterY();
		float width = tiles[i][j].getWidth();
		float height = tiles[i][j].getHeight();

		return isInside(centerX, centerY, width, height, x, y);
	}

	// private boolean isInsideHitbox(int x, int y) {
	// float centerX = gridTable.getCenterX();
	// float centerY = gridTable.getCenterY();
	// float width = gridTableWidth;
	// float height = gridTableHeight;
	//
	// return isInside(centerX, centerY, width, height, x, y);
	// }

	public float getTableWidth() {
		return tableWidth;
	}

	public void setTableWidth(float tableWidth) {
		this.tableWidth = tableWidth;
	}

	public float getTableHeight() {
		return tableHeight;
	}

	public void setTableHeight(float tableHeight) {
		this.tableHeight = tableHeight;
	}

	private Image getGridImage(MonsterEnum.SizeType sizeType) {
		// FIXME medium대신 monster.getType() 사용해야 함.
		return new Image(
				StaticAssets.battleUiTextureMap.get("grid_" + sizeType));
	}

	public boolean isGridShow() {
		return gridShow;
	}

	public class Tile {
		Image image = new Image(StaticAssets.battleUiTextureMap.get("tile"));

		public void setVisible(boolean val) {
			image.setVisible(val);
		}

		public float getCenterX() {
			return image.getCenterX();
		}

		public float getCenterY() {
			return image.getCenterY();
		}

		public float getWidth() {
			return image.getWidth();
		}

		public float getHeight() {
			return image.getHeight();
		}

	}

}
