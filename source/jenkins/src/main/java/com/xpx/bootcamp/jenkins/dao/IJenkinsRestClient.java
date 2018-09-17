package com.xpx.bootcamp.jenkins.dao;

import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;

public interface IJenkinsRestClient {

	/**
	 * Runs a simple build on the jenkins server and returns true if the expected status code is returned. 
	 *
	 * @return true, if successful
	 */
	boolean runSimpleBuild(String jobName);

	/**
	 * Runs a paramatarized build with the passed in value.
	 *
	 * @param param the parameter to use
	 * @return true, if successful
	 */
	boolean runParamBuild(String param, String jobName);

	/**
	 * Gets information on a job based on the passed in job name. 
	 *
	 * @param jobName the name of the job to get info for
	 * @return the job info
	 */
	Job getJob(String jobName);

	/**
	 * Gets the builds information based on the job name and build id
	 *
	 * @param jobName the job name 
	 * @param id the id of the build to get the info 
	 * @return build info
	 */
	Build getBuild(String jobName, Integer id);

}