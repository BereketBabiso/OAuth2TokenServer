package com.spring.security.oauth.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
@EnableAuthorizationServer
public class SpringSecurityOAuthServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityOAuthServerApplication.class, args);
	}

}
