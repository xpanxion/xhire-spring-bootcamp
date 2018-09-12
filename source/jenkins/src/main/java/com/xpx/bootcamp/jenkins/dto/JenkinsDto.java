package com.xpx.bootcamp.jenkins.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


/**
 * The Class JenkinsDto.
 */
@JsonInclude(Include.NON_NULL)
public class JenkinsDto {

	/** Indicates if the request was successful. */
	private Boolean success;
	
	/** The value that was passed in for a parameter. */
	private String value;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Gets the success.
	 *
	 * @return the success
	 */
	public Boolean getSuccess() {
		return success;
	}

	/**
	 * Sets the success.
	 *
	 * @param success the new success
	 */
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
}
