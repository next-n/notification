package com.delivery.notification.config;

import java.util.Collection;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.server.WebSocketService;
import org.springframework.web.reactive.socket.server.support.HandshakeWebSocketService;
import org.springframework.web.server.ServerWebExchange;

import com.delivery.notification.repository.UserWebSocketSessionDataRepository;
import com.delivery.notification.webSocketHandler.CustomWebSocketHandler;

import reactor.core.publisher.Mono;
@Configuration
public class CustomWebSockeService extends HandshakeWebSocketService{
private final UserWebSocketSessionDataRepository userdata;

	public CustomWebSockeService(UserWebSocketSessionDataRepository userdata) {
	super();
	this.userdata = userdata;
}

	@Override
	public Mono<Void> handleRequest(ServerWebExchange exchange, WebSocketHandler handler) {
		CustomWebSocketHandler cwsh = (CustomWebSocketHandler) handler;
		
//		System.out.println("customservice" + handler.hashCode());
		var a = userdata.findAll();
//		System.out.println("userdata" + a);
		var id = exchange.getRequest().getHeaders().getFirst("Authorization");
		
		// TODO Auto-generated method stub
		return super.handleRequest(exchange, handler);
	}

	@Override
	public void stop() {
		System.out.println("stopping now");
		// TODO Auto-generated method stub
		super.stop();
	}
	

	

}
