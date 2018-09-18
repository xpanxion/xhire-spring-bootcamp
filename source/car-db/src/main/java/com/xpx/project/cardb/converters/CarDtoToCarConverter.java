package com.xpx.project.cardb.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.entity.Car;

@Component
public class CarDtoToCarConverter implements Converter<CarDto, Car> {


	@Override
	public Car convert(CarDto source) {
		Car car = new Car();
		car.setId(source.getId());
		car.setMake(source.getMake());
		car.setModel(source.getModel());
		car.setPrice(source.getPrice());
		return car;
	}

}
