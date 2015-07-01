package com.mygdx.manager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.enums.TextureEnum;
import com.mygdx.model.FrameSheet;
import com.mygdx.state.StaticAssets;

public class AnimationManager {
	private SpriteBatch spriteBatch;
	private Queue<AnimationBit> animations;

	private float stateTime;

	public AnimationManager() {
		spriteBatch = new SpriteBatch();
		animations = new LinkedList<AnimationBit>();
	}

	public static FrameSheet getAnimationSheet(TextureEnum name) {
		return StaticAssets.animationSheetMap.get(name);
	}

	public void registerAnimation(TextureEnum name, int x, int y) {
		FrameSheet sheet = StaticAssets.animationSheetMap.get(name.toString());
		TextureRegion[][] tmp = TextureRegion.split(sheet.getTexture(), sheet
				.getTexture().getWidth() / sheet.getColumn(), sheet
				.getTexture().getHeight() / sheet.getRow());
		TextureRegion[] frames = new TextureRegion[sheet.getFrameNumber()];

		int index = 0;
		for (int i = 0; i < sheet.getRow(); i++) {
			for (int j = 0; j < sheet.getColumn(); j++) {
				if (index >= sheet.getFrameNumber())
					break;
				frames[index++] = tmp[i][j];
			}
		}
		
		animations.add(new AnimationBit(new Animation(0.025f, frames), x, y));
	}

	public void startPlay() {
		stateTime = 0f;
	}

	public void nextFrame(float delta) {
		stateTime += delta;

		spriteBatch.begin();
		for (Iterator<AnimationBit> iterator = animations.iterator(); iterator.hasNext();) {
			AnimationBit bit = (AnimationBit) iterator.next();
			if (bit.getAnimation().isAnimationFinished(stateTime)) {
				iterator.remove();
			} else {
				spriteBatch.draw(bit.getAnimation().getKeyFrame(stateTime), bit.getX(), bit.getY());
			}
		}
		spriteBatch.end();
	}

	public boolean isPlaying() {
		if (animations.isEmpty()) {
			startPlay();
			return false;
		} else {
			return true;
		}
	}
	
	public class AnimationBit {
		private Animation animation;
		private int x;
		private int y;
		
		public AnimationBit(Animation animation, int x, int y) {
			this.animation = animation;
			this.x = x;
			this.y = y;
		}
		
		public Animation getAnimation() {
			return animation;
		}
		public void setAnimation(Animation animation) {
			this.animation = animation;
		}
		public int getX() {
			return x;
		}
		public void setX(int x) {
			this.x = x;
		}
		public int getY() {
			return y;
		}
		public void setY(int y) {
			this.y = y;
		}
	}
}
