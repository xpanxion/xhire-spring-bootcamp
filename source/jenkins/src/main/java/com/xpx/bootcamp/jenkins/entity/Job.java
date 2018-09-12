package com.xpx.bootcamp.jenkins.entity;

import java.util.List;

/**
 * The Class Job.
 */
public class Job {

	/**
	 * Gets the builds.
	 *
	 * @return the builds
	 */
	public List<Build> getBuilds() {
		return builds;
	}

	/**
	 * Sets the builds.
	 *
	 * @param builds the new builds
	 */
	public void setBuilds(List<Build> builds) {
		this.builds = builds;
	}

	/** The builds. */
	private List<Build> builds;
	
}
