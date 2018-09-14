package com.xpx.bootcamp.jenkins.converters;

import org.springframework.core.convert.converter.Converter;

import com.xpx.bootcamp.jenkins.dto.BuildDto;
import com.xpx.bootcamp.jenkins.entity.Action;
import com.xpx.bootcamp.jenkins.entity.Build;

public class BuildToBuildDTOConverter implements Converter<Build, BuildDto> {

	@Override
	public BuildDto convert(Build source) {

		BuildDto dto = new BuildDto();
		dto.setNumber(source.getNumber());
		if(source.getActions() != null && !source.getActions().isEmpty()) {
			Action action = source.getActions().get(0);
			if(action.getParameters() != null && !action.getParameters().isEmpty()) {
				dto.setParam(action.getParameters().get(0).getValue());
			}
		}
		
		return dto;
	}

}
