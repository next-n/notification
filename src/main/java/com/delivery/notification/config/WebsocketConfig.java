package com.delivery.notification.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.handler.SimpleUrlHandlerMapping;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.server.MethodNotAllowedException;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

@Configuration
public class WebsocketConfig {
	private final WebSocketService customWebSockeService;
	public WebsocketConfig(WebSocketService webSocketService) {
		this.customWebSockeService = webSocketService;
		
	}
	@Bean
	public SimpleUrlHandlerMapping handlerMapping(WebSocketHandler wsh) {
		Map<String, WebSocketHandler> urlMap = new HashMap<>();
		urlMap.put("/ws/messages", wsh);
		return new SimpleUrlHandlerMapping(Map.of("/ws/messages", wsh), 1);
		
	}
	@Bean
	public WebSocketHandlerAdapter webSocketHandlerAdapter() {
		return new WebSocketHandlerAdapter(customWebSockeService);
	}
	
	
	

}
