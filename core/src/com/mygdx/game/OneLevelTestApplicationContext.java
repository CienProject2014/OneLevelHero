package com.mygdx.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.Gdx;
import com.mygdx.state.TestAssets;

@Configuration
@ComponentScan(basePackages = "com.mygdx")
public class OneLevelTestApplicationContext {
	public OneLevelTestApplicationContext() {
		Gdx.app.debug("OneLevelHeroApplicationContext", "ComponentScan 시작");
	}

	@Bean
	public TestAssets assets() {
		return new TestAssets();
	}
}
