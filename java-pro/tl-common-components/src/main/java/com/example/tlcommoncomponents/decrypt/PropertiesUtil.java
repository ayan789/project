package com.example.tlcommoncomponents.decrypt;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class PropertiesUtil {

	@Autowired
	Environment env;
	private static Environment environment;

	@PostConstruct
	public final void init() {
		environment = env;
	}

	public final static String getProperty(String key, String defaultVal) {
		String val = System.getProperty(key);
		if (StringUtils.isBlank(val)) {
			val = environment.getProperty(key, defaultVal);
			log.info("PropertiesUtil.getProperty.key:{}",key);
			log.info("PropertiesUtil.getProperty.url:{}",val);
		}
		return val;
	}
}
