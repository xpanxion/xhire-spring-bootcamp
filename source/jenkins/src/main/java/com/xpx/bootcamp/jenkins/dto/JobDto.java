package com.xpx.bootcamp.jenkins.dto;

import java.util.List;

/**
 *  Job Data.
 */
public class JobDto {

	/** A list of run builds */
	private List<Integer> numbers;

	/**
	 * Gets the numbers.
	 *
	 * @return the numbers
	 */
	public List<Integer> getNumbers() {
		return numbers;
	}

	/**
	 * Sets the numbers.
	 *
	 * @param numbers the new numbers
	 */
	public void setNumbers(List<Integer> numbers) {
		this.numbers = numbers;
	}
	
	
}
