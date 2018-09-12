package com.xpx.bootcamp.jenkins.entity;

import java.util.List;

/**
 * The Class Build.
 */
public class Build {

	/** The number. */
	private Integer number;
	
	/** The actions. */
	private List<Action> actions;

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

	/**
	 * Gets the actions.
	 *
	 * @return the actions
	 */
	public List<Action> getActions() {
		return actions;
	}

	/**
	 * Sets the actions.
	 *
	 * @param actions the new actions
	 */
	public void setActions(List<Action> actions) {
		this.actions = actions;
	}
}
