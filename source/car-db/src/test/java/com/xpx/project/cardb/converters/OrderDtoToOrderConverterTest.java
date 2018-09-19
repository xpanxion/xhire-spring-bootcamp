package com.xpx.project.cardb.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.xpx.project.cardb.dao.CarRepository;
import com.xpx.project.cardb.dao.CustomerRepository;
import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.dto.CustomerDto;
import com.xpx.project.cardb.dto.OrderDto;
import com.xpx.project.cardb.entity.Car;
import com.xpx.project.cardb.entity.Customer;
import com.xpx.project.cardb.entity.Order;

@RunWith(MockitoJUnitRunner.class)
public class OrderDtoToOrderConverterTest {

	@InjectMocks
	private OrderDtoToOrderConverter testee;
	
	@Mock
	private CarRepository carRepo;
	
	@Mock
	private CustomerRepository customerRepo;
	
	@Test
	public void testConvert() {
		//given
		OrderDto dto = new OrderDto();
		dto.setId(123l);
		dto.setDate(LocalDate.now());
		dto.setCar(getCarDto(222l));
		dto.setCustomer(getCustomerDto(333l));
		
		
		Car car = new Car();
		when(carRepo.getOne(222l)).thenReturn(car);
		
		Customer customer = new Customer();
		when(customerRepo.getOne(333l)).thenReturn(customer);
		
		//when
		Order output = testee.convert(dto);
		
		
		//then
		assertThat(output.getId(), is(123l));
		assertThat(output.getDate(), is(dto.getDate()));
		assertThat(output.getCar(), is(car));
		assertThat(output.getCustomer(), is(customer));
		
	}
	
	private CarDto getCarDto(Long id) {
		CarDto dto = new CarDto();
		dto.setId(id);
		return dto;
	}

	private CustomerDto getCustomerDto(Long id) {
		CustomerDto dto = new CustomerDto();
		dto.setId(id);
		return dto;
	}

}


