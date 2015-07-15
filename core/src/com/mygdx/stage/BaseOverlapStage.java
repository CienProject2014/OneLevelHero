package com.mygdx.stage;

import org.springframework.beans.factory.annotation.Autowired;

import com.mygdx.assets.Assets;
import com.mygdx.currentState.MovingInfo;
import com.mygdx.currentState.PartyInfo;
import com.mygdx.currentState.PositionInfo;
import com.mygdx.currentState.TimeInfo;
import com.mygdx.factory.ScreenFactory;
import com.mygdx.manager.CameraManager;
import com.uwsoft.editor.renderer.Overlap2DStage;

public class BaseOverlapStage extends Overlap2DStage {
	@Autowired
	protected Assets assets;
	@Autowired
	protected ScreenFactory screenFactory;
	@Autowired
	protected CameraManager cameraManager;
	@Autowired
	protected PartyInfo partyInfo;
	@Autowired
	protected PositionInfo positionInfo;
	@Autowired
	protected TimeInfo timeInfo;
	@Autowired
	protected MovingInfo movingInfo;
}
