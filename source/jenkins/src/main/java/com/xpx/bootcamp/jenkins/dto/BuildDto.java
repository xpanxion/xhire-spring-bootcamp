package com.xpx.bootcamp.jenkins.dto;

/**
 * Build Info.
 */
public class BuildDto {

	/** The parameter used in the build. */
	private String param;
	
	/** The build number. */
	private Integer number;

	/**
	 * Gets the param.
	 *
	 * @return the param
	 */
	public String getParam() {
		return param;
	}

	/**
	 * Sets the param.
	 *
	 * @param param the new param
	 */
	public void setParam(String param) {
		this.param = param;
	}

	/**
	 * Gets the number.
	 *
	 * @return the number
	 */
	public Integer getNumber() {
		return number;
	}

	/**
	 * Sets the number.
	 *
	 * @param number the new number
	 */
	public void setNumber(Integer number) {
		this.number = number;
	}
	
}
