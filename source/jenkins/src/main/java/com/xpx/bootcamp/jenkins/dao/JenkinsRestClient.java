package com.xpx.bootcamp.jenkins.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

/**
 * Allows access to a jenkins server via rest service. 
 */
@Repository
public class JenkinsRestClient {

	/** Rest template for accessing the server. */
	@Autowired
	private RestTemplate template;
	
	/** The url of the jenkins server for a simple build. */
	@Value("${jenkins.simplebuild.url}")
	private String url;
	
	
	/**
	 * Runs a simple build on the jenkins server and returns true if the expected status code is returned. 
	 *
	 * @return true, if successful
	 */
	public boolean runSimpleBuild() {
		ResponseEntity<Object> response = template.postForEntity(url, null, null);
		return HttpStatus.CREATED.equals(response.getStatusCode());
	}
	
}
