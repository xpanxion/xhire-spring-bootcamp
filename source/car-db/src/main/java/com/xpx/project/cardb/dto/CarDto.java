package com.xpx.project.cardb.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class CarDto {

	private long id;
	
	@NotNull
	private String make;
	
	@NotNull
	private String model;
	
	@Min(1)
	private Double price;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
}
