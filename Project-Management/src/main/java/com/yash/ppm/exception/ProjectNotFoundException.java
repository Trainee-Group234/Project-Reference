package com.yash.ppm.exception;

public class ProjectNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProjectNotFoundException(String mssg) {
		super(mssg);
	}
}
