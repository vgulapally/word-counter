package com.gvk.wc.controller.advice;

public class ErrorMessage {

	private final int status;
	private final String message;
	private final String description;

	public ErrorMessage(int status, String message, String description) {
		this.status = status;
		this.message = message;
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public String getDescription() {
		return description;
	}
	
}
