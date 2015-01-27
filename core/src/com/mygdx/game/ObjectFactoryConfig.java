package com.mygdx.game;

import org.springframework.beans.factory.config.ObjectFactoryCreatingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectFactoryConfig {
	@Bean
	public ObjectFactoryCreatingFactoryBean serviceRequestFactory() {
		ObjectFactoryCreatingFactoryBean factoryBean = new ObjectFactoryCreatingFactoryBean();
		factoryBean.setTargetBeanName("serviceRequest");
		return factoryBean;
	}
}
