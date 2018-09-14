package com.xpx.bootcamp.jenkins.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import com.xpx.bootcamp.jenkins.entity.Parameters;


@RunWith(MockitoJUnitRunner.class)
public class MapToParametersConverterTest {

	@InjectMocks
	private MapToParametersConverter testee;
	
	@Test
	public void testConvert() {
		//given
		Map<String, String> input = new HashMap<>();
		input.put("key", "val");
		
		
		//when
		Parameters output = testee.convert(input);
		
		
		//then
		assertThat(output.getParameter().size(), is(1));
		assertThat(output.getParameter().get(0).getName(), is("key"));
		assertThat(output.getParameter().get(0).getValue(), is("val"));		
	}

}
