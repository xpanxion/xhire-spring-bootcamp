package com.xpx.bootcamp.jenkins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpx.bootcamp.jenkins.dao.IJenkinsRestClient;
import com.xpx.bootcamp.jenkins.dto.BuildDto;
import com.xpx.bootcamp.jenkins.dto.JenkinsDto;
import com.xpx.bootcamp.jenkins.dto.JobDto;
import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;

/**
 * Service for accessing jenkins.
 */
@Service
public class JenkinsService {

	/** The rest client to access jenknins with. */
	@Autowired
	IJenkinsRestClient restClient;
	
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
	
	/**
	 * Starts a build on the jenkins server.
	 *
	 * @param param the param
	 * @return the jenkins dto with info about the build.
	 */
	public JenkinsDto startParamBuild(String param) {
		JenkinsDto retval = new JenkinsDto();
		
		retval.setSuccess(restClient.runParamBuild(param));
		retval.setValue(param);
		
		return retval;
	}
	
	/**
	 * Gets the job based on the passed in job name.
	 *
	 * @param jobName the job name
	 * @return the job
	 */
	public JobDto getJob(String jobName) {
		Job job = restClient.getJob(jobName);
		
		List<Integer> ints = new ArrayList<>();
		for(Build build : job.getBuilds()) {
			ints.add(build.getNumber());
		}
		
		JobDto retVal = new JobDto();
		retVal.setNumbers(ints);
		
		
		return retVal;
	}
	
	/**
	 * Gets the builds for the job and build number.
	 *
	 * @param jobName the job name
	 * @param id the id
	 * @return the build info
	 */
	public BuildDto getBuild(String jobName, Integer id) {
		Build build = restClient.getBuild(jobName, id);
		
		BuildDto dto = new BuildDto();
		dto.setNumber(build.getNumber());
		//TODO:  Help this later on
		dto.setParam(build.getActions().get(0).getParameters().get(0).getValue());
		return dto;
		
	}
	
	
	
}
