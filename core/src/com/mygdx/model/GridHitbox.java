package com.mygdx.model;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.MonsterEnum;

public class GridHitbox extends Table {
	private HashMap<String, Float> uiConstantsMap = StaticAssets.uiConstantsMap.get("BattleStage");

	private Image[][] tiles;
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
			tiles = new Image[rows][columns];
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					tiles[i][j] = new Image(StaticAssets.battleUiTextureMap.get("tile"));
				}
			}
			break;
		case LARGE:
			// TODO 추후 구현
			break;
		}

		this.add(makeGridTable(sizeType)).padTop(uiConstantsMap.get("gridPadTop"));
	}

	public Stack makeGridTable(MonsterEnum.SizeType sizeType) {
		Stack stack = new Stack();
		Table tileTable = new Table();

		tileTable.setWidth(tableWidth);
		tileTable.setHeight(tableHeight);

		align(Align.top);

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tileTable.add(tiles[i][j]).width(uiConstantsMap.get("gridTileWidth"))
						.height(uiConstantsMap.get("gridTileHeight"));
			}
			tileTable.row();
		}

		Table imageTable = new Table();

		// FIXME Medium을 sizeType에 맞춴
		imageTable.add(getGridImage(sizeType)).width(uiConstantsMap.get("gridTableWidthMedium"))
				.height(uiConstantsMap.get("gridTableHeightMedium"));
		stack.add(imageTable);
		stack.add(tileTable);

		setVisible(false);

		return stack;
	}

	public void showGrid() {
		gridShow = true;
		setVisible(true);
	}

	public void hideGrid() {
		gridShow = false;
		setVisible(false);
	}

	private Image findTile(float x, float y) {
		boolean found = false;
		int row = 0, column = 0;
		for (int i = 0; i < rows && !found; i++) {
			for (int j = 0; j < columns && !found; j++) {
				if (isInsideTile(i, j, x, y)) {
					row = i;
					column = j;
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

	public void showTileWhereClicked(float x, float y) {
		Image tile = findTile(x, y);
		if (tile != null) {
			tile.setVisible(true);
		}
	}

	public void showTileWhereMoved(float x, float y) {
		Image tile = findTile(x, y);
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

	private boolean isInside(float centerX, float centerY, float width, float height, float x, float y) {
		if (x > (centerX - width / 2) && x < (centerX + width / 2) && y < (centerY + height / 2)
				&& y > (centerY - height / 2)) {
			return true;
		}

		return false;
	}

	public boolean isInsideTile(int i, int j, float x, float y) {
		// FIXME 좌표계 변환을 하지 못해서 우선 상수로 처리.
		float centerX = tiles[i][j].getCenterX() + 640;
		float centerY = tiles[i][j].getCenterY() + 215;
		float width = tiles[i][j].getWidth();
		float height = tiles[i][j].getHeight();

		return isInside(centerX, centerY, width, height, x, y);
	}

	public boolean isInsideHitbox(float x, float y) {
		float centerX = this.getCenterX();
		float centerY = this.getCenterY();
		float width = uiConstantsMap.get("gridTableWidthSmall");
		float height = uiConstantsMap.get("gridTableHeightSmall");

		return isInside(centerX, centerY, width, height, x, y);
	}

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
		return new Image(StaticAssets.battleUiTextureMap.get("grid_" + sizeType));
	}

	public boolean isGridShow() {
		return gridShow;
	}

}
