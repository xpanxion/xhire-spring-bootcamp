package com.xpx.project.cardb.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xpx.project.cardb.dao.CarRepository;
import com.xpx.project.cardb.dao.OrderRepository;
import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.entity.Car;
import com.xpx.project.cardb.entity.Order;

/**
 * Service to access car data.
 */
@Service
@Transactional
public class CarService {

	/** The car repo. */
	@Autowired
	private CarRepository carRepo;
	
	/** The order repo. */
	@Autowired
	private OrderRepository orderRepo;
	
	/** The conversion service. */
	@Autowired
	@Qualifier("carsConverter")
	private ConversionService conversionService;
	
	/**
	 * Gets all of the current available cars.
	 *
	 * @return all of the cars
	 */
	public List<CarDto> getCars() {
		List<Car> cars = carRepo.findAll();
		
		List<CarDto> dtos = new ArrayList<>();
		for(Car car : cars) {
			dtos.add(conversionService.convert(car, CarDto.class));
		}
		return dtos;
	}
	
	/**
	 * Gets a car based on an id
	 *
	 * @param id the id of the car
	 * @return the car with the passed in id
	 */
	public CarDto getCar(Long id) {
		return conversionService.convert(carRepo.getOne(id), CarDto.class);
	}
	
	/**
	 * Adds the car to the database
	 *
	 * @param carDto the car to add 
	 * @return the newly added car
	 */
	public CarDto addCar(CarDto carDto) {
		Car car = conversionService.convert(carDto, Car.class);
		Car savedCar = carRepo.save(car);
		
		return conversionService.convert(savedCar, CarDto.class);
		
	}
	
	/**
	 * Deletes the car with the given id
	 * Will remove all associated orders first.
	 *
	 * @param id the id of the car to delete
	 * @return the car that had been deleted
	 */
	public CarDto deleteCar(Long id) {
		Car car = carRepo.getOne(id);
		List<Order> orders = orderRepo.findByCar(car);
		orderRepo.deleteAll(orders);
		carRepo.delete(car);
		return conversionService.convert(car, CarDto.class);
		
	}
	
	/**
	 * Updated a car.
	 *
	 * @param carDto the car updates
	 * @return the updated cars
	 */
	public CarDto updateCar(CarDto carDto) {
		Car car = conversionService.convert(carDto, Car.class);
		Car existingCar = carRepo.getOne(carDto.getId());
		car = updateNonNullValues(car, existingCar);
		
		
		Car savedCar = carRepo.saveAndFlush(car);
		return(conversionService.convert(savedCar, CarDto.class));
	}
	
	/**
	 * Update non null values.
	 *
	 * @param from car to update from
	 * @param to the existing car to update
	 * @return the car with updates
	 */
	private Car updateNonNullValues(Car from, Car to) {
		if(from.getMake() != null) {
			to.setMake(from.getMake());
		}
	
		if(from.getModel() != null) {
			to.setModel(from.getModel());
		}

		if(from.getPrice() != null) {
			to.setPrice(from.getPrice());
		}
		return to;
	}
	
	
}
