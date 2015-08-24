package com.mygdx.ui;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.mygdx.assets.ConstantsAssets;
import com.mygdx.enums.MonsterEnum;
import com.mygdx.manager.TextureManager;

public class GridHitbox extends Table {
	private TextureManager textureManager;
	private final String TAG = "GridHitbox";

	private ConstantsAssets constantsAssets;
	private HashMap<String, Float> uiConstantsMap;

	private final int TILE_WIDTH = 128;

	private Image[][] tiles;
	private float tableWidth;
	private float tableHeight;
	private int rows;
	private int columns;

	private int showTileCount;
	private int limitNum;

	private int[] hitboxCenter;
	private int[][] hitboxShape;
	private int[][] previousHitArea;

	private float startX, startY;

	private boolean gridShow;

	public TextureManager getTextureManager() {
		return textureManager;
	}

	public void setTextureManager(TextureManager textureManager) {
		this.textureManager = textureManager;
	}

	public ConstantsAssets getConstantsAssets() {
		return constantsAssets;
	}

	public void setConstantsAssets(ConstantsAssets constantsAssets) {
		this.constantsAssets = constantsAssets;
	}

	public void setSizeType(MonsterEnum.SizeType sizeType) {

		switch (sizeType) {
		case SMALL:
			// TODO 추후 구현
			break;
		case MEDIUM:
			setMediumSizeType();
			break;
		case LARGE:
			// TODO 추후 구현
			break;
		}
		Stack stk = makeGridTable(sizeType);
		this.add(stk).padTop(uiConstantsMap.get("gridPadTop"));
	}

	private void setMediumSizeType() {
		tableWidth = uiConstantsMap.get("gridTableWidthMedium");
		tableHeight = uiConstantsMap.get("gridTableHeightMedium");
		rows = 5;
		columns = 5;
		tiles = new Image[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tiles[i][j] = new Image(textureManager.getEtcTexture("tile"));
			}
		}
		previousHitArea = new int[rows][columns];
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
		hideAllTiles();

		Table imageTable = new Table();
		// FIXME Medium을 sizeType에 맞춴
		imageTable.add(getGridImage(sizeType)).width(uiConstantsMap.get("gridTableWidthMedium"))
				.height(uiConstantsMap.get("gridTableHeightMedium"));
		stack.add(imageTable);
		stack.add(tileTable);
		setVisible(false);

		return stack;
	}

	public void setStartPosition(float x, float y) {
		startX = x;
		startY = y;
	}

	public void showGrid() {
		gridShow = true;
		setVisible(true);
	}

	public void hideGrid() {
		gridShow = false;
		setVisible(false);
	}

	public void showTileWhereMoved(float x, float y) {
		hideAllTiles();

		float dx = x - startX;
		float dy = y - startY;

		if (dx == 0 || dy == 0) {
			return;
		}

		if (Math.abs(dx) > Math.abs(dy)) {
			int deltaX = (int) (TILE_WIDTH * (dx / Math.abs(dx)));
			int deltaY = (int) (TILE_WIDTH * (dy / Math.abs(dx)));
			if (dx > 0) {
				for (int i = 0, j = 0; i < dx; i += deltaX, j += deltaY) {
					findAndShow(startX + i, startY + j);
				}
			} else {
				for (int i = 0, j = 0; i > dx; i += deltaX, j += deltaY) {
					findAndShow(startX + i, startY + j);
				}
			}

		} else {
			int deltaX = (int) (TILE_WIDTH * (dx / Math.abs(dy)));
			int deltaY = (int) (TILE_WIDTH * (dy / Math.abs(dy)));
			if (dy > 0) {
				for (int i = 0, j = 0; j < dy; i += deltaX, j += deltaY) {
					findAndShow(startX + i, startY + j);
				}
			} else {
				for (int i = 0, j = 0; j > dy; i += deltaX, j += deltaY) {
					findAndShow(startX + i, startY + j);
				}
			}
		}
	}

	public void showFixedTilesAt(float x, float y) {
		hideAllTiles();

		int[] rc = findTileIndex(x, y);
		if (rc != null) {

			for (int i = -hitboxCenter[0]; i <= hitboxCenter[0]; i++) {
				int row = rc[0] + i;
				if (row < 0 || row >= rows) {
					continue;
				}
				for (int j = -hitboxCenter[1]; j <= hitboxCenter[1]; j++) {
					int col = rc[1] + j;
					if (col < 0 || col >= columns) {
						continue;
					}

					if (hitboxShape[i + hitboxCenter[0]][j + hitboxCenter[1]] == 1) {
						tiles[row][col].setVisible(true);
						previousHitArea[row][col] = 1;
					}
				}
			}
		}
	}

	private boolean findAndShow(float x, float y) {
		int[] rc = findTileIndex(x, y);

		if (rc != null && showTileCount < limitNum) {
			int row = rc[0];
			int col = rc[1];

			showTileCount++;
			tiles[row][col].setVisible(true);
			previousHitArea[row][col] = 1;
			return true;
		}

		return false;
	}

	private int[] findTileIndex(float x, float y) {
		int[] index = new int[2];

		boolean found = false;
		for (int i = 0; i < rows && !found; i++) {
			for (int j = 0; j < columns && !found; j++) {
				if (isInsideTile(i, j, x, y)) {
					index[0] = i;
					index[1] = j;
					found = true;
				}
			}
		}

		if (found) {
			return index;
		} else {
			return null;
		}

	}

	public void hideAllTiles() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				tiles[i][j].setVisible(false);
				previousHitArea[i][j] = 0;
			}
		}
		showTileCount = 0;
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
		return new Image(textureManager.getEtcTexture("grid_" + sizeType));
	}

	public boolean isGridShow() {
		return gridShow;
	}

	public int getLimitNum() {
		return limitNum;
	}

	public void setLimitNum(int limitNum) {
		Gdx.app.log("GridHitbox", "set limit: " + limitNum);
		this.limitNum = limitNum;
	}

	public int[] getHitboxCenter() {
		return hitboxCenter;
	}

	public void setHitboxCenter(int[] hitboxCenter) {
		this.hitboxCenter = hitboxCenter;
	}

	public int[][] getHitboxShape() {
		return hitboxShape;
	}

	public void setHitboxShape(int[][] hitboxShape) {
		this.hitboxShape = hitboxShape;
	}

	public int[][] getPreviousHitArea() {
		return previousHitArea;
	}

	public void setPreviousHitArea(int[][] previousHitArea) {
		this.previousHitArea = previousHitArea;
	}

	public void setUiConstantsMap(ConstantsAssets constantsAssets) {
		uiConstantsMap = constantsAssets.getUiConstants("BattleStage");
	}
}
