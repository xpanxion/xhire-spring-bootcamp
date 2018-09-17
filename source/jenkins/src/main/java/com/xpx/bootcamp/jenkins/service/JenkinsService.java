package com.xpx.bootcamp.jenkins.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
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
	private IJenkinsRestClient restClient;
	
	@Autowired
	@Qualifier("jenkinsConverter")
	private ConversionService converter;
	
	/**
	 * Starts a build on the jenkins server.
	 *
	 * @return the jenkins dto with info about the build. 
	 */
	public JenkinsDto startBuild(String jobName) {
		JenkinsDto retval = new JenkinsDto();
		
		retval.setSuccess(restClient.runSimpleBuild(jobName));
		return retval;
	}
	
	/**
	 * Starts a build on the jenkins server.
	 *
	 * @param param the param
	 * @return the jenkins dto with info about the build.
	 */
	public JenkinsDto startParamBuild(String param, String jobName) {
		JenkinsDto retval = new JenkinsDto();
		
		retval.setSuccess(restClient.runParamBuild(param, jobName));
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
	public BuildDto getbuild(String jobName, Integer id) {
		Build build = restClient.getBuild(jobName, id);
		
		return converter.convert(build, BuildDto.class);
		
	}
	
	
	
}
