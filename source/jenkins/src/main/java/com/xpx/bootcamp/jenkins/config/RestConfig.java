package com.xpx.bootcamp.jenkins.config;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.util.Base64Utils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configuration for the rest template access to jenkins. 
 */
@Configuration
public class RestConfig {
	
	/** The jenkins user name to access the server with */
	@Value("${jenkins.user}")
	private String user;
	
	/** The jenkins password to access the server with. */
	@Value("${jenkins.pass}")
	private String password;

	@Value("${jenkins.host}")
	private String host;
	
	/**
	 * Creates a rest template with basic auth using the user name and password from the properties file. 
	 *
	 * @return the rest template to use to access the jenkins server. 
	 */
	@Bean
	@Profile("RestTemplate")
	public RestTemplate jenkinsRestTemplate() {
		RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
		return restTemplateBuilder.basicAuthorization(user, password).build();
		
	}
	
	@Bean
	@Profile("WebClient")
	public WebClient webClient() throws UnsupportedEncodingException{
		return WebClient.builder()
				.baseUrl(host)
				.defaultHeader("Authorization", "Basic " + Base64Utils.encodeToString((user + ":" + password).getBytes("UTF-8"))).build();
		
	}
	
}
