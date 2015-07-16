package com.mygdx.manager;

import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Queue;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.TextureEnum;
import com.mygdx.model.FrameSheet;

public class AnimationManager {
	private SpriteBatch spriteBatch;
	private Queue<AnimationBit> animations;

	private float stateTime;

	public AnimationManager() {
		spriteBatch = new SpriteBatch();
		animations = new ArrayDeque<AnimationBit>();
	}

	/**
	 * Animation 시퀀스에 재생하고자 하는 애니메이션을 등록
	 * 
	 * @param name
	 *            재생하고자 하는 애니메이션의 TextureEnum
	 * @param x
	 *            재생하고자 하는 x위치
	 * @param y
	 *            재생하고자 하는 y위치
	 */
	public void registerAnimation(TextureEnum name, int x, int y) {
		FrameSheet sheet = StaticAssets.animationSheetMap.get(name.toString());

		TextureRegion[][] splitted = splitSheet(sheet);

		TextureRegion[] frames = matrixToArray(splitted, sheet);

		animations.add(new AnimationBit(new Animation(0.025f, frames), x, y));
	}

	/**
	 * 하나의 sheet에 포함된 프레임들을 TextureRegion 타입의 이차원 배열로 분리한다.
	 * 
	 * @param sheet
	 *            분리하고자 하는 FrameSheet
	 * @return TextureRegion 이차원 배열
	 */
	private TextureRegion[][] splitSheet(FrameSheet sheet) {
		if (sheet.getTexture() == null)
			sheet.loadTexture();
		return TextureRegion.split(sheet.getTexture(), sheet.getTexture().getWidth() / sheet.getColumn(),
				sheet.getTexture().getHeight() / sheet.getRow());
	}

	/**
	 * TextureRegion 타입의 2d array를 1d array로 바꾼다.
	 * 
	 * @param matrix
	 *            TextureRegion 타입의 이차원 배열
	 * @param sheet
	 *            행, 열 크기가 담긴 데이터
	 * @return TextureRegion 타입의 1d array
	 */
	private TextureRegion[] matrixToArray(TextureRegion[][] matrix, FrameSheet sheet) {
		TextureRegion[] frames = new TextureRegion[sheet.getFrameNumber()];
		int index = 0;
		for (int i = 0; i < sheet.getRow(); i++) {
			for (int j = 0; j < sheet.getColumn(); j++) {
				if (index >= sheet.getFrameNumber()) {
					break;
				}
				frames[index++] = matrix[i][j];
			}
		}

		return frames;
	}

	/**
	 * 재생 시간을 초기화
	 */
	public void resetTime() {
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

	public boolean hasPlayable() {
		if (animations.isEmpty()) {
			resetTime();
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
