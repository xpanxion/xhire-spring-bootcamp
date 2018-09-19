package com.xpx.project.cardb.service;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.convert.ConversionService;

import com.xpx.project.cardb.dao.CarRepository;
import com.xpx.project.cardb.dao.OrderRepository;
import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.entity.Car;
import com.xpx.project.cardb.entity.Order;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {
	
	@InjectMocks
	private CarService testee;
	
	@Mock
	private CarRepository carRepo;
	
	@Mock
	private OrderRepository orderRepo;
	
	@Mock
	private ConversionService conversionService;
	

	@Test
	public void testGetCars() {
		//given
		Car car1 = new Car();
		Car car2 = new Car();
		List<Car> cars = new ArrayList<>();
		cars.add(car1);
		cars.add(car2);
		
		when(carRepo.findAll()).thenReturn(cars);
		
		CarDto dto1 = new CarDto();
		CarDto dto2 = new CarDto();
		when(conversionService.convert(car1, CarDto.class)).thenReturn(dto1);
		when(conversionService.convert(car2, CarDto.class)).thenReturn(dto2);
		
		//when
		List<CarDto> output = testee.getCars();
		
		
		//then
		assertThat(output, Matchers.containsInAnyOrder(dto1,dto2));
		
		
	
	}

	@Test
	public void testGetCar() {
		//given
		Long id = 1234l;
		Car car = new Car();
		CarDto dto = new CarDto();
		
		when(carRepo.getOne(id)).thenReturn(car);
		when(conversionService.convert(car, CarDto.class)).thenReturn(dto);
		
		
		//when
		CarDto output = testee.getCar(id);
		
		//then
		assertThat(output, is(dto));
		
	}

	@Test
	public void testAddCar() {
		//given
		CarDto input = new CarDto();
		Car conversionOutput = new Car();
		Car savedCar = new Car();
		CarDto convertedCar = new CarDto();
		
		when(conversionService.convert(input, Car.class)).thenReturn(conversionOutput);
		when(carRepo.save(conversionOutput)).thenReturn(savedCar);
		when(conversionService.convert(savedCar, CarDto.class)).thenReturn(convertedCar);
		
		//when
		CarDto output = testee.addCar(input);
		
		//then
		assertThat(output, is(convertedCar));
		
	}

	@Test
	public void testDeleteCar() {
		//given
		Long id = 123l;
		Car car = new Car();
		when(carRepo.getOne(id)).thenReturn(car);
		
		List<Order> orders = new ArrayList<>();
		when(orderRepo.findByCar(car)).thenReturn(orders);
		
		CarDto dto = new CarDto();
		when(conversionService.convert(car, CarDto.class)).thenReturn(dto);
		
		
		
		//when
		CarDto output = testee.deleteCar(id);
		
		
		//then
		assertThat(output, is(dto));
		verify(orderRepo).deleteAll(orders);
		verify(carRepo).delete(car);
		
		
	}

}
