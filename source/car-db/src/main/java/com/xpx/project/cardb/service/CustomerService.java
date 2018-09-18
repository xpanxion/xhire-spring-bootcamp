package com.xpx.project.cardb.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.xpx.project.cardb.dao.CustomerRepository;
import com.xpx.project.cardb.dao.OrderRepository;
import com.xpx.project.cardb.dto.CustomerDto;
import com.xpx.project.cardb.entity.Customer;
import com.xpx.project.cardb.entity.Order;

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	@Qualifier("carsConverter")
	private ConversionService conversionService;
	
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private OrderRepository orderRepo; 
	
	
	public List<CustomerDto> getCustomers(){
		List<Customer> custs = customerRepo.findAll();
		return custs.stream().map(it -> conversionService.convert(it, CustomerDto.class)).collect(Collectors.toList());
	}

	public CustomerDto getCustomer(Long id) {
		return conversionService.convert(customerRepo.getOne(id), CustomerDto.class);
	}
	
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Customer cust = conversionService.convert(customerDto, Customer.class	);
		return conversionService.convert(customerRepo.saveAndFlush(cust), CustomerDto.class);
	}
	
	public CustomerDto deleteCustomer(Long id) {
		Customer customer = customerRepo.getOne(id);
		List<Order> orders = orderRepo.findByCustomer(customer);
		orderRepo.deleteAll(orders);
		customerRepo.delete(customer);
		return conversionService.convert(customer, CustomerDto.class);
	}
}
