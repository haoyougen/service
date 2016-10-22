package com.ww;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource(value = { "classpath:service-web.xml" })
public class ServiceApplication {

	public static void main(String[] args) {
		// logback.configurationFile
		System.setProperty("logging.config", System.getProperty("APP_HOME") + File.separator + "conf" + File.separator
				+ System.getProperty("env") + File.separator + "logback.xml");

		SpringApplication.run(ServiceApplication.class, args);
	}
}
