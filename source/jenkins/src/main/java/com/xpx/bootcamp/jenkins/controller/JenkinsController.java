package com.xpx.bootcamp.jenkins.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xpx.bootcamp.jenkins.dto.JenkinsDto;
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

	/**
	 * Calls the Jenkins build to do a simple request. The user can indicate if they want a response or not. 
	 *
	 * @param returnResponse true if the user wants a response otherwise false. 
	 * @return the jenkins dto if requested with response info. 
	 * 
	 */
	@RequestMapping(value="/build/simple", method=RequestMethod.GET)
	public JenkinsDto build(@RequestParam("response") boolean returnResponse) {
		JenkinsDto dto = jenkinsService.startBuild();
		
		if(returnResponse) {
			return dto;
		} 
		return null;
	}

	
	
	
}
