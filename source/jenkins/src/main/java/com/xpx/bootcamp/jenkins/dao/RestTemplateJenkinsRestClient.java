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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;
import com.xpx.bootcamp.jenkins.entity.Parameters;
import com.xpx.bootcamp.jenkins.errors.JenkinsApplicationException;

// TODO: Auto-generated Javadoc
/**
 * Allows access to a jenkins server via rest service. 
 */
@Repository
@Profile("RestTemplate")
public class RestTemplateJenkinsRestClient implements IJenkinsRestClient {

	/** The Constant API_JSON. */
	private static final String API_JSON = "/api/json";
	
	/** The Constant BUILD_URL_PATH_PART_FORMAT. */
	private static final String BUILD_URL_PATH_PART_FORMAT = "%1$s/%2$s" + API_JSON;

	/** Rest template for accessing the server. */
	@Autowired
	private RestTemplate template;
	
	/** The object mapper used to convert an object to JSON. */
	@Autowired
	private ObjectMapper objectMapper;
	
	/** The url of the jenkins server for a simple build. */
	@Value("${jenkins.build.url}")
	private String buildUrl;
	
	/** The job url. */
	@Value("${jenkins.job.url}")
	private String jobUrl;
	
	@Autowired
	@Qualifier("jenkinsConverter")
	private ConversionService converter;
	
	/** LOGGER. */
	private static final Logger LOG = LoggerFactory.getLogger(RestTemplateJenkinsRestClient.class);
	
	/* (non-Javadoc)
	 * @see com.xpx.bootcamp.jenkins.dao.IJenkinsRestClient#runSimpleBuild(java.lang.String)
	 */
	@Override
	public boolean runSimpleBuild(String jobName) {
		ResponseEntity<Object> response = template.postForEntity(String.format(buildUrl, jobName), null, null);
		return HttpStatus.CREATED.equals(response.getStatusCode());
	}
	
	/* (non-Javadoc)
	 * @see com.xpx.bootcamp.jenkins.dao.IJenkinsRestClient#runParamBuild(java.lang.String, java.lang.String)
	 */
	@Override
	public boolean runParamBuild(String param, String jobName) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("whoIs", param);

		Parameters parameters = converter.convert(paramMap, Parameters.class);
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		
		try {
			map.add("json", objectMapper.writeValueAsString(parameters));
		} catch (JsonProcessingException e) {
			throw new JenkinsApplicationException("Problem with conerting to parameters", e);
		}
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		ResponseEntity<Object> response = template.postForEntity(String.format(buildUrl, jobName), request, null);
		
		return HttpStatus.CREATED.equals(response.getStatusCode());
		
	}
	
	/* (non-Javadoc)
	 * @see com.xpx.bootcamp.jenkins.dao.IJenkinsRestClient#getJob(java.lang.String)
	 */
	@Override
	public Job getJob(String jobName) {
		
		String url = jobUrl + jobName + API_JSON;

		return template.getForObject(url, Job.class);
		
	}
	
	/* (non-Javadoc)
	 * @see com.xpx.bootcamp.jenkins.dao.IJenkinsRestClient#getBuild(java.lang.String, java.lang.Integer)
	 */
	@Override
	public Build getBuild(String jobName, Integer id) {
		String url = jobUrl + String.format(BUILD_URL_PATH_PART_FORMAT, jobName, id);
		LOG.info(url);
		return template.getForObject(url, Build.class);
	}
	
}
