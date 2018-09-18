package com.xpx.project.cardb.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import com.xpx.project.cardb.dao.OrderRepository;
import com.xpx.project.cardb.dto.OrderDto;
import com.xpx.project.cardb.entity.Order;

@Service
@Transactional
public class OrderService {

	@Autowired
	@Qualifier("carsConverter")
	private ConversionService converter;
	
	@Autowired
	private OrderRepository orderRepo; 
	
	public List<OrderDto> getOrders(){
		List<Order> orders = orderRepo.findAll();
		
		List<OrderDto> dtos = new ArrayList<>();
		for(Order order : orders) {
			dtos.add(converter.convert(order, OrderDto.class));
		}
		
		return dtos;
		
		
	}
	
	public OrderDto getOrder(Long id) {
		return converter.convert(orderRepo.getOne(id), OrderDto.class);
	}

	public OrderDto addOrder(OrderDto orderdto) {
		Order order = converter.convert(orderdto, Order.class);
		Order savedOrder = orderRepo.saveAndFlush(order);
		return converter.convert(savedOrder, OrderDto.class);
	
	}
	
	public OrderDto deleteOrder(Long id) {
		Order order = orderRepo.getOne(id);
		orderRepo.delete(order);
		return converter.convert(order, OrderDto.class);
	}
	
}
