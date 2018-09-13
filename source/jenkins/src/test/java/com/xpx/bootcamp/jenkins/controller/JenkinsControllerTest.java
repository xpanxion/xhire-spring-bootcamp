package com.xpx.bootcamp.jenkins.controller;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.xpx.bootcamp.jenkins.dto.BuildDto;
import com.xpx.bootcamp.jenkins.dto.JenkinsDto;
import com.xpx.bootcamp.jenkins.dto.JobDto;
import com.xpx.bootcamp.jenkins.service.JenkinsService;

@RunWith(MockitoJUnitRunner.class)
public class JenkinsControllerTest {

	@Mock
	private JenkinsService jenkinsService;
	
	@InjectMocks
	private JenkinsController testee;
	
	
	@Test
	public void testBuildParam() {
		//given
		String param = "bob";
		BuildDto buildDto = new BuildDto();
		buildDto.setParam(param);
		String jobName = "jobName";
		
		JenkinsDto dto = new JenkinsDto();
		when(jenkinsService.startParamBuild(param, jobName)).thenReturn(dto);
		
		//when
		JenkinsDto output = testee.buildParam(true, buildDto, jobName);
		
		//then
		assertThat(output, is(dto));
		
	}
	
	@Test
	public void testBuildParam_nullDto() {
		//given
		String jobName = "jobName";
		
		JenkinsDto dto = new JenkinsDto();
		when(jenkinsService.startBuild(jobName)).thenReturn(dto);
		
		//when
		JenkinsDto output = testee.buildParam(true, null, jobName);
		
		//then
		assertThat(output, is(dto));
		
	}

	@Test
	public void testBuildParam_returnFalse() {
		//given
		String param = "bob";
		BuildDto buildDto = new BuildDto();
		buildDto.setParam(param);
		String jobName = "jobName";
		
		JenkinsDto dto = new JenkinsDto();
		when(jenkinsService.startParamBuild(param, jobName)).thenReturn(dto);
		
		//when
		JenkinsDto output = testee.buildParam(false, buildDto, jobName);
		
		//then
		assertThat(output, nullValue());
		
	}
	
	
	@Test
	public void testGetSimpleJob() {
		//given
		JobDto jobDto = new JobDto();
		when(jenkinsService.getJob("Job1")).thenReturn(jobDto);
		
		//when
		JobDto output = testee.getSimpleJob();
		
		//then
		assertThat(output, is(jobDto));
		
	
	}

	@Test
	public void testGetParamJob() {
		//given
		JobDto jobDto = new JobDto();
		when(jenkinsService.getJob("Job2")).thenReturn(jobDto);
		
		//when
		JobDto output = testee.getParamJob();
		
		//then
		assertThat(output, is(jobDto));
		
	}

	@Test
	public void testGetBuild() {
		//given
		BuildDto dto = new BuildDto();
		Integer id = 433;
		when(jenkinsService.getbuild("Job2",id)).thenReturn(dto);
		
		//when
		BuildDto output = testee.getBuild(id);
		
		//then
		assertThat(output, is(dto));
	}

}
