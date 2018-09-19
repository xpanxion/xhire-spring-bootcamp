package com.xpx.project.cardb.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderDto.
 */
public class OrderDto {

	/** The id. */
	private long id;
	
	/** The customer. */
	@NotNull
	private CustomerDto customer;
	
	/** The car. */
	@NotNull
	private CarDto car;
	
	/** The date. */
	@Past
	private LocalDate date;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the customer.
	 *
	 * @return the customer
	 */
	public CustomerDto getCustomer() {
		return customer;
	}

	/**
	 * Sets the customer.
	 *
	 * @param customer the new customer
	 */
	public void setCustomer(CustomerDto customer) {
		this.customer = customer;
	}

	/**
	 * Gets the car.
	 *
	 * @return the car
	 */
	public CarDto getCar() {
		return car;
	}

	/**
	 * Sets the car.
	 *
	 * @param car the new car
	 */
	public void setCar(CarDto car) {
		this.car = car;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public LocalDate getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
