package com.mygdx.manager;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.enums.UnitTextureEnum;
import com.mygdx.state.Assets;

/**
 * 각각의 유닛에 Texture를 할당해서 쓸 수 있도록 하는 로직
 * 위키의 그래픽_작업물_명명규칙을 참고하자
 * http://cien.woobi.co.kr/dokuwiki/doku.php?id=%EC%A0%9C%EC%9E%91%ED%99%9C%EB%8F%99:%EA%B7%B8%EB%9E%98%ED%94%BD_%EC%9E%91%EC%97%85%EB%AC%BC_%EB%AA%85%EB%AA%85%EA%B7%9C%EC%B9%99
 * @author Velmont
 *
 */
@Component
public class TextureManager {

	public static Texture getCharacterBattleTexture(String name) {
		return Assets.characterTextureMap.get(name + "_"
				+ UnitTextureEnum.BATTLE_ALLY_NORMAL);
	}

	public static Texture getMonsterBattleTexture(String name) {
		System.out.println((name + "_" + UnitTextureEnum.BATTLE_ENEMY_NORMAL));
		return Assets.monsterTextureMap.get(name + "_"
				+ UnitTextureEnum.BATTLE_ENEMY_NORMAL);
	}

	public static Texture getStatusTexture(String name) {
		return Assets.characterTextureMap.get(name + "_"
				+ UnitTextureEnum.STATUS_NORMAL);
	}

	public static Texture getNpcTexture(String name) {
		return Assets.characterTextureMap.get(name + "_"
				+ UnitTextureEnum.NPC_NORMAL);
	}
}
