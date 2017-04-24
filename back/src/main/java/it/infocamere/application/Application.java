package it.infocamere.application;

import javax.security.auth.message.config.AuthConfigFactory;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// non necessario in quanto presente nel file mvc-config.xml
 @ComponentScan({"it.infocamere.controllers","it.infocamere.entity","it.infocamere.application","it.infocamere.springrepository"})

public class Application extends SpringBootServletInitializer{
	public Application() {
		super();
		setRegisterErrorPageFilter(false);
	}
	public static void main(String[] args) throws Exception {
		// bug con Tomcat 8
		if (AuthConfigFactory.getFactory() == null) {
			AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
		}
		//premette hot reload
		System.setProperty("spring.devtools.restart.enabled", "true");

		SpringApplication.run(Application.class, args);
	}
}
