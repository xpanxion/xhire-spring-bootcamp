package com.xpx.project.cardb.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

// TODO: Auto-generated Javadoc
/**
 * The Class CustomerDto.
 */
public class CustomerDto {

	/** The id. */
	private long id;
	
	/** The first. */
	@NotNull
	private String first;
	
	/** The last. */
	@NotNull
	private String last;

	/** The phone. */
	@Pattern(regexp="[0-9]{3}-[0-9]{3}-[0-9]{4}")
	private String phone;

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
	 * Gets the first.
	 *
	 * @return the first
	 */
	public String getFirst() {
		return first;
	}

	/**
	 * Sets the first.
	 *
	 * @param first the new first
	 */
	public void setFirst(String first) {
		this.first = first;
	}

	/**
	 * Gets the last.
	 *
	 * @return the last
	 */
	public String getLast() {
		return last;
	}

	/**
	 * Sets the last.
	 *
	 * @param last the new last
	 */
	public void setLast(String last) {
		this.last = last;
	}

	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	
}
