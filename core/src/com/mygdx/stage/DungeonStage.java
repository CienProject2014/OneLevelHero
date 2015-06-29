package com.mygdx.stage;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.manager.CameraManager.CameraPosition;
import com.mygdx.state.StaticAssets;
import com.uwsoft.editor.renderer.actor.CompositeItem;

/**
 * @author Velmont
 *
 */
public class DungeonStage extends OverlapStage {
	private CompositeItem arrowUp, arrowDown, arrowLeft, arrowRight;

	private final static int dungeonMapFlag_Disabled = 0;
	private final static int dungeonMapFlag_Dir_N = 1 << 1;
	private final static int dungeonMapFlag_Dir_E = 1 << 2;
	private final static int dungeonMapFlag_Dir_S = 1 << 3;
	private final static int dungeonMapFlag_Dir_W = 1 << 4;

	private final static int Dir_N = 0 << 1 | 0 << 0;
	private final static int Dir_E = 0 << 1 | 1 << 0;
	private final static int Dir_S = 1 << 1 | 0 << 0;
	private final static int Dir_W = 1 << 1 | 1 << 0;

	private int[][] map_data;
	private boolean[][] map_visible;
	private int sizeX, sizeY, currentX, currentY, currentDir;

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

	private void setButton() {
		arrowUp = sceneLoader.getRoot().getCompositeById("arrow_up");
		arrowDown = sceneLoader.getRoot().getCompositeById("arrow_down");
		arrowLeft = sceneLoader.getRoot().getCompositeById("arrow_left");
		arrowRight = sceneLoader.getRoot().getCompositeById("arrow_right");

		arrowUp.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.debug("DungeonStage", "앞으로 이동");
				moveForward();
				update();
			}
		});

		arrowDown.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.debug("DungeonStage", "뒤로 이동");
				moveBackward();
				update();
			}
		});

		arrowLeft.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.debug("DungeonStage", "왼쪽으로 이동");
				moveLeft();
				update();
			}
		});

		arrowRight.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y,
					int pointer, int button) {
				return true;
			}

			@Override
			public void touchUp(InputEvent event, float x, float y,
					int pointer, int button) {
				Gdx.app.debug("DungeonStage", "오른쪽으로 이동");
				moveRight();
				update();
			}
		});
	}

	private void initMapData() { // TODO, FIXME
		sizeX = sizeY = 3;
		currentX = currentY = currentDir = 0;

		map_data = new int[sizeY][sizeX];
		map_visible = new boolean[sizeY][sizeX];

		for (int y = 0; y < sizeY; y++)
			for (int x = 0; x < sizeX; x++) {
				map_data[y][x] = dungeonMapFlag_Dir_N | dungeonMapFlag_Dir_E
						| dungeonMapFlag_Dir_S | dungeonMapFlag_Dir_W;
				map_visible[y][x] = false;
			}

		update();
	}

	private boolean chkMapFlag(int x, int y, int flag) {
		return (map_data[y][x] & flag) != dungeonMapFlag_Disabled;
	}

	// N : 00 E : 01 S : 10 W : 11

	private void update() {
		map_visible[currentY][currentX] = true;

		boolean N = currentY > 0
				&& chkMapFlag(currentX, currentY, dungeonMapFlag_Dir_N), E = currentX < sizeX - 1
				&& chkMapFlag(currentX, currentY, dungeonMapFlag_Dir_E), S = currentY < sizeY - 1
				&& chkMapFlag(currentX, currentY, dungeonMapFlag_Dir_S), W = currentX > 0
				&& chkMapFlag(currentX, currentY, dungeonMapFlag_Dir_W);

		arrowUp.setVisible((currentDir & (1 << 0)) == 0 ? ((currentDir & (1 << 1)) == 0 ? N
				: S)
				: (currentDir & (1 << 1)) == 0 ? E : W);
		arrowDown
				.setVisible((currentDir & (1 << 0)) == 0 ? ((currentDir & (1 << 1)) != 0 ? N
						: S)
						: (currentDir & (1 << 1)) != 0 ? E : W);
		arrowLeft
				.setVisible((currentDir & (1 << 0)) != 0 ? ((currentDir & (1 << 1)) == 0 ? N
						: S)
						: (currentDir & (1 << 1)) != 0 ? E : W);
		arrowRight
				.setVisible((currentDir & (1 << 0)) != 0 ? ((currentDir & (1 << 1)) != 0 ? N
						: S)
						: (currentDir & (1 << 1)) == 0 ? E : W);

		arrowUp.setTouchable(arrowUp.isVisible() ? Touchable.enabled
				: Touchable.disabled);
		arrowDown.setTouchable(arrowDown.isVisible() ? Touchable.enabled
				: Touchable.disabled);
		arrowLeft.setTouchable(arrowLeft.isVisible() ? Touchable.enabled
				: Touchable.disabled);
		arrowRight.setTouchable(arrowRight.isVisible() ? Touchable.enabled
				: Touchable.disabled);
	}

	private void tmpmove(boolean flg0, boolean flg1, boolean flg2) {
		if (flg0) {
			if (flg1) {
				currentDir = Dir_N;
				currentY -= 1;
			} else {
				currentDir = Dir_S;
				currentY += 1;
			}
		} else {
			if (flg2) {
				currentDir = Dir_E;
				currentX += 1;
			} else {
				currentDir = Dir_W;
				currentX -= 1;
			}
		}
	}

	private void moveForward() {
		tmpmove(((currentDir & (1 << 0)) == 0), ((currentDir & (1 << 1)) == 0),
				((currentDir & (1 << 1)) == 0));
	}

	private void moveBackward() {
		tmpmove(((currentDir & (1 << 0)) == 0), ((currentDir & (1 << 1)) != 0),
				((currentDir & (1 << 1)) != 0));
	}

	private void moveLeft() {
		tmpmove(((currentDir & (1 << 0)) != 0), ((currentDir & (1 << 1)) == 0),
				((currentDir & (1 << 1)) != 0));
	}

	private void moveRight() {
		tmpmove(((currentDir & (1 << 0)) != 0), ((currentDir & (1 << 1)) != 0),
				((currentDir & (1 << 1)) == 0));
	}
}
