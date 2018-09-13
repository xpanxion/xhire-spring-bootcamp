package com.xpx.bootcamp.jenkins.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpx.bootcamp.jenkins.dto.BuildDto;
import com.xpx.bootcamp.jenkins.dto.JenkinsDto;
import com.xpx.bootcamp.jenkins.dto.JobDto;
import com.xpx.bootcamp.jenkins.service.JenkinsService;

/**
 * Controller for accessing Jenkins builds via a rest service. 
 */
@RestController
@RequestMapping("/api")
public class JenkinsController {
	
	/** The jenkins service. */
	@Autowired
	private JenkinsService jenkinsService;

	/** The Logger. */
	private static final Logger LOG  = LoggerFactory.getLogger(JenkinsController.class);
	
	/**
	 * Calls the Jenkins build to do a simple request. The user can indicate if they want a response or not. 
	 *
	 * @param returnResponse true if the user wants a response otherwise false. 
	 * @return the jenkins dto if requested with response info. 
	 * 
	 */
	@RequestMapping(value="/build/simple", method=RequestMethod.GET)
	public JenkinsDto build(@RequestParam(value="response", required=false, defaultValue="true") Boolean returnResponse) {
		return returnIfWanted(returnResponse,jenkinsService.startBuild());

	}

	/**
	 * Call Jenkins to do a build with a a parameter.  
	 *
	 * @param returnResponse flag to indicate if the response should be returned
	 * @param dto the input from the request
	 * @return the dto with info about the build
	 */
	@RequestMapping(value="/build/param", method=RequestMethod.POST)
	public JenkinsDto buildParam(@RequestParam(value="response", required=false, defaultValue="true") Boolean returnResponse,
			@RequestBody BuildDto dto) {
			LOG.info("Param is {}", dto.getParam());
			return returnIfWanted(returnResponse, jenkinsService.startParamBuild(dto.getParam()));
	}

	/**
	 * Returns the build numbers for the simple job.
	 *
	 * @return info on the job
	 */
	@RequestMapping(value="/job/simple", method=RequestMethod.GET)
	public JobDto getSimpleJob() {
		return jenkinsService.getJob("Job1");
	}
	
	/**
	 * Returns the build numbers for the param jobs.
	 *
	 * @return info on the param build. 
	 */
	@RequestMapping(value="/job/param", method=RequestMethod.GET)
	public JobDto getParamJob() {
		return jenkinsService.getJob("Job2");
	}
	
	/**
	 * Returns info on a particular parameter build. 
	 *
	 * @param id the number of the build to check
	 * @return the build info
	 */
	@RequestMapping(value="/job/param/{id}", method=RequestMethod.GET) 
	public BuildDto getBuild(@PathVariable("id") Integer id) {
		return jenkinsService.getBuild("Job2", id);
		
	}
	
	
	/**
	 * Returns the passed in object if the flag is true other wise null. 
	 *
	 * @param wanted true if the result should be return otherwise null
	 * @param dto the dto to return
	 * @return the jenkins dto if wanted is true otherwise null
	 */
	private JenkinsDto returnIfWanted(Boolean wanted, JenkinsDto dto) {
		if(wanted) {
			return dto;
		} 
		return null;
	}
	
}
