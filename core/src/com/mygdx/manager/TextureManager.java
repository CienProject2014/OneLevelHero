package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.assets.TextureAssets;
import com.mygdx.enums.TextureEnum;

/**
 * 각각의 유닛에 Texture를 할당해서 쓸 수 있도록 하는 로직 StaticAssets을 다루기 때문에 싱글턴 클래스가 아니다. 위키의
 * 그래픽_작업물_명명규칙을 참고하자
 * http://cien.woobi.co.kr/dokuwiki/doku.php?id=%EC%A0%9C%EC%9E%91%ED%99%9C%EB%
 * 8F%99:%EA%B7%B8%EB%9E%98%ED%94%BD_%EC%9E%91%EC%97%85%EB%AC%BC_%EB%AA%85%EB%AA
 * %85%EA%B7%9C%EC%B9%99
 * 
 * @author Velmont
 * 
 */
public class TextureManager {
	@Autowired
	private AssetsManager assetsManager;
	@Autowired
	private TextureAssets textureAssets;

	public Texture getEtcTexture(String textureName) {
		return assetsManager.get(textureAssets.getTexturePath(textureName), Texture.class);
	}

	public Texture getMonsterTexture(String monsterName) {
		return assetsManager.get(textureAssets.getTexturePath(TextureEnum.MONSTER + "_" + monsterName));
	}

	public Texture getBustTexture(String facePath) {
		if (textureAssets.getTexturePath(facePath) != null) {
			return assetsManager.get(textureAssets.getTexturePath(facePath), Texture.class);
		} else {
			Gdx.app.log("EventScene", "chracterTextureMap에 " + facePath + " 에 해당하는 이미지가 존재하지 않습니다.");
			return getBustTexture("default", "01");
		}
	}

	public Texture getBustTexture(String facePath, String faceNumber) {
		if (textureAssets.getTexturePath(TextureEnum.BUST + "_" + facePath + "_" + faceNumber) != null) {
			return assetsManager.get(
					textureAssets.getTexturePath(TextureEnum.BUST + "_" + facePath + "_" + faceNumber), Texture.class);
		} else {
			Gdx.app.log("TextureManager", "chracterTextureMap에 " + facePath + faceNumber + " 에 해당하는 이미지가 존재하지 않습니다.");
			return getBustTexture("default", "01");
		}
	}
	public Texture getStatusTexture(String facePath) {
		Texture statusTexture = assetsManager.get(textureAssets.getTexturePath(TextureEnum.STATUS + "_" + facePath),
				Texture.class);
		if (statusTexture == null) {
			Gdx.app.log("TextureManager", "TextureEnum.STATUS" + "_" + facePath + "is null");
		}
		return statusTexture;
	}

	public Texture getCharacterBodyTexture(String facePath) {
		return assetsManager.get(textureAssets.getTexturePath(TextureEnum.NPC + "_" + facePath), Texture.class);
	}

	public Texture getFaceTexture(String facePath) {
		return assetsManager.get(textureAssets.getTexturePath(TextureEnum.FACE + "_" + facePath), Texture.class);
	}

	public Texture getMonsterBattleTexture(String facePath) {
		return assetsManager.get(textureAssets.getTexturePath(TextureEnum.MONSTER + "_" + facePath), Texture.class);
	}

	public Texture getMonsterBodyTexture(String facePath) {
		return assetsManager.get(textureAssets.getTexturePath(TextureEnum.MONSTER + "_" + facePath), Texture.class);
	}

	public Texture getItemTexture(String itemPath) {
		return assetsManager
				.get(textureAssets.getTexturePath(TextureEnum.ITEM + "_" + "one_hand_sword"), Texture.class);
	}

	public Texture getBackgroundTexture(String backgroundName) {
		if (textureAssets.getTexturePath(TextureEnum.BACKGROUND + "_" + backgroundName) != null) {
			return assetsManager.get(textureAssets.getTexturePath(TextureEnum.BACKGROUND + "_" + backgroundName),
					Texture.class);
		} else {
			Gdx.app.log("TextureManager", backgroundName + "에 해당하는 이미지가 없습니다");
			return assetsManager.get(textureAssets.getTexturePath(TextureEnum.BACKGROUND + "_" + "black"),
					Texture.class);
		}
	}

	public Texture getBackgroundTexture(String facePath, TextureEnum textureEnum) {
		return assetsManager.get(
				textureAssets.getTexturePath(TextureEnum.BACKGROUND + "_" + facePath + "_" + textureEnum),
				Texture.class);
	}

	public Texture getFaceImage(String facePath) {
		return assetsManager.get(textureAssets.getTexturePath(TextureEnum.FACE + "_" + facePath));
	}

	public Texture getSmallBattleImage(String facePath) {
		return assetsManager.get(textureAssets.getTexturePath(TextureEnum.BATTLE + "_" + facePath + "_"
				+ TextureEnum.SMALL_IMAGE));
	}

	public Texture getBigBattleImage(String facePath) {
		return assetsManager.get(
				textureAssets.getTexturePath(TextureEnum.BATTLE + "_" + facePath + "_" + TextureEnum.BIG_IMAGE),
				Texture.class);
	}
}
