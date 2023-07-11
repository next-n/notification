package com.delivery.notification.dto;

public class NotiSentDTO {
	private String[] userIdArr;
	private String content;
	public NotiSentDTO(String[] userIdArr, String content) {
		super();
		this.userIdArr = userIdArr;
		this.content = content;
	}
	public String[] getUserIdArr() {
		return userIdArr;
	}
	public void setUserIdArr(String[] userIdArr) {
		this.userIdArr = userIdArr;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	
}
