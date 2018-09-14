package com.xpx.bootcamp.jenkins.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;
import com.xpx.bootcamp.jenkins.entity.Parameter;
import com.xpx.bootcamp.jenkins.entity.Parameters;

@Repository
@Profile("WebClient")
public class WebClientJenkinsRestClient implements IJenkinsRestClient {

	@Autowired
	private WebClient client;
	
	@Value("${jenkins.simplebuild.path}")
	private String simpleBuildURI;
	
	@Value("${jenkins.parambuild.path}")
	private String paramBuildURI;
	
	/** The job url. */
	@Value("${jenkins.job.path}")
	private String jobUrl;
	
	@Value("${jenkins.job.build.path}")
	private String buildUrl;
	
	@Autowired
	private ObjectMapper mapper;
	
	private static final Logger LOG = LoggerFactory.getLogger(WebClientJenkinsRestClient.class);
	
	@Override
	public boolean runSimpleBuild() {
		ClientResponse response = client.post().uri(simpleBuildURI).exchange().block();
		return HttpStatus.CREATED.equals(response.statusCode());
	}

	@Override
	public boolean runParamBuild(String param) {
		LOG.info("WEb Client param Build");
		Parameters parameters = new Parameters();
		Parameter parameter = new Parameter();
		parameter.setName("whoIs");
		parameter.setValue(param);
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter);
		parameters.setParameter(parameterList);
		
		String bodyString;
		try {
			bodyString = mapper.writeValueAsString(parameters);
		} catch (JsonProcessingException e) {
			return false;
		}
		
		ClientResponse response = client.post()
				.uri(paramBuildURI)
				.body(BodyInserters.fromFormData("json", bodyString))
				.exchange().block();

		return HttpStatus.CREATED.equals(response.statusCode());
	}

	@Override
	public Job getJob(String jobName) {
		return client.get().uri(jobUrl, jobName).retrieve().bodyToMono(Job.class).block();
		
	}

	@Override
	public Build getBuild(String jobName, Integer id) {
		return client.get()
				.uri(buildUrl, jobName, id)
				.retrieve()
				.bodyToMono(Build.class)
				.block();

	}

}
