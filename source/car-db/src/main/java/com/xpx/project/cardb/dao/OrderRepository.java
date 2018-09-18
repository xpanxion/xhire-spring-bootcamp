package com.xpx.project.cardb.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpx.project.cardb.entity.Car;
import com.xpx.project.cardb.entity.Customer;
import com.xpx.project.cardb.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
	
	List<Order> findByCar(Car car);

	List<Order> findByCustomer(Customer customer);

}
