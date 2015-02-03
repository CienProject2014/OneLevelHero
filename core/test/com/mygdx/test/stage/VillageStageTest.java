package com.mygdx.test.stage;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.enums.StageEnum;
import com.mygdx.factory.StageFactory;
import com.mygdx.game.OneLevelHeroApplicationContext;
import com.mygdx.game.OneLevelHeroSpring;
import com.mygdx.stage.VillageStage;
import com.mygdx.state.Assets;
import com.mygdx.test.GdxTestRunner;

/**
 * 아직 미구현
 * 
 * @author Velmont
 *
 */
@Component
@RunWith(GdxTestRunner.class)
public class VillageStageTest {
	@Autowired
	private StageFactory stageFactory;
	@Autowired
	@Qualifier("testAssets")
	private static Assets assets;
	private static ApplicationContext context;
	private VillageStage villageStage;

	@BeforeClass
	public static void springInit() {
		Gdx.gl = mock(GL20.class);
		Gdx.gl20 = mock(GL20.class);
		assets.loadAll();
		context = new AnnotationConfigApplicationContext(
				OneLevelHeroApplicationContext.class);
	}

	@Before
	public void makeStage() {

		context.getBean(OneLevelHeroSpring.class);
		villageStage = (VillageStage) stageFactory.makeStage(StageEnum.VILLAGE);
	}

	@Test
	public void confirmStageInfo() {
		assertThat(villageStage.getCam().viewportHeight,
				is(Assets.windowHeight));
	}
}
