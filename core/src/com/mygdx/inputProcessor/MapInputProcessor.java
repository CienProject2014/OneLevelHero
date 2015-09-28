package com.mygdx.inputProcessor;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.assets.StaticAssets;

public class MapInputProcessor implements InputProcessor {
	private int x_left_limit = (int) (StaticAssets.windowWidth / 2);
	private int x_right_limit = (int) (3000 - (StaticAssets.windowWidth / 2));
	private int y_bottom_limit = (int) (StaticAssets.windowHeight / 2);
	private int y_top_limit = (int) (1688 - (StaticAssets.windowHeight / 2));
	private Stage stage;
	private Vector3 last_touch_down = new Vector3();

	public MapInputProcessor(Stage stage) {
		this.stage = stage;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		moveCamera(x, y);
		return false;
	}

	private void moveCamera(int touch_x, int touch_y) {
		Vector3 new_position = getNewCameraPosition(touch_x, touch_y);

		if (!cameraOutOfLimit(new_position)) {
			stage.getCamera().translate(
					new_position.sub(stage.getCamera().position));
		}
		last_touch_down.set(touch_x, touch_y, 0);
	}

	private Vector3 getNewCameraPosition(int x, int y) {
		Vector3 new_position = last_touch_down;
		new_position.sub(x, y, 0);
		new_position.y = -new_position.y;
		new_position.add(stage.getCamera().position);

		return new_position;
	}

	private boolean cameraOutOfLimit(Vector3 position) {
		if (position.x < x_left_limit || position.x > x_right_limit)
			return true;
		else if (position.y < y_bottom_limit || position.y > y_top_limit)
			return true;
		else
			return false;
	}

	@Override
	public boolean keyDown(int keycode) {
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		last_touch_down.set(screenX, screenY, 0);
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}
