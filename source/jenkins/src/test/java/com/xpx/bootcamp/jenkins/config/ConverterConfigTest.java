package com.xpx.bootcamp.jenkins.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import com.xpx.bootcamp.jenkins.dto.BuildDto;
import com.xpx.bootcamp.jenkins.entity.Build;
import com.xpx.bootcamp.jenkins.entity.Parameters;

@RunWith(MockitoJUnitRunner.class)
public class ConverterConfigTest {

	@InjectMocks
	private ConverterConfig testee;
	
	@Test
	public void testConversionService() {
		//given
		
		//when
		ConversionService output = testee.conversionService();
		
		//then
		assertThat(output.canConvert(Map.class, Parameters.class), is(true));
		assertThat(output.canConvert(Build.class, BuildDto.class), is(true));
		
		
	
	}

}
