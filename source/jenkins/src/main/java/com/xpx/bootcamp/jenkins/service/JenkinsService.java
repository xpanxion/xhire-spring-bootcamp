package com.xpx.bootcamp.jenkins.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpx.bootcamp.jenkins.dao.JenkinsRestClient;
import com.xpx.bootcamp.jenkins.dto.JenkinsDto;

/**
 * Service for accessing jenkins.
 */
@Service
public class JenkinsService {

	/** The rest client to access jenknins with. */
	@Autowired
	JenkinsRestClient restClient;
	
	/**
	 * Starts a build on the jenkins server.
	 *
	 * @return the jenkins dto with info about the build. 
	 */
	public JenkinsDto startBuild() {
		JenkinsDto retval = new JenkinsDto();
		
		retval.setSuccess(restClient.runSimpleBuild());
		return retval;
	}
	
}
