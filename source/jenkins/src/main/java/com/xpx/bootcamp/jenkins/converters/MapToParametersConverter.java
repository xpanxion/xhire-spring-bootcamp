package com.xpx.bootcamp.jenkins.converters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.core.convert.converter.Converter;

import com.xpx.bootcamp.jenkins.entity.Parameter;
import com.xpx.bootcamp.jenkins.entity.Parameters;

public class MapToParametersConverter implements Converter<Map<String, String>, Parameters> {

	@Override
	public Parameters convert(Map<String, String> source) {
		List<Parameter> parameterList = new ArrayList<>();

		for(Entry<String, String> entry : source.entrySet()) {
			Parameter parameter = new Parameter();
			parameter.setName(entry.getKey());
			parameter.setValue(entry.getValue());
			parameterList.add(parameter);
		}

		Parameters parameters = new Parameters();
		parameters.setParameter(parameterList);

		return parameters;
	
	}

}
