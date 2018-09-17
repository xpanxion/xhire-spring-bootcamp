package com.xpx.bootcamp.jenkins.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;
import com.xpx.bootcamp.jenkins.entity.Parameters;
import com.xpx.bootcamp.jenkins.errors.JenkinsApplicationException;

@Repository
@Profile("WebClient")

public class WebClientJenkinsRestClient implements IJenkinsRestClient {

	@Autowired
	private WebClient client;
	
	@Value("${jenkins.build.path}")
	private String buildPath;
	
	@Value("${jenkins.job.urltemplate}")
	private String jobTemplate;
	
	@Value("${jenkins.build.urltemplate}")
	private String buildTemplate;
	
	private static final Logger LOG = LoggerFactory.getLogger(WebClientJenkinsRestClient.class);
	
	@Autowired
	@Qualifier("jenkinsConverter")
	private ConversionService converter;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public boolean runSimpleBuild(String jobName) {
		String finalPath = String.format(buildPath, jobName);
		LOG.info("in web client rest client");
		
		ClientResponse response = client.post().uri(finalPath).exchange().block();

		return response.statusCode().equals(HttpStatus.CREATED);
	}

	@Override
	public boolean runParamBuild(String param, String jobName) {
		LOG.info("in web client rest client run param build");

		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("whoIs", param);
		Parameters params = converter.convert(paramMap, Parameters.class);
		
		String inputBody;
		try {
			 inputBody = mapper.writeValueAsString(params);
		} catch (JsonProcessingException e) {
			throw new JenkinsApplicationException("error creating body string", e);
		}
		
		ClientResponse response = client.post()
				.uri(String.format(buildPath, jobName))
				.body(BodyInserters.fromFormData("json", inputBody))
				.exchange().block();
		
		return HttpStatus.CREATED.equals(response.statusCode());
	}

	@Override
	public Job getJob(String jobName) {
		LOG.info("in web client rest client getJob");
		return client.get().uri(jobTemplate, jobName).retrieve().bodyToMono(Job.class).block();
			
	}

	@Override
	public Build getBuild(String jobName, Integer id) {
		LOG.info("in web client rest client getbuild");
		return client.get().uri(buildTemplate, jobName, id).retrieve().bodyToMono(Build.class).block();
		
	}

}
