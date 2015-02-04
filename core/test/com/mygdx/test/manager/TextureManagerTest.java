package com.mygdx.test.manager;

import static org.mockito.Mockito.*;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.mygdx.game.OneLevelHeroApplicationContext;
import com.mygdx.game.OneLevelHero;
import com.mygdx.state.Assets;
import com.mygdx.test.GdxTestRunner;

@RunWith(GdxTestRunner.class)
public class TextureManagerTest {
	@Autowired
	private Assets assets;
	private static ApplicationContext context;

	@BeforeClass
	public static void springInit() {
		Gdx.gl = mock(GL20.class);
		Gdx.gl20 = mock(GL20.class);
		context = new AnnotationConfigApplicationContext(
				OneLevelHeroApplicationContext.class);
		context.getBean(OneLevelHero.class);
	}
}
