package com.xpx.project.cardb.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xpx.project.cardb.dto.CarDto;
import com.xpx.project.cardb.dto.CustomerDto;
import com.xpx.project.cardb.dto.OrderDto;
import com.xpx.project.cardb.entity.Order;


// TODO: Auto-generated Javadoc
/**
 * Converters an order to an order dto
 */
@Component
public class OrderToOrderDtoConverter implements Converter<Order, OrderDto> {

	/** The car converter. */
	@Autowired
	private CarToCarDtoConverter carConverter;

	/** The customer converter. */
	@Autowired
	private CustomerToCustomerDtoConverter customerConverter;
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public OrderDto convert(Order source) {
		OrderDto orderDto = new OrderDto();
		orderDto.setId(source.getId());
		orderDto.setDate(source.getDate());
		
		orderDto.setCar(carConverter.convert(source.getCar()));
		orderDto.setCustomer(customerConverter.convert(source.getCustomer()));

		return orderDto;
	}


}
