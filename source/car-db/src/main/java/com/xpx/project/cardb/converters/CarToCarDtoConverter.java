package com.xpx.project.cardb.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.entity.Car;

@Component
public class CarToCarDtoConverter implements Converter<Car, CarDto> {

	@Override
	public CarDto convert(Car source) {
		CarDto dto = new CarDto();
		dto.setId(source.getId());
		dto.setMake(source.getMake());
		dto.setModel(source.getModel());
		dto.setPrice(source.getPrice());
		
		return dto;
	}

}
