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

/**
 * Service to access customer data
 */
@Service
@Transactional
public class CustomerService {
	
	/** The conversion service. */
	@Autowired
	@Qualifier("carsConverter")
	private ConversionService conversionService;
	
	/** The customer repo. */
	@Autowired
	private CustomerRepository customerRepo;
	
	/** The order repo. */
	@Autowired
	private OrderRepository orderRepo; 
	
	
	/**
	 * Gets all of the current customers
	 *
	 * @return the list of customers
	 */
	public List<CustomerDto> getCustomers(){
		List<Customer> custs = customerRepo.findAll();
		return custs.stream().map(it -> conversionService.convert(it, CustomerDto.class)).collect(Collectors.toList());
	}

	/**
	 * Gets a specific customer with the given id
	 *
	 * @param id the id of the customer to get
	 * @return the customer to get 
	 */
	public CustomerDto getCustomer(Long id) {
		return conversionService.convert(customerRepo.getOne(id), CustomerDto.class);
	}
	
	/**
	 * Adds a customer to the database
	 *
	 * @param customerDto the new customer to add
	 * @return the created customer
	 */
	public CustomerDto addCustomer(CustomerDto customerDto) {
		Customer cust = conversionService.convert(customerDto, Customer.class	);
		return conversionService.convert(customerRepo.saveAndFlush(cust), CustomerDto.class);
	}
	
	/**
	 * Deletes a customer with the given id 
	 * Will remove all associated orders first.
	 *
	 * @param id the id of the customer to delete
	 * @return the deleted customer
	 */
	public CustomerDto deleteCustomer(Long id) {
		Customer customer = customerRepo.getOne(id);
		List<Order> orders = orderRepo.findByCustomer(customer);
		orderRepo.deleteAll(orders);
		customerRepo.delete(customer);
		return conversionService.convert(customer, CustomerDto.class);
	}
	
	/**
	 * Updates a customer
	 *
	 * @param update the customer to update
	 * @return the updated customer
	 */
	public CustomerDto updateCustomer(CustomerDto update) {
		Customer customer = conversionService.convert(update, Customer.class);
		Customer current = customerRepo.getOne(update.getId());
		customer = updateValuesIfNotNull(customer, current);
		
		customer = customerRepo.saveAndFlush(customer);
		
		return conversionService.convert(customer, CustomerDto.class);
				
		
		
	}
	
	private Customer updateValuesIfNotNull(Customer from, Customer to) {
		if(from.getFirstName() != null) {
			to.setFirstName(from.getFirstName());
		}
		
		if(from.getLastName() != null) {
			to.setLastName(from.getLastName());
		}

		if(from.getPhone() != null) {
			to.setPhone(from.getPhone());
		}
		return to;
	}
}
