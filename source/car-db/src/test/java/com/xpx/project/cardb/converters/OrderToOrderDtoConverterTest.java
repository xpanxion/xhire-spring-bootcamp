package com.xpx.project.cardb.converters;

import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.dto.CustomerDto;
import com.xpx.project.cardb.dto.OrderDto;
import com.xpx.project.cardb.entity.Car;
import com.xpx.project.cardb.entity.Customer;
import com.xpx.project.cardb.entity.Order;


@RunWith(MockitoJUnitRunner.class)
public class OrderToOrderDtoConverterTest {

	@Mock
	private CarToCarDtoConverter carConverter;
	
	@Mock
	private CustomerToCustomerDtoConverter customerConverter;
	
	@InjectMocks
	private OrderToOrderDtoConverter testee;
	
	@Test
	public void testConvert() {
		//Given
		Order order = new Order();
		order.setId(123l);
		order.setDate(LocalDate.now());
		
		Car car = new Car();
		order.setCar( car);
		CarDto carDto = new CarDto();
		when(carConverter.convert(car)).thenReturn(carDto);
		
		Customer customer = new Customer();
		order.setCustomer(customer);
		CustomerDto customerDto = new CustomerDto();
		when(customerConverter.convert(customer)).thenReturn(customerDto);
		
		
		
		//When
		OrderDto output = testee.convert(order);
		
		//Then
		assertThat(output.getId(), is(123l));
		assertThat(output.getDate(), is(order.getDate()));
		assertThat(output.getCustomer(), is(customerDto));
		assertThat(output.getCar(), is(carDto));
		
	
	}

}
