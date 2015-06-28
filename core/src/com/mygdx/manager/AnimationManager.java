package com.mygdx.manager;

import com.mygdx.enums.TextureEnum;
import com.mygdx.model.AnimationSheet;
import com.mygdx.state.StaticAssets;

public class AnimationManager {
	private AnimationManager(){	
		// 객체를 생성하지 못하게 하기 위해 private으로 설정
	}
	
	public static AnimationSheet getAnimationSheet(TextureEnum name) {
		return StaticAssets.animationSheetMap.get(name);
	}
}
