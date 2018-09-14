package com.xpx.bootcamp.jenkins.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.xpx.bootcamp.jenkins.dto.BuildDto;
import com.xpx.bootcamp.jenkins.entity.Action;
import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Parameter;

@RunWith(MockitoJUnitRunner.class)
public class BuildToBuildDTOConverterTest {

	@InjectMocks
	private BuildToBuildDTOConverter converter;
	
	@Test
	public void testConvert() {
		//given
		Build build = buildBuild(3, "Doug");
		
		//when
		BuildDto output = converter.convert(build);
		
		//then
		assertThat(output.getNumber(), is(3));
		assertThat(output.getParam(), is("Doug"));
		
		
	}

	@Test
	public void testConvert_paramsEmpty() {
		//given
		Build build = buildBuild(3, "Doug");
		build.getActions().get(0).getParameters().clear();
		
		//when
		BuildDto output = converter.convert(build);
		
		//then
		assertThat(output.getNumber(), is(3));
		assertThat(output.getParam(), nullValue());
		
		
	}

	@Test
	public void testConvert_paramsNull() {
		//given
		Build build = buildBuild(3, "Doug");
		build.getActions().get(0).setParameters(null);
		
		//when
		BuildDto output = converter.convert(build);
		
		//then
		assertThat(output.getNumber(), is(3));
		assertThat(output.getParam(), nullValue());
		
		
	}	

	@Test
	public void testConvert_actionsEmpty() {
		//given
		Build build = buildBuild(3, "Doug");
		build.getActions().clear();
		
		//when
		BuildDto output = converter.convert(build);
		
		//then
		assertThat(output.getNumber(), is(3));
		assertThat(output.getParam(), nullValue());
	}	

	@Test
	public void testConvert_actionsNull() {
		//given
		Build build = buildBuild(3, "Doug");
		build.setActions(null);
		
		//when
		BuildDto output = converter.convert(build);
		
		//then
		assertThat(output.getNumber(), is(3));
		assertThat(output.getParam(), nullValue());
	}	

	
	private Build buildBuild(Integer id, String value) {
		Build build = new Build();
		build.setNumber(id);
		
		Parameter param = new Parameter();
		param.setValue(value);
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
