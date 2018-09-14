package com.xpx.bootcamp.jenkins.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import com.xpx.bootcamp.jenkins.converters.BuildToBuildDTOConverter;
import com.xpx.bootcamp.jenkins.converters.MapToParametersConverter;

@Configuration
public class ConverterConfig {

	@Bean(name="jenkinsConverter")
	public ConversionService conversionService() {
        DefaultConversionService converterService = new DefaultConversionService();
        converterService.addConverter(new MapToParametersConverter());
        converterService.addConverter(new BuildToBuildDTOConverter());
        return converterService;
	}
	
}
