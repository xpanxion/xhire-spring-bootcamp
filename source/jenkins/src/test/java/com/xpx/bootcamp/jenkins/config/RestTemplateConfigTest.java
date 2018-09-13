package com.xpx.bootcamp.jenkins.config;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;


@RunWith(MockitoJUnitRunner.class)
public class RestTemplateConfigTest {

	@InjectMocks
	private RestTemplateConfig testee;
	
	@Mock
	private RestTemplateBuilder builder;
	
	@Before
	public void setup() {
		ReflectionTestUtils.setField(testee, "user", "bob");
		ReflectionTestUtils.setField(testee, "password", "burger");
	}
	
	@Test
	public void testJenkinsRestTemplate() {
		//Given
		RestTemplate template = new RestTemplate();
		
		when(builder.basicAuthorization("bob", "burger")).thenReturn(builder);
		when(builder.build()).thenReturn(template);
		
		//When
		RestTemplate output = testee.jenkinsRestTemplate(builder);
		
		//Then 
				
		assertThat(output, is(template));
	}

}
