package com.xpx.bootcamp.jenkins.dao;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.xpx.bootcamp.jenkins.entity.Parameter;
import com.xpx.bootcamp.jenkins.entity.Parameters;

// TODO: Auto-generated Javadoc
/**
 * Allows access to a jenkins server via rest service. 
 */
@Repository
public class JenkinsRestClient {

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
	@Value("${jenkins.simplebuild.url}")
	private String simpleUrl;
	
	/** The url of the jenkins server for a simple build. */
	@Value("${jenkins.parambuild.url}")
	private String paramUrl;
	
	/** The job url. */
	@Value("${jenkins.job.url}")
	private String jobUrl;
	
	/** LOGGER. */
	private static final Logger LOG = LoggerFactory.getLogger(JenkinsRestClient.class);
	
	/**
	 * Runs a simple build on the jenkins server and returns true if the expected status code is returned. 
	 *
	 * @return true, if successful
	 */
	public boolean runSimpleBuild() {
		ResponseEntity<Object> response = template.postForEntity(simpleUrl, null, null);
		return HttpStatus.CREATED.equals(response.getStatusCode());
	}
	
	/**
	 * Runs a paramatarized build with the passed in value.
	 *
	 * @param param the parameter to use
	 * @return true, if successful
	 */
	public boolean runParamBuild(String param) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		
		//TODO : Use converters to convert from DTO to Entity 
		Parameters parameters = new Parameters();
		Parameter parameter = new Parameter();
		parameter.setName("whoIs");
		parameter.setValue(param);
		List<Parameter> parameterList = new ArrayList<>();
		parameterList.add(parameter);
		parameters.setParameter(parameterList);
		
		
		MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		
		//TODO : Add Error Handling 
		try {
			map.add("json", objectMapper.writeValueAsString(parameters));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		
		ResponseEntity<Object> response = template.postForEntity(paramUrl, request, null);
		
		return HttpStatus.CREATED.equals(response.getStatusCode());
		
	}
	
	/**
	 * Gets information on a job based on the passed in job name. 
	 *
	 * @param jobName the name of the job to get info for
	 * @return the job info
	 */
	public Job getJob(String jobName) {
		
		String url = jobUrl + jobName + API_JSON;

		return template.getForObject(url, Job.class);
		
	}
	
	/**
	 * Gets the builds information based on the job name and build id
	 *
	 * @param jobName the job name 
	 * @param id the id of the build to get the info 
	 * @return build info
	 */
	public Build getBuild(String jobName, Integer id) {
		String url = jobUrl + String.format(BUILD_URL_PATH_PART_FORMAT, jobName, id);
		LOG.info(url);
		return template.getForObject(url, Build.class);
	}
	
}
