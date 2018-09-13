package com.xpx.bootcamp.jenkins.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for the rest template access to jenkins. 
 */
@Configuration
public class RestTemplateConfig {
	
	/** The jenkins user name to access the server with */
	@Value("${jenkins.user}")
	private String user;
	
	/** The jenkins password to access the server with. */
	@Value("${jenkins.pass}")
	private String password;

	/**
	 * Creates a rest template with basic auth using the user name and password from the properties file. 
	 *
	 * @return the rest template to use to access the jenkins server. 
	 */
	@Bean
	public RestTemplate jenkinsRestTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.basicAuthorization(user, password).build();

	}

	@Bean
	public RestTemplateBuilder restTemplatebuilder() {
		return new RestTemplateBuilder();
	}
	
}
