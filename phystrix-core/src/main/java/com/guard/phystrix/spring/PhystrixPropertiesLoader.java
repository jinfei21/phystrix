package com.guard.phystrix.spring;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.netflix.config.ConfigurationManager;

@Component
public class PhystrixPropertiesLoader implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		final ApplicationContext app= event.getApplicationContext(); 
		if (null == app.getParent()) {
			try {
					ConfigurationManager.loadAppOverrideProperties("phystrix");
					ConfigurationManager.loadAppOverrideProperties("phystrix_config");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

}
