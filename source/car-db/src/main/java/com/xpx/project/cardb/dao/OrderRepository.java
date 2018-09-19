package com.xpx.project.cardb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpx.project.cardb.entity.Car;
import com.xpx.project.cardb.entity.Customer;
import com.xpx.project.cardb.entity.Order;


/**
 * Repository to access orders
 */
public interface OrderRepository extends JpaRepository<Order, Long> {
	
	/**
	 * Finds all orders with the passed in car
	 *
	 * @param car the car to find orders for
	 * @return the list
	 */
	List<Order> findByCar(Car car);

	/**
	 * Finds all orders for a given customer
	 *
	 * @param customer the customer to get the orders for
	 * @return the list of orders for the customer
	 */
	List<Order> findByCustomer(Customer customer);

}
