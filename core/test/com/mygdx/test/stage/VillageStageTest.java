package com.mygdx.test.stage;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.game.OneLevelHero;
import com.mygdx.stage.VillageStage;
import com.mygdx.state.TestAssets;

/**
 * 아직 미구현
 * 
 * @author Velmont
 *
 */
@Component
public class VillageStageTest extends ApplicationAdapter {
	@Autowired
	private static ApplicationContext context;
	@Autowired
	private TestAssets testAssets;
	private VillageStage villageStage;

	@BeforeClass
	public static void springInit() {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 480;
		config.height = 320;
		new LwjglApplication(new OneLevelHero(), config);
	}

	@Before
	public void makeStage() {
		villageStage = (VillageStage) context.getBean(StageFactory.class)
				.makeStage(StageEnum.VILLAGE);
	}

	@Test
	public void confirmStageInfo() {
		assertThat(villageStage.getCam().viewportHeight,
				is(testAssets.windowHeight));
	}
}
