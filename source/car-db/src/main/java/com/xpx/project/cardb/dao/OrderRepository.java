package com.xpx.project.cardb.dao;

import org.springframework.data.repository.CrudRepository;

import com.xpx.project.cardb.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
