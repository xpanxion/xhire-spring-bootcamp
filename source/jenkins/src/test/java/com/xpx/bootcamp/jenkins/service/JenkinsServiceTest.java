package com.xpx.bootcamp.jenkins.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import com.xpx.bootcamp.jenkins.dao.IJenkinsRestClient;
import com.xpx.bootcamp.jenkins.dto.BuildDto;
import com.xpx.bootcamp.jenkins.dto.JenkinsDto;
import com.xpx.bootcamp.jenkins.dto.JobDto;
import com.xpx.bootcamp.jenkins.entity.Action;
import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Job;
import com.xpx.bootcamp.jenkins.entity.Parameter;

 
@RunWith(MockitoJUnitRunner.class)
public class JenkinsServiceTest extends JenkinsService {

	@Mock
	private IJenkinsRestClient restClient;
	
	@InjectMocks
	private JenkinsService testee;
	
	@Mock
	private ConversionService converter;
	
	@Test
	public void testStartBuild() {
		//given;
		String jobName = "jobName";
		when(restClient.runSimpleBuild(jobName)).thenReturn(true);
		
		//when
		JenkinsDto output = testee.startBuild(jobName);
		
		//then
		assertThat(output.getSuccess(), is(true));
		
	}

	@Test
	public void testStartBuild_simpleFalse() {
		//given
		String jobName = "jobName";
		when(restClient.runSimpleBuild(jobName)).thenReturn(false);
		
		//when
		JenkinsDto output = testee.startBuild(jobName);
		
		//then
		assertThat(output.getSuccess(), is(false));
		
	}

	
	@Test
	public void testStartParamBuild() {
		//given
		String param = "param";
		String jobName = "jobName";
		when(restClient.runParamBuild(param, jobName)).thenReturn(true);
		
		//when
		JenkinsDto output = testee.startParamBuild(param, jobName);
		
		//then
		assertThat(output.getSuccess(), is(true));
		assertThat(output.getValue(), is(param));
		
	}

	@Test
	public void testStartParamBuild_paramFalse() {
		//given
		String param = "param";
		String jobName = "jobName";
		when(restClient.runParamBuild(param, jobName)).thenReturn(false);
		
		//when
		JenkinsDto output = testee.startParamBuild(param, jobName);
		
		//then
		assertThat(output.getSuccess(), is(false));
		assertThat(output.getValue(), is(param));
		
	}
	
	@Test
	public void testGetJob() {
		//given
		String jobName = "job";
		
		Job job = buildJob(5);
		when(restClient.getJob(jobName)).thenReturn(job);
		
		//when
		JobDto output = testee.getJob(jobName);
		
		//then
		assertThat(output.getNumbers(), Matchers.containsInAnyOrder(1,2,3,4,5));
		
	
	}

	@Test
	public void testGetbuild() {
		//given
		String name = "job";
		Integer id = 4;
		Build build = buildBuild(3);
		
		when(restClient.getBuild(name, id)).thenReturn(build);
		
		BuildDto dto = new BuildDto();
		when(converter.convert(build, BuildDto.class)).thenReturn(dto);
		
		//when
		BuildDto output = testee.getbuild(name, id);
		
		//then
		assertThat(output, is(dto));
	}
	
	
	private Job buildJob(int count) {
		Job job = new Job();
		
		List<Build> builds = new ArrayList<>();
		for(int i = 1 ; i <= count; i++) {
			builds.add(buildBuild(i));
		}
		
		job.setBuilds(builds);
		return job;
		
	}
	
	private Build buildBuild(Integer id) {
		Build build = new Build();
		build.setNumber(id);
		
		Parameter param = new Parameter();
		param.setValue("bob");
		List<Parameter> params = new ArrayList<>();
		params.add(param);
		
		Action action = new Action();
		action.setParameters(params);
		
		List<Action> actions = new ArrayList<>();
		actions.add(action);
		build.setActions(actions);
		
		return build;
	}
	

}
