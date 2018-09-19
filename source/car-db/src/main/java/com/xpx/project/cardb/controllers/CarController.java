package com.xpx.project.cardb.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.xpx.project.cardb.dao.CarRepository;
import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.service.CarService;

/**
 * Provides rest access to car data
 */
@RestController
@RequestMapping("/api")
public class CarController {
	

	/** The car service. */
	@Autowired
	private CarService carService; 
	
	
	/**
	 * Returns the current list of cars. 
	 *
	 * @return the cars list
	 */
	@RequestMapping(value="/cars", method=RequestMethod.GET)
	public Map<String, List<CarDto>> getCars() {
		Map<String, List<CarDto>> output = new HashMap<>();
		output.put("cars", carService.getCars());
		
		return output;
	}
	
	/**
	 * Gets a specific car based on id
	 *
	 * @param id of the car to get
	 * @return the car with the passed in id
	 */
	@RequestMapping(value="/cars/{carid}", method=RequestMethod.GET)
	public CarDto getCar(@PathVariable(name="carid") Long id) {

		return carService.getCar(id);
	}
	
	
	/**
	 * Adds a car.
	 *
	 * @param input the car to add
	 * @return the car that was added
	 */
	@RequestMapping(value="/cars", method=RequestMethod.POST)
	public CarDto addCar(@Valid @RequestBody CarDto input) {
		
		return carService.addCar(input);
	}
	
	/**
	 * Adds a car.
	 *
	 * @param input the car to add
	 * @return the car that was added
	 */
	@RequestMapping(value="/cars", method=RequestMethod.PUT)
	public CarDto updateCar(@RequestBody CarDto input) {
		
		return carService.updateCar(input);
	}
	
	/**
	 * Deletes a car based on passed in id.
	 *
	 * @param id of the car to delete
	 * @return the deleted object. 
	 */
	@RequestMapping(value="/cars/{carid}", method=RequestMethod.DELETE)
	public CarDto deleteCar(@PathVariable("carid") Long id) {
		return carService.deleteCar(id);
	}
	
	
	

}
