package com.xpx.project.cardb.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xpx.project.cardb.dao.CarRepository;
import com.xpx.project.cardb.dao.CustomerRepository;
import com.xpx.project.cardb.dto.OrderDto;
import com.xpx.project.cardb.entity.Order;

/**
 * Converts an orderdto to an order
 */
@Component
public class OrderDtoToOrderConverter implements Converter<OrderDto, Order> {

	/** The car repo. */
	@Autowired
	private CarRepository carRepo;
	
	/** The customer repo. */
	@Autowired
	private CustomerRepository customerRepo;
	
	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Order convert(OrderDto source) {

		Order order = new Order();
		order.setId(source.getId());
		order.setDate(source.getDate());
		
		order.setCar(carRepo.getOne(source.getCar().getId()));
		order.setCustomer(customerRepo.getOne(source.getCustomer().getId()));
		
		
		return order;
	}

}
