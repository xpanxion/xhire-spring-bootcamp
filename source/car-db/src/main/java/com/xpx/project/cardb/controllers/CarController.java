package com.xpx.project.cardb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpx.project.cardb.dao.CarRepository;
import com.xpx.project.cardb.entity.Car;

@RestController
@RequestMapping("/api")
public class CarController {
	

	@Autowired
	private CarRepository carRepo;
	
	
	@RequestMapping("/test")
	public String test() {
		StringBuilder builder = new StringBuilder();
		Iterable<Car> cars = carRepo.findAll();
		
		for(Car car : cars) {
			builder.append(car.getModel()).append("\n");
		}
		return builder.toString();
	}

}
