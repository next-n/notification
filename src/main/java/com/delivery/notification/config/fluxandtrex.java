package com.delivery.notification.config;

public class fluxandtrex {
	private Boolean check;
	private String message;
	public fluxandtrex(Boolean check, String message) {
		this.check = check;
		this.message = message;
	}
	public Boolean getCheck() {
		return check;
	}
	public void setCheck(Boolean check) {
		this.check = check;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
