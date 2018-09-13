package com.xpx.bootcamp.jenkins.dao;

import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;

public interface IJenkinsRestClient {

	boolean runSimpleBuild();
	
	boolean runParamBuild(String param);
	
	public Job getJob(String jobName);
	
	Build getBuild(String jobName, Integer id);
}
