package com.mygdx.manager;

import org.springframework.beans.factory.annotation.Autowired;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.assets.TextureAssets;
import com.mygdx.enums.TextureEnum;
import com.sun.istack.internal.NotNull;

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
	private String[] preName = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
	// 1. 똑같은걸 불러왔을 때
	// 2. 전투 (미리 로드)
	// 3. 목표마을(미리 로드)

	public boolean checkPreName(String nowName, String preName) {
		boolean check;
		if (nowName == preName) {
			check = true;
		} else {
			check = false;
			if (preName != "") {
				if (assetsManager.isLoaded(textureAssets.getTexturePath(preName))) {
					assetsManager.unload(textureAssets.getTexturePath(preName));
					assetsManager.finishLoading();
				}
			}
		}
		return check;
	}

	@NotNull
	public Texture getTexture(String texturePath, String defaultPath) {
		if (textureAssets.getTexturePath(texturePath) != null) {
			assetsManager.load(textureAssets.getTexturePath(texturePath), Texture.class);
			assetsManager.finishLoading();
			return assetsManager.get(textureAssets.getTexturePath(texturePath));
		} else {
			Gdx.app.log("TextureManager", texturePath + " is Null.");
			return getTexture(defaultPath);
		}
	}

	public Texture getTexture(String texturePath) {
		return getTexture(texturePath, TextureEnum.BUST + "_default_01");
	}

	public Texture getMonsterTexture(String monsterName) {
		return getTexture(TextureEnum.MONSTER + "_" + monsterName);
	}

	public Texture getBustTexture(String facePath) {
		return getTexture(facePath);
	}

	public Texture getBustTexture(String facePath, String faceNumber) {
		return getTexture(TextureEnum.BUST + "_" + facePath + "_" + faceNumber);
	}

	public Texture getStatusTexture(String facePath) {
		return getTexture(TextureEnum.STATUS + "_" + facePath);
	}

	public Texture getCharacterBodyTexture(String facePath) {
		if (textureAssets.getTexturePath(TextureEnum.NPC + "_" + facePath) != null) {
			if (!checkPreName(TextureEnum.NPC + "_" + facePath, preName[5])) {
				preName[5] = TextureEnum.NPC + "_" + facePath;
				assetsManager.load(textureAssets.getTexturePath(preName[5]), Texture.class);
				assetsManager.finishLoading();
			} else {
				preName[5] = TextureEnum.NPC + "_" + facePath;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[5]), Texture.class);
		} else
			return getTexture(preName[5]);
	}

	public Texture getFaceTexture(String facePath) {
		return getTexture(TextureEnum.FACE + "_" + facePath);
	}

	public Texture getMonsterBattleTexture(String facePath) {
		if (textureAssets.getTexturePath(TextureEnum.MONSTER + "_" + facePath) != null) {
			if (!checkPreName(TextureEnum.MONSTER + "_" + facePath, preName[7])) {
				preName[7] = TextureEnum.MONSTER + "_" + facePath;
				assetsManager.load(textureAssets.getTexturePath(preName[7]), Texture.class);
				assetsManager.finishLoading();
			} else {
				preName[7] = TextureEnum.MONSTER + "_" + facePath;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[7]), Texture.class);
		} else
			return getTexture(preName[7]);
	}

	public Texture getMonsterBodyTexture(String facePath) {
		if (textureAssets.getTexturePath(TextureEnum.MONSTER + "_" + facePath) != null) {
			if (!checkPreName(TextureEnum.MONSTER + "_" + facePath, preName[8])) {
				preName[8] = TextureEnum.MONSTER + "_" + facePath;
				assetsManager.load(textureAssets.getTexturePath(preName[8]), Texture.class);
				assetsManager.finishLoading();
			} else {
				preName[8] = TextureEnum.MONSTER + "_" + facePath;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[8]), Texture.class);
		} else
			return getTexture(preName[8]);
	}

	public Texture getItemTexture(String itemPath) {
		return getTexture(TextureEnum.ITEM + "_" + "one_hand_sword");
	}

	public Texture getBackgroundTexture(String backgroundName) {
		if (textureAssets.getTexturePath(TextureEnum.BACKGROUND + "_" + backgroundName) != null) {
			if (!checkPreName(TextureEnum.BACKGROUND + "_" + backgroundName, preName[10])) {
				preName[10] = TextureEnum.BACKGROUND + "_" + backgroundName;
				assetsManager.load(textureAssets.getTexturePath(preName[10]), Texture.class);
				assetsManager.finishLoading();
			} else {
				preName[10] = TextureEnum.BACKGROUND + "_" + backgroundName;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[10]), Texture.class);
		} else
			return getTexture(preName[10], TextureEnum.BACKGROUND + "_" + "prog_team_01");
	}

	public Texture getBackgroundTexture(String facePath, TextureEnum textureEnum) {
		if (textureAssets.getTexturePath(TextureEnum.BACKGROUND + "_" + facePath + "_" + textureEnum) != null) {
			if (!checkPreName(TextureEnum.BACKGROUND + "_" + facePath + "_" + textureEnum, preName[12])) {
				preName[12] = TextureEnum.BACKGROUND + "_" + facePath + "_" + textureEnum;
				assetsManager.load(textureAssets.getTexturePath(preName[12]), Texture.class);
				assetsManager.finishLoading();
			} else {
				preName[12] = TextureEnum.BACKGROUND + "_" + facePath + "_" + textureEnum;
			}
			return assetsManager.get(textureAssets.getTexturePath(preName[12]), Texture.class);
		} else
			return getTexture(preName[12]);
	}

	public Texture getFaceImage(String facePath) {
		return getTexture(TextureEnum.FACE + "_" + facePath);
	}

	public Texture getSmallBattleImage(String facePath) {
		return getTexture(TextureEnum.BATTLE + "_" + facePath + "_" + TextureEnum.SMALL_IMAGE);
	}

	public Texture getBigBattleImage(String facePath) {
		return getTexture(TextureEnum.BATTLE + "_" + facePath + "_" + TextureEnum.BIG_IMAGE, TextureEnum.BATTLE
				+ "_default_" + TextureEnum.BIG_IMAGE);
	}
}
