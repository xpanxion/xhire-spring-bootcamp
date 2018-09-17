package com.xpx.project.cardb.dao;

import org.springframework.data.repository.CrudRepository;

import com.xpx.project.cardb.entity.Car;

public interface CarRepository extends CrudRepository<Car, Long> {

}
