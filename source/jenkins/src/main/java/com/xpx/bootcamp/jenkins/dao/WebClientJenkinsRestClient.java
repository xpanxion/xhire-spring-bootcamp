package com.xpx.bootcamp.jenkins.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;

@Repository
@Profile("WebClient")
public class WebClientJenkinsRestClient implements IJenkinsRestClient {

	@Autowired
	private WebClient client;
	
	@Value("${jenkins.simplebuild.path}")
	private String simpleBuildURI;
	
	@Override
	public boolean runSimpleBuild() {
		ClientResponse response = client.post().uri(simpleBuildURI).exchange().block();
		return HttpStatus.CREATED.equals(response.statusCode());
	}

	@Override
	public boolean runParamBuild(String param) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Job getJob(String jobName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Build getBuild(String jobName, Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

}
