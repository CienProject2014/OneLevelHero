package com.mygdx.state;

//package com.mygdx.state;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.model.Hero;
import com.mygdx.model.Monster;
import com.mygdx.model.NPC;
import com.mygdx.model.Village;
import com.mygdx.model.WorldNode;

/**
 * 각종 리소스들을 static하게 관리해주는 Assets 클래스 Stage및 Screen에 필요한 요소들을 전달해준다.
 * 
 * @author Velmont
 * 
 */

@Component
public interface Assets {
	float windowWidth = 0;
	float windowHeight = 0;
	Skin skin = null;
	Map<String, Texture> backgroundTextureMap = null;
	Map<String, TextureRegionDrawable> atlasUiMap = null;
	BitmapFont font = null;
	Drawable[] chatButton = null;
	Map<String, Village> villageMap = null;
	Map<String, WorldNode> worldNodeInfoMap = null;
	float musicVolume = 0;
	Music mainMusic = null;
	Map<String, NPC> npcMap = null;
	Map<String, Texture> characterTextureMap = null;
	Map<String, Texture> monsterTextureMap = null;
	Map<String, Hero> heroMap = null;
	Map<String, Monster> monsterMap = null;
	TextureAtlas items = null;

	public void loadAll();

	void resourceFileLoad();

	void mapInfoLoad();

	void unitInfoLoad();

	void loadFilePath();

	// Stage 크기 설정
	public void loadSize(Stage stage);

	// JsonFile의 path를 읽어온다.
	void jsonObjectLoad();

	void roadInfoLoad();

	void heroInfoLoad();

	void worldMapInfoLoad();

	void monsterInfoLoad();

	void villageInfoLoad();

	void npcInfoLoad();

	void atlasUiTextureLoad();

	void sceneTextureLoad();

	void fontLoad();

	void chatButtonLoad();

	// 임시용, 추후 제거 예정
	void etcResourceLoad();
}
