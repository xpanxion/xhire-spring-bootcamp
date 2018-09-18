package com.xpx.project.cardb.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.xpx.project.cardb.dao.CarRepository;
import com.xpx.project.cardb.dao.OrderRepository;
import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.entity.Car;
import com.xpx.project.cardb.entity.Order;

@Service
@Transactional
public class CarService {

	@Autowired
	private CarRepository carRepo;
	
	@Autowired
	private OrderRepository orderRepo;
	
	@Autowired
	@Qualifier("carsConverter")
	private ConversionService conversionService;
	
	public List<CarDto> getCars() {
		List<Car> cars = carRepo.findAll();
		
		List<CarDto> dtos = new ArrayList<>();
		for(Car car : cars) {
			dtos.add(conversionService.convert(car, CarDto.class));
		}
		return dtos;
	}
	
	public CarDto getCar(Long id) {
		return conversionService.convert(carRepo.getOne(id), CarDto.class);
	}
	
	public CarDto addCar(CarDto carDto) {
		Car car = conversionService.convert(carDto, Car.class);
		Car savedCar = carRepo.save(car);
		
		return conversionService.convert(savedCar, CarDto.class);
		
	}
	
	public CarDto deleteCar(Long id) {
		Car car = carRepo.getOne(id);
		List<Order> orders = orderRepo.findByCar(car);
		orderRepo.deleteAll(orders);
		carRepo.delete(car);
		return conversionService.convert(car, CarDto.class);
		
	}
	
	
}
