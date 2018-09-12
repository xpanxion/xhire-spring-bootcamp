package com.xpx.bootcamp.jenkins.entity;

import java.util.List;

/**
 * The Class Action.
 */
public class Action {

	/** The parameters. */
	List<Parameter> parameters;

	/**
	 * Gets the parameters.
	 *
	 * @return the parameters
	 */
	public List<Parameter> getParameters() {
		return parameters;
	}

	/**
	 * Sets the parameters.
	 *
	 * @param parameters the new parameters
	 */
	public void setParameters(List<Parameter> parameters) {
		this.parameters = parameters;
	}
}
