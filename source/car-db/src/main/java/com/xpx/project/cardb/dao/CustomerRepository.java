package com.xpx.project.cardb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.xpx.project.cardb.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
