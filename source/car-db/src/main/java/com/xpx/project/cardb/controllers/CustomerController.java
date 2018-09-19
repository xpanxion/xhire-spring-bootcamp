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

import com.xpx.project.cardb.dto.CustomerDto;
import com.xpx.project.cardb.service.CustomerService;


/**
 * Provides access to the customers via REST .
 */
@RestController
@RequestMapping("/api")
public class CustomerController {

	/** The cust service. */
	@Autowired
	private CustomerService custService;
	
	
	/**
	 * Returns a list of existing customers
	 *
	 * @return the list of customers
	 */
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public Map<String, List<CustomerDto>> getCustomers(){
		Map<String, List<CustomerDto>> output = new HashMap<>();
		output.put("customers", custService.getCustomers());
		return output;
		
	}
	
	/**
	 * Gets a specific customer based on id
	 *
	 * @param id the id of the customer to get
	 * @return the customer witht the passed in id
	 */
	@RequestMapping(value="/customers/{custId}", method=RequestMethod.GET)
	public CustomerDto getCustomer(@PathVariable("custId") Long id) {
		return custService.getCustomer(id);
	}
	
	/**
	 * Adds a new customer 
	 *
	 * @param customerDto the customer to add
	 * @return the added customer
	 */
	@RequestMapping(value="/customers", method=RequestMethod.POST)
	public CustomerDto getCustomer(@Valid @RequestBody CustomerDto customerDto) {
		return custService.addCustomer(customerDto);
	}

	/**
	 * Update an existing customer.
	 *
	 * @param customerDto the customer updated
	 * @return the updated customer
	 */
	@RequestMapping(value="/customers", method=RequestMethod.PUT)
	public CustomerDto updateCustomer(@RequestBody CustomerDto customerDto) {
		return custService.updateCustomer(customerDto);
	}

	
	/**
	 * Deletes a customer based on the id
	 *
	 * @param id the id of the customer to delete
	 * @return the deleted customer
	 */
	@RequestMapping(value="/customers/{custId}", method=RequestMethod.DELETE)
	public CustomerDto deleteCustomer(@PathVariable("custId") Long id) {
		return custService.deleteCustomer(id);
	}
	
	
	
	
}
