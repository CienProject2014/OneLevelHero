package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.listener.TouchListenerWithLog;
import com.mygdx.manager.CameraManager.CameraPosition;
import com.mygdx.state.StaticAssets;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonStage extends OverlapStage {
	private CompositeItem arrowUp, arrowDown, arrowLeft, arrowRight;

	private final static int DUNGEONMAPFLAG_DISABLED = 0;
	private final static int DUNGEONMAPFLAG_DIR_N = 1 << 1;
	private final static int DUNGEONMAPFLAG_DIR_E = 1 << 2;
	private final static int DUNGEONMAPFLAG_DIR_S = 1 << 3;
	private final static int DUNGEONMAPFLAG_DIR_W = 1 << 4;

	private final static int DIR_N = 0 << 1 | 0 << 0;
	private final static int DIR_E = 0 << 1 | 1 << 0;
	private final static int DIR_S = 1 << 1 | 0 << 0;
	private final static int DIR_W = 1 << 1 | 1 << 0;

	private final static int DELTA = 1;

	private int[][] map_data;
	private boolean[][] map_visible;
	private int sizeX, sizeY, currentX, currentY, currentDir;

	private int miniMapX = 1 << 3, miniMapY = 1 << 3,
			miniMapTileWidth = 1 << 5, miniMapTileHeight = 1 << 5;

	private ShapeRenderer sr = new ShapeRenderer();

	@Override
	public void draw() {
		super.draw();

		sr.setProjectionMatrix(getCamera().combined);

		Gdx.gl.glScissor(miniMapX, miniMapY, miniMapTileWidth * sizeX,
				miniMapTileHeight * sizeY);
		Gdx.gl.glEnable(GL20.GL_SCISSOR_TEST);
		Gdx.gl.glClearColor(.7f, .7f, .7f, 1.f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		Gdx.gl.glClearColor(0, 0, 0, 0);

		for (int y = 0; y < sizeY; y++) {
			for (int x = 0; x < sizeX; x++) {
				sr.begin(ShapeType.Line);
				sr.setColor(Color.WHITE);
				if (map_visible[y][x]) {
					if (chkMapFlag(x, y, DUNGEONMAPFLAG_DIR_N) && y > 0) {
						sr.line((float) (miniMapX + miniMapTileWidth * (x + .5)),
								(float) (miniMapY + miniMapTileHeight
										* ((sizeY - 1 - y) + .5)),
								(float) (miniMapX + miniMapTileWidth * (x + .5)),
								miniMapY + miniMapTileHeight
										* ((sizeY - 1 - y) + 1));
					}
					if (chkMapFlag(x, y, DUNGEONMAPFLAG_DIR_E) && x < sizeX - 1) {
						sr.line((float) (miniMapX + miniMapTileWidth * (x + .5)),
								(float) (miniMapY + miniMapTileHeight
										* ((sizeY - 1 - y) + .5)), miniMapX
										+ miniMapTileWidth * (x + 1),
								(float) (miniMapY + miniMapTileHeight
										* ((sizeY - 1 - y) + .5)));
					}
					if (chkMapFlag(x, y, DUNGEONMAPFLAG_DIR_S) && y < sizeY - 1) {
						sr.line((float) (miniMapX + miniMapTileWidth * (x + .5)),
								(float) (miniMapY + miniMapTileHeight
										* ((sizeY - 1 - y) + .5)),
								(float) (miniMapX + miniMapTileWidth * (x + .5)),
								miniMapY + miniMapTileHeight * (sizeY - 1 - y));
					}
					if (chkMapFlag(x, y, DUNGEONMAPFLAG_DIR_W) && x > 0) {
						sr.line((float) (miniMapX + miniMapTileWidth * (x + .5)),
								(float) (miniMapY + miniMapTileHeight
										* ((sizeY - 1 - y) + .5)), miniMapX
										+ miniMapTileWidth * x,
								(float) (miniMapY + miniMapTileHeight
										* ((sizeY - 1 - y) + .5)));
					}
				}
				sr.end();
			}
		}
		Gdx.gl.glDisable(GL20.GL_SCISSOR_TEST);
	}

	public Stage makeStage() {
		initSceneLoader(StaticAssets.rm);

		makeScene();
		setButton();

		initMapData();

		return this;
	}

	private void makeScene() {
		// 우선은 blackwood_forest_dungeon_scene으로 통일하자
		sceneLoader.loadScene("blackwood_forest_dungeon_scene");
		cameraManager.setCameraSize(this, CameraPosition.BELOW_GAME_UI);
		addActor(sceneLoader.getRoot());
	}

	private class TouchListenerWithLogAndUpdate extends TouchListenerWithLog {
		public TouchListenerWithLogAndUpdate(String tag, String msg,
				Runnable func) {
			super(tag, msg, func);
		}

		@Override
		public void touchUp(InputEvent event, float x, float y, int pointer,
				int button) {
			super.touchUp(event, x, y, pointer, button);
			update();
		}
	}

	private void setButton() {
		arrowUp = sceneLoader.getRoot().getCompositeById("arrow_up");
		arrowDown = sceneLoader.getRoot().getCompositeById("arrow_down");
		arrowLeft = sceneLoader.getRoot().getCompositeById("arrow_left");
		arrowRight = sceneLoader.getRoot().getCompositeById("arrow_right");

		arrowUp.addListener(new TouchListenerWithLogAndUpdate("DungeonState",
				"앞으로 이동", new Runnable() {
					@Override
					public void run() {
						moveForward();
					}
				}));

		arrowDown.addListener(new TouchListenerWithLogAndUpdate("DungeonState",
				"뒤로 이동", new Runnable() {
					@Override
					public void run() {
						moveBackward();
					}
				}));

		arrowLeft.addListener(new TouchListenerWithLogAndUpdate("DungeonState",
				"왼쪽으로 이동", new Runnable() {
					@Override
					public void run() {
						moveLeft();
					}
				}));

		arrowRight.addListener(new TouchListenerWithLogAndUpdate(
				"DungeonState", "오른쪽으로 이동", new Runnable() {
					@Override
					public void run() {
						moveRight();
					}
				}));
	}

	private void initMapData() { // TODO, FIXME
		sizeX = sizeY = 3;
		currentX = currentY = currentDir = 0;

		map_data = new int[sizeY][sizeX];
		map_visible = new boolean[sizeY][sizeX];

		for (int y = 0; y < sizeY; y++)
			for (int x = 0; x < sizeX; x++) {
				map_data[y][x] = DUNGEONMAPFLAG_DIR_N | DUNGEONMAPFLAG_DIR_E
						| DUNGEONMAPFLAG_DIR_S | DUNGEONMAPFLAG_DIR_W;
				map_visible[y][x] = false;
			}

		update();
	}

	private boolean chkMapFlag(int x, int y, int flag) {
		return (map_data[y][x] & flag) != DUNGEONMAPFLAG_DISABLED;
	}

	// N : 00 E : 01 S : 10 W : 11

	private int chkDir(boolean chkAxisVertical, boolean chkDirNorth,
			boolean chkDirEast) {
		return chkAxisVertical ? (chkDirNorth ? DIR_N : DIR_S)
				: (chkDirEast ? DIR_E : DIR_W);
	}

	private void update() {
		map_visible[currentY][currentX] = true;

		boolean[] movable = new boolean[4];
		movable[DIR_N] = chkMapFlag(currentX, currentY, DUNGEONMAPFLAG_DIR_N)
				&& currentY > 0;
		movable[DIR_E] = chkMapFlag(currentX, currentY, DUNGEONMAPFLAG_DIR_E)
				&& currentX < sizeX - 1;
		movable[DIR_S] = chkMapFlag(currentX, currentY, DUNGEONMAPFLAG_DIR_S)
				&& currentY < sizeY - 1;
		movable[DIR_W] = chkMapFlag(currentX, currentY, DUNGEONMAPFLAG_DIR_W)
				&& currentX > 0;

		arrowUp.setVisible(movable[chkDir((currentDir & (1 << 0)) == 0,
				(currentDir & (1 << 1)) == 0, (currentDir & (1 << 1)) == 0)]);
		arrowDown.setVisible(movable[chkDir((currentDir & (1 << 0)) == 0,
				(currentDir & (1 << 1)) != 0, (currentDir & (1 << 1)) != 0)]);
		arrowLeft.setVisible(movable[chkDir((currentDir & (1 << 0)) != 0,
				(currentDir & (1 << 1)) == 0, (currentDir & (1 << 1)) != 0)]);
		arrowRight.setVisible(movable[chkDir((currentDir & (1 << 0)) != 0,
				(currentDir & (1 << 1)) != 0, (currentDir & (1 << 1)) == 0)]);

		arrowUp.setTouchable(arrowUp.isVisible() ? Touchable.enabled
				: Touchable.disabled);
		arrowDown.setTouchable(arrowDown.isVisible() ? Touchable.enabled
				: Touchable.disabled);
		arrowLeft.setTouchable(arrowLeft.isVisible() ? Touchable.enabled
				: Touchable.disabled);
		arrowRight.setTouchable(arrowRight.isVisible() ? Touchable.enabled
				: Touchable.disabled);
	}

	private void movebody(boolean chkAxisVertical, boolean chkDirNorth,
			boolean chkDirEast) {
		if (chkAxisVertical) {
			if (chkDirNorth) {
				currentDir = DIR_N;
				currentY -= DELTA;
			} else {
				currentDir = DIR_S;
				currentY += DELTA;
			}
		} else {
			if (chkDirEast) {
				currentDir = DIR_E;
				currentX += DELTA;
			} else {
				currentDir = DIR_W;
				currentX -= DELTA;
			}
		}
	}

	private void moveForward() {
		movebody(((currentDir & (1 << 0)) == 0),
				((currentDir & (1 << 1)) == 0), ((currentDir & (1 << 1)) == 0));
	}

	private void moveBackward() {
		movebody(((currentDir & (1 << 0)) == 0),
				((currentDir & (1 << 1)) != 0), ((currentDir & (1 << 1)) != 0));
	}

	private void moveLeft() {
		movebody(((currentDir & (1 << 0)) != 0),
				((currentDir & (1 << 1)) == 0), ((currentDir & (1 << 1)) != 0));
	}

	private void moveRight() {
		movebody(((currentDir & (1 << 0)) != 0),
				((currentDir & (1 << 1)) != 0), ((currentDir & (1 << 1)) == 0));
	}
}
