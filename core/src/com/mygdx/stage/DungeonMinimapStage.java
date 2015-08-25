package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.manager.DungeonManager;
import com.mygdx.manager.TextureManager;
import com.mygdx.model.surroundings.DungeonNode;

public class DungeonMinimapStage extends BaseOneLevelStage {

	@Autowired
	DungeonManager dungeonManager;
	private Texture map;
	private TextureRegion[][] maptile;
	private Table minimaptable;
	private Texture blacktile;

	private Image[][] tiles;
	private float tableWidth;
	private float tableHeight;
	private int rows;
	private int columns;

	private boolean[][] isOn;

	@Autowired
	TextureManager textureManager;

	public void loadTexture() {

	}

	@Override
	public void act(float delta) {
		super.act(delta);

		refreshMinimap();
	}

	public void refreshMinimap() {

		minimaptable.clear();

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());
		dungeonManager.turnIsOn(currentNode.getNodePosY(), currentNode.getNodePosX());

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				// outofbound
				int indexX = currentNode.getNodePosX() - 2 + j;
				int indexY = currentNode.getNodePosY() - 1 + i;

				if (indexX < 0 || indexY < 0) {
					minimaptable.add(new Image(blacktile));
				} else {
					if (dungeonManager.checkIsOn(indexY, indexX))
						minimaptable.add(new Image(maptile[indexY][indexX]));
					else
						minimaptable.add(new Image(blacktile));
				}
			}
			minimaptable.row();
		}
	}

	public Stage makeStage() {

		super.makeStage();

		blacktile = new Texture(Gdx.files.internal("texture/dungeon_minimap/black_tile.png"));

		map = new Texture(Gdx.files.internal("texture/dungeon_minimap/devil_castle_minimap.png"));

		maptile = TextureRegion.split(map, map.getWidth() / dungeonManager.getMapInfo().getMapWidth(),
				map.getHeight() / dungeonManager.getMapInfo().getMapHeight());

		DungeonNode currentNode = dungeonManager.getMapInfo().nodes.get(dungeonManager.getCurrentPos());
		dungeonManager.turnIsOn(currentNode.getNodePosY(), currentNode.getNodePosX());

		// setisOn();

		float tileHeight = maptile[0][0].getRegionHeight();
		float tileWidth = maptile[0][0].getRegionWidth();

		// blacktile.setSize(tileHeight, tileWidth);
		// blacktile.set

		minimaptable = new Table();

		minimaptable.top();
		minimaptable.right();

		minimaptable.addAction(Actions.moveTo(-50, -150));
		// 세로 가로
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 5; j++) {
				// outofbound
				int indexX = currentNode.getNodePosX() - 2 + j;
				int indexY = currentNode.getNodePosY() - 1 + i;

				if (indexX < 0 || indexY < 0) {
					minimaptable.add(new Image(blacktile));
				} else {
					if (dungeonManager.checkIsOn(indexY, indexX))
						minimaptable.add(new Image(maptile[indexY][indexX]));
					else
						minimaptable.add(new Image(blacktile));
				}
			}
			minimaptable.row();
		}
		tableStack.add(minimaptable);
		// this.addActor(new Image(maptile[0][0]));

		return this;
	}

}
