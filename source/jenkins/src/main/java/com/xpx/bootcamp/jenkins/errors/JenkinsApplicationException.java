package com.xpx.bootcamp.jenkins.errors;

public class JenkinsApplicationException extends RuntimeException {

	public JenkinsApplicationException(String message, Throwable error) {
		super(message, error);
	}
	
	
	
}
