package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.assets.StaticAssets;
import com.mygdx.enums.TextureEnum;

/**
 * 각각의 유닛에 Texture를 할당해서 쓸 수 있도록 하는 로직 StaticAssets을 다루기 때문에 싱글턴 클래스가 아니다. 위키의
 * 그래픽_작업물_명명규칙을 참고하자 http://cien.woobi.co.kr/dokuwiki/doku.php?id=%EC%A0%9C%EC%
 * 9E%91%ED%99%9C%EB%8F%99:%EA%B7%B8%EB%9E%98%ED%94%BD_%EC%9E%91%EC%97%85%EB%AC%BC_%EB%AA%85%EB%AA%85%EA
 * % B 7 % 9 C % E C % B 9 % 9 9
 * 
 * @author Velmont
 * 
 */
public class TextureManager {
	public TextureManager() {
		Gdx.app.debug("TextureManager", "Constructor() call (실행되어서는 안됨)");
	}

	public static Texture getStatusTexture(String facePath) {
		return StaticAssets.characterTextureMap.get(TextureEnum.STATUS + "_"
				+ facePath);
	}

	public static Texture getCharacterBodyTexture(String facePath) {
		return StaticAssets.characterTextureMap.get(TextureEnum.NPC + "_"
				+ facePath);
	}

	public static Texture getCharacterFaceTexture(String facePath) {
		return StaticAssets.characterTextureMap.get(TextureEnum.FACE + "_"
				+ facePath);
	}

	public static Texture getMonsterBattleTexture(String facePath) {
		return StaticAssets.monsterTextureMap.get(TextureEnum.MONSTER + "_"
				+ facePath);
	}

	public static Texture getCharacterBattleTurnBigTexture(String facePath) {
		return StaticAssets.characterTextureMap.get(TextureEnum.BATTLE_TURN
				+ "_" + facePath + "_" + "big");
	}

	public static Texture getMonsterBattleTurnBigTexture(String facePath) {
		return StaticAssets.monsterTextureMap.get(TextureEnum.BATTLE_TURN + "_"
				+ facePath + "_" + "big");
	}

	public static Texture getCharacterBattleTurnSmallTexture(String facePath) {
		return StaticAssets.characterTextureMap.get(TextureEnum.BATTLE_TURN
				+ "_" + facePath + "_" + "small");
	}

	public static Texture getMonsterBattleTurnSmallTexture(String facePath) {
		return StaticAssets.monsterTextureMap.get(TextureEnum.BATTLE_TURN + "_"
				+ facePath + "_" + "small");
	}

	public static Texture getMonsterFaceTexture(String facePath) {
		return StaticAssets.monsterTextureMap.get(TextureEnum.FACE + "_"
				+ facePath);
	}

	public static Texture getMonsterBodyTexture(String facePath) {
		System.out.println(TextureEnum.MONSTER + "_" + facePath);
		return StaticAssets.monsterTextureMap.get(TextureEnum.MONSTER + "_"
				+ facePath);
	}

	public static Texture getBackgroundTexture(String facePath,
			TextureEnum textureEnum) {
		return StaticAssets.backgroundTextureMap.get(TextureEnum.BACKGROUND
				+ "_" + facePath + "_" + textureEnum);
	}
}
