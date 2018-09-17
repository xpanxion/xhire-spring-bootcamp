package com.xpx.project.cardb.dto;

import java.time.LocalDate;

public class OrderDto {

	private long id;
	
	private CustomerDto customer;
	
	private CarDto car;
	
	private LocalDate date;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public CustomerDto getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	public CarDto getCar() {
		return car;
	}

	public void setCar(CarDto car) {
		this.car = car;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
