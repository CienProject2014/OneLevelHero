package com.mygdx.game;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.badlogic.gdx.Gdx;
import com.mygdx.state.Assets;

@Configuration
@ComponentScan(basePackages = "com.mygdx")
public class OneLevelHeroApplicationContext {
	public OneLevelHeroApplicationContext() {
		Gdx.app.debug("OneLevelHeroApplicationContext", "ComponentScan 시작");
	}

	@Bean
	public Assets assets() {
		return new Assets();
	}
}
