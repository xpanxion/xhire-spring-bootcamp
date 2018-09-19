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

import com.xpx.project.cardb.dto.OrderDto;
import com.xpx.project.cardb.service.OrderService;

/**
 * Provides access to orders via rest service
 */
@RestController
@RequestMapping("/api")
public class OrderController {

	/** The order service. */
	@Autowired
	private OrderService orderService;
	
	/**
	 * Gets a list of the existing orders.
	 *
	 * @return all of the orders
	 */
	@RequestMapping(value="/orders", method=RequestMethod.GET)
	public Map<String, List<OrderDto>> getOrders(){
		Map<String, List<OrderDto>> output = new HashMap<>();
		output.put("orders", orderService.getOrders());
		return output;
	}
	
	/**
	 * Gets an order based on an id.
	 *
	 * @param id the id of the order
	 * @return the order of the passed in id
	 */
	@RequestMapping(value="/orders/{orderid}", method=RequestMethod.GET )
	public OrderDto getOrder(@PathVariable("orderid") Long id) {
		return orderService.getOrder(id);
	}

	/**
	 * Adds an order.
	 *
	 * @param input the order to add
	 * @return the added order 
	 */
	@RequestMapping(value="/orders", method=RequestMethod.POST) 
	public OrderDto addOrder(@Valid @RequestBody OrderDto input) {
			return orderService.addOrder(input);
	}
	
	/**
	 * Deletes an order.
	 *
	 * @param id the id of the order to delete
	 * @return the order that was deleted
	 */
	@RequestMapping(value="/orders/{orderid}", method=RequestMethod.DELETE)
	public OrderDto deleteOrder(@PathVariable("orderid") Long id) {
		return orderService.deleteOrder(id);
	}
}
