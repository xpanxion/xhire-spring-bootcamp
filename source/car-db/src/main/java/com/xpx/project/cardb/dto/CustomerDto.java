package com.xpx.project.cardb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CustomerDto {

	private long id;
	
	@NotNull
	private String first;
	
	@NotNull
	private String last;

	@Pattern(regexp="[0-9]{3}-[0-9]{3}-[0-9]{4}")
	private String phone;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirst() {
		return first;
	}

	public void setFirst(String first) {
		this.first = first;
	}

	public String getLast() {
		return last;
	}

	public void setLast(String last) {
		this.last = last;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
