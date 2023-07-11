package com.delivery.notification.webSocketHandler;

import java.util.ArrayList;

import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketSession;

import reactor.core.publisher.Mono;

public abstract class AbstractWebSocketHandler implements WebSocketHandler {
	private ArrayList<String> sessionIds = new ArrayList<>();
	private String userType;
	public ArrayList<String> getSessionIds() {
		return sessionIds;
	}

	public void setSessionIds(ArrayList<String> sessionIds) {
		this.sessionIds = sessionIds;
	}

	
	

}
