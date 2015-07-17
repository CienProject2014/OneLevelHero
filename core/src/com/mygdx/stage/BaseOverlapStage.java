package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.Assets;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.CameraManager;
import com.mygdx.manager.PartyManager;
import com.mygdx.manager.PositionManager;
import com.uwsoft.editor.renderer.Overlap2DStage;

public class BaseOverlapStage extends Overlap2DStage {
	@Autowired
	protected Assets assets;
	@Autowired
	protected ScreenFactory screenFactory;
	@Autowired
	protected CameraManager cameraManager;
	@Autowired
	protected PartyManager partyManager;
	@Autowired
	protected PositionManager positionManager;
}
