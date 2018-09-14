package com.xpx.bootcamp.jenkins.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;
import com.xpx.bootcamp.jenkins.entity.Parameters;

@RunWith(MockitoJUnitRunner.class)
public class JenkinsRestClientTest {
	
	@Mock
	private RestTemplate template;
	
	@Mock
	private ObjectMapper mapper;
	
	@InjectMocks
	private JenkinsRestClient testee;

	@Mock 
	private ConversionService converter;
	
	@Before
	public void setup() {
		ReflectionTestUtils.setField(testee, "buildUrl", "simpleUrl");
		ReflectionTestUtils.setField(testee, "jobUrl", "jobUrl");
		
	}
	
	
	@Test
	public void testRunSimpleBuild() {
		//given
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.CREATED);
		when(template.postForEntity("simpleUrl", null, null)).thenReturn(response);
		String jobName = "jobName";
		
		//when
		boolean output = testee.runSimpleBuild(jobName);
		
		//then
		assertThat(output, is(true));
	
	}

	@Test
	public void testRunSimpleBuild_WithError() {
		//given
		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		when(template.postForEntity("simpleUrl", null, null)).thenReturn(response);
		String jobName = "jobName";
		
		//when
		boolean output = testee.runSimpleBuild(jobName);
		
		//then
		assertThat(output, is(false));
	
	}
	
	@Test
	public void testRunParamBuild() throws JsonProcessingException {
		//Given
		String param = "bob";
		Parameters parameters = new Parameters();
		when(converter.convert(Mockito.isA(Map.class), Mockito.eq(Parameters.class))).thenReturn(parameters);
		
		when(mapper.writeValueAsString(parameters)).thenReturn("json string");

		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.CREATED);
		when(template.postForEntity(Mockito.eq("simpleUrl"), Mockito.isA(HttpEntity.class), Mockito.eq(null))).thenReturn(response);
		String jobName = "jobName";

		
		//when
		boolean output = testee.runParamBuild(param, jobName);
		
		//then
		assertThat(output, is(true));
		
		ArgumentCaptor<Map<String,String>> paramCaptor = ArgumentCaptor.forClass(Map.class);
		verify(converter).convert(paramCaptor.capture(), Mockito.eq(Parameters.class));
		
		assertThat(paramCaptor.getValue().get("whoIs"), is(param));

		
		ArgumentCaptor<HttpEntity<MultiValueMap<String, String>>> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
		verify(template).postForEntity(Mockito.eq("simpleUrl"), entityCaptor.capture(), Mockito.eq(null));
		
		assertThat(entityCaptor.getValue().getBody().get("json").get(0), is("json string"));
		assertThat(entityCaptor.getValue().getHeaders().getContentType(), is(MediaType.APPLICATION_FORM_URLENCODED));
		
	
	}

	@Test
	public void testRunParamBuild_BadResult() throws JsonProcessingException {
		//Given
		String param = "bob";
		Parameters parameters = new Parameters();
		when(converter.convert(Mockito.isA(Map.class), Mockito.eq(Parameters.class))).thenReturn(parameters);
		
		when(mapper.writeValueAsString(parameters)).thenReturn("json string");

		ResponseEntity<Object> response = new ResponseEntity<>(HttpStatus.I_AM_A_TEAPOT);
		when(template.postForEntity(Mockito.eq("simpleUrl"), Mockito.isA(HttpEntity.class), Mockito.eq(null))).thenReturn(response);
		String jobName = "jobName";

		
		//when
		boolean output = testee.runParamBuild(param, jobName);
		
		//then
		assertThat(output, is(false));
		
		ArgumentCaptor<Map<String,String>> paramCaptor = ArgumentCaptor.forClass(Map.class);
		verify(converter).convert(paramCaptor.capture(), Mockito.eq(Parameters.class));
		
		assertThat(paramCaptor.getValue().get("whoIs"), is(param));

		
		ArgumentCaptor<HttpEntity<MultiValueMap<String, String>>> entityCaptor = ArgumentCaptor.forClass(HttpEntity.class);
		verify(template).postForEntity(Mockito.eq("simpleUrl"), entityCaptor.capture(), Mockito.eq(null));
		
		assertThat(entityCaptor.getValue().getBody().get("json").get(0), is("json string"));
		assertThat(entityCaptor.getValue().getHeaders().getContentType(), is(MediaType.APPLICATION_FORM_URLENCODED));
	
	}
	
	
	@Test
	public void testGetJob() {
		//given
		String jobName = "job";
		
		Job job = new Job();
		when(template.getForObject("jobUrl" + jobName + "/api/json", Job.class)).thenReturn(job);
		
		//when
		Job output = testee.getJob(jobName);
		
		//then
		assertThat(output, is(job));
		
		
	}

	@Test
	public void testGetBuild() {
		//given
		String jobName = "job";
		Integer id = 123;
		
		Build build = new Build();
		when(template.getForObject("jobUrl" + jobName + "/"+id +"/api/json", Build.class)).thenReturn(build);
		
		//when
		Build output = testee.getBuild(jobName, id);
		
		//then
		assertThat(output, is(build));
		
	}

}
