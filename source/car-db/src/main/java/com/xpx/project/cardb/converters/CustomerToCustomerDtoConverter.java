package com.xpx.project.cardb.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.xpx.project.cardb.dto.CustomerDto;
import com.xpx.project.cardb.entity.Customer;

@Component
public class CustomerToCustomerDtoConverter implements Converter<Customer, CustomerDto> {

	@Override
	public CustomerDto convert(Customer source) {
		CustomerDto dto = new CustomerDto();
		dto.setId(source.getId());
		dto.setFirst(source.getFirstName());
		dto.setLast(source.getLastName());
		dto.setPhone(source.getPhone());
		
		return dto;
	}

}
