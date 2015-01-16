package com.mygdx.manager;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.enums.UnitTextureEnum;
import com.mygdx.model.Hero;
import com.mygdx.model.NPC;
import com.mygdx.model.Unit;
import com.mygdx.state.Assets;

/**
 * 각각의 유닛에 Texture를 할당해서 쓸 수 있도록 하는 로직
 * 위키의 그래픽_작업물_명명규칙을 참고하자
 * http://cien.woobi.co.kr/dokuwiki/doku.php?id=%EC%A0%9C%EC%9E%91%ED%99%9C%EB%8F%99:%EA%B7%B8%EB%9E%98%ED%94%BD_%EC%9E%91%EC%97%85%EB%AC%BC_%EB%AA%85%EB%AA%85%EA%B7%9C%EC%B9%99
 * @author Velmont
 *
 */
public class UnitTextureManager {
	public static void setBattleTexture(Unit unit) {
		if (unit instanceof NPC || unit instanceof Hero) {
			//아군 각성시의 분기는 추후 여기에 설정해 둘것
			Texture battleTexture = Assets.characterTextureMap.get(unit
					.getName() + "_" + UnitTextureEnum.BATTLE_ALLY_NORMAL);
			unit.setBattleTexture(battleTexture);
		} else {
			//적군 각성시의 분기
			Texture battleTexture = Assets.monsterTextureMap.get(unit.getName()
					+ "_" + UnitTextureEnum.BATTLE_ENEMY_NORMAL);
			unit.setBattleTexture(battleTexture);
		}
	}

	public static void setStatusTexture(Unit unit) {
		Texture statusTexture = Assets.characterTextureMap.get(unit.getName()
				+ "_" + UnitTextureEnum.STATUS_NORMAL);
		unit.setStatusTexture(statusTexture);
	}

	public static void setNpcTexture(Unit unit) {
		Texture npcTexture = Assets.characterTextureMap.get(unit.getName()
				+ "_" + UnitTextureEnum.NPC_NORMAL);
		unit.setNpcTexture(npcTexture);
	}
}
