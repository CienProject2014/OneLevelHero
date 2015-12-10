package com.mygdx.manager;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Queue;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.assets.StaticAssets;
import com.mygdx.assets.TextureAssets;
import com.mygdx.model.jsonModel.FrameSheet;
import com.mygdx.model.unit.Hero;
import com.mygdx.model.unit.Unit;

public class RealAnimationManager extends AnimationManager {
	@Autowired
	private TextureAssets textureAssets;
	@Autowired
	private PartyManager partyManager;
	@Autowired
	private BattleManager battleManager;
	private SpriteBatch spriteBatch;
	private ArrayList<OneAnimation> animations;

	public RealAnimationManager() {
		spriteBatch = new SpriteBatch();
		animations = new ArrayList<OneAnimation>();
	}

	public void registerAnimation(Hero attackedHero, String animationName) {
		int playerOrder = partyManager.getPlayerOrder(attackedHero);
		final int x;
		final int y;
		switch (playerOrder) {
			case 0 :
				x = (int) (StaticAssets.windowWidth * 0.1f);
				y = (int) (StaticAssets.windowHeight * 0.6f);
				break;
			case 1 :
				x = (int) (StaticAssets.windowWidth * 0.1f);
				y = (int) (StaticAssets.windowHeight * 0.5f);
				break;
			case 2 :
				x = (int) (StaticAssets.windowWidth * 0.1f);
				y = (int) (StaticAssets.windowHeight * 0.4f);
				break;
			default :
				x = (int) (StaticAssets.windowWidth * 0.1f);
				y = (int) (StaticAssets.windowHeight * 0.6f);
				break;
		}

		registerAnimation("attack_cutting", x, y);
	}

	public void registerAnimation(String animationName, int x, int y) {
		if (partyManager.getCurrentSelectedHero() == null) {
			// 팀원을 누르는 경우가 아닐때는 받아온 경로로 실행시킨다.
			x = (int) (StaticAssets.windowWidth / 2);
			y = (int) (StaticAssets.windowHeight / 2);
			/*
			 * int localx = (int) (StaticAssets.windowWidth * 0.5f) + (int)
			 * (StaticAssets.windowHeight * (-0.24f + 0.12f * x)); int localy =
			 * (int) (StaticAssets.windowHeight * 0.5f) + (int)
			 * (StaticAssets.windowHeight * (-0.24f + 0.12f * y));
			 */
		} else {
			int playerOrder = partyManager.getPlayerOrder(partyManager.getCurrentSelectedHero());
			switch (playerOrder) {
				case 0 :
					x = (int) (StaticAssets.windowWidth * 0.1f);
					y = (int) (StaticAssets.windowHeight * 0.6f);
					break;
				case 1 :
					x = (int) (StaticAssets.windowWidth * 0.1f);
					y = (int) (StaticAssets.windowHeight * 0.5f);
					break;
				case 2 :
					x = (int) (StaticAssets.windowWidth * 0.1f);
					y = (int) (StaticAssets.windowHeight * 0.4f);
					break;
				default :
					x = (int) (StaticAssets.windowWidth * 0.1f);
					y = (int) (StaticAssets.windowHeight * 0.6f);
					break;
			}
		}

		int height = (int) (StaticAssets.windowHeight * 0.5f);
		int width = (int) (StaticAssets.windowHeight * 0.5f);
		OneAnimation newAni = new OneAnimation();
		newAni.registerAnimation(animationName, x, y, width, height);
		animations.add(newAni);
	}

	public void nextFrame(float delta) {
		for (int i = 0; i < animations.size(); i++) {
			if (animations.get(i).isEndAnimation()) {
				animations.remove(i);
			} else {
				animations.get(i).nextFrame(delta);
			}
		}
	}

	public boolean isPlayable() {
		if (animations.isEmpty()) {
			// resetTime();
			return false;
		} else {
			return true;
		}
	}

	public boolean isEmptyAnimation() {
		return animations.isEmpty();
	}

	public class OneAnimation {
		private float stateTime;

		private Queue<OneImageBit> images;

		public OneAnimation() {
			images = new ArrayDeque<OneImageBit>();
			stateTime = 0;
		}

		public void registerAnimation(String name, int x, int y, int width, int height) {
			textureAssets.loadTexture();
			FrameSheet sheet = textureAssets.getAnimationSheet(name);

			TextureRegion[][] splitted = splitSheet(sheet);

			TextureRegion[] frames = matrixToArray(splitted, sheet);

			images.add(new OneImageBit(new Animation(0.025f, frames), x, y, width, height));
		}

		/**
		 * nextFrame 다음 프레임으로 넘김. 지정된 점의 중점
		 * 
		 * @param delta
		 */
		public void nextFrame(float delta) {
			stateTime += delta;

			spriteBatch.begin();
			Iterator<OneImageBit> iterator = images.iterator();
			while (iterator.hasNext()) {

				OneImageBit bit = (OneImageBit) iterator.next();

				if (bit.getAnimation().isAnimationFinished(stateTime)) {
					iterator.remove();

				} else {
					spriteBatch.draw(bit.getAnimation().getKeyFrame(stateTime), bit.getX() - bit.getWidth() / 2,
							bit.getY() - bit.getHeight() / 2, bit.getWidth(), bit.getHeight());
				}
			}
			spriteBatch.end();
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
			return TextureRegion.split(sheet.getTexture(), sheet.getTexture().getWidth() / sheet.getColumn(), sheet
					.getTexture().getHeight() / sheet.getRow());
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

		public boolean isEndAnimation() {
			return images.isEmpty();
		}

	}

	public class OneImageBit {
		private Animation animation;
		private int x;
		private int y;
		private int width;
		private int height;

		public OneImageBit(Animation animation, int x, int y) {
			this.animation = animation;
			this.x = x;
			this.y = y;
		}

		public OneImageBit(Animation animation, int x, int y, int width, int height) {
			this.animation = animation;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
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

		public void setWidth(int width) {
			this.width = width;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public int getWidth() {
			return width;
		}

		public int getHeight() {
			return height;
		}
	}
	/*
	 * public SpriteBatch getSpriteBatch() { return spriteBatch; }
	 * 
	 * public void setSpriteBatch(SpriteBatch spriteBatch) { this.spriteBatch =
	 * spriteBatch; }
	 * 
	 * public Queue<OneImageBit> getAnimations() { return animations; }
	 * 
	 * public void setAnimations(Queue<OneImageBit> animations) {
	 * this.animations = animations; }
	 */

	@Override
	public void registerAnimation(Unit attackUnit, Unit defendUnit, String animationName) {
		registerAnimation(animationName, 0, 0);
	}
}
