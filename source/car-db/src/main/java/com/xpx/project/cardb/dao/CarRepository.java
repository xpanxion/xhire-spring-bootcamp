package com.xpx.project.cardb.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xpx.project.cardb.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

}
