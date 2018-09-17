package com.xpx.bootcamp.jenkins.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.xpx.bootcamp.jenkins.dto.BuildDto;
import com.xpx.bootcamp.jenkins.dto.JenkinsDto;
import com.xpx.bootcamp.jenkins.dto.JobDto;
import com.xpx.bootcamp.jenkins.errors.JenkinsApplicationException;
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
	 * Call Jenkins to do a build with a a parameter.  
	 *
	 * @param returnResponse flag to indicate if the response should be returned
	 * @param dto the input from the request
	 * @return the dto with info about the build
	 */
	@RequestMapping(value="/build/{jobName}", method=RequestMethod.POST)
	public JenkinsDto buildParam(
			@RequestParam(value="response", required=false, defaultValue="true") Boolean returnResponse,
			@RequestBody(required=false) BuildDto dto,
			@PathVariable("jobName") String job) {
			
			if(dto == null) {
				return returnIfWanted(returnResponse, jenkinsService.startBuild(job));
			} 
			
			return returnIfWanted(returnResponse, jenkinsService.startParamBuild(dto.getParam(), job));
	}

	/**
	 * Returns the build numbers for the simple job.
	 *
	 * @return info on the job
	 */
	@RequestMapping(value="/job/simple", method=RequestMethod.GET)
	public JobDto getSimpleJob() {
		return jenkinsService.getJob("job1");
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
		return jenkinsService.getbuild("Job2", id);
		
	}
	
	
	@ExceptionHandler(value=JenkinsApplicationException.class)
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	public void handleApplicationException(Throwable e) {
		LOG.error("error occured", e);
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
