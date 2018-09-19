package com.xpx.project.cardb.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xpx.project.cardb.dto.CustomerDto;
import com.xpx.project.cardb.entity.Customer;


/**
 * Converts from a CustomerDto to a Customer
 */
@Component
public class CustomerDtoToCustomerConverter implements Converter<CustomerDto, Customer> {

	/* (non-Javadoc)
	 * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
	 */
	@Override
	public Customer convert(CustomerDto source) {
		Customer customer = new Customer();
		customer.setId(source.getId());
		customer.setFirstName(source.getFirst());
		customer.setLastName(source.getLast());
		customer.setPhone(source.getPhone());
		
		return customer;
	}


}
