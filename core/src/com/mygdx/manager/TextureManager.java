package com.mygdx.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.enums.TextureEnum;
import com.mygdx.state.StaticAssets;

/**
 * 각각의 유닛에 Texture를 할당해서 쓸 수 있도록 하는 로직
 * StaticAssets을 다루기 때문에 싱글턴 클래스가 아니다.
 * 위키의 그래픽_작업물_명명규칙을 참고하자
 * http://cien.woobi.co.kr/dokuwiki/doku.php?id=%EC%A0%9C%EC%9E%91%ED%99%9C%EB%8F%99:%EA%B7%B8%EB%9E%98%ED%94%BD_%EC%9E%91%EC%97%85%EB%AC%BC_%EB%AA%85%EB%AA%85%EA%B7%9C%EC%B9%99
 * @author Velmont
 *
 */
public class TextureManager {
	public TextureManager() {
		Gdx.app.debug("TextureManager", "Constructor() call (실행되어서는 안됨)");
	}

	public static Texture getCharacterBattleTexture(String name) {
		return StaticAssets.characterTextureMap.get(name + "_"
				+ TextureEnum.BATTLE_ALLY_NORMAL);
	}

	public static Texture getMonsterBattleTexture(String name) {
		System.out.println((name + "_" + TextureEnum.BATTLE_ENEMY_NORMAL));
		return StaticAssets.monsterTextureMap.get(name + "_"
				+ TextureEnum.BATTLE_ENEMY_NORMAL);
	}

	public static Texture getStatusTexture(String name) {
		return StaticAssets.characterTextureMap.get(name + "_"
				+ TextureEnum.STATUS_NORMAL);
	}

	public static Texture getStatusTexture(String name, TextureEnum textureEnum) {
		return StaticAssets.characterTextureMap.get(name + "_" + textureEnum);
	}

	public static Texture getNpcTexture(String name) {
		return StaticAssets.characterTextureMap.get(name + "_"
				+ TextureEnum.NPC_NORMAL);
	}

	public static Texture getNpcTexture(String name, TextureEnum textureEnum) {
		return StaticAssets.characterTextureMap.get(name + "_" + textureEnum);
	}

	public static Texture getBackgroundTexture(String name,
			TextureEnum textureEnum) {
		return StaticAssets.backgroundTextureMap.get(name + "_" + textureEnum);
	}
}
