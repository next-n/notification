package com.delivery.notification.service;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.delivery.notification.dto.maltipoo;
import com.delivery.notification.model.UserWebSocketSessionData;
import com.delivery.notification.repository.UserWebSocketSessionDataRepository;

import jakarta.transaction.Transactional;
import reactor.core.publisher.Sinks;

@Service
public class UserWebSocketSessionDataService {
	private final UserWebSocketSessionDataRepository webSocketSession;
	private final RestTemplate restTemplate;
	public UserWebSocketSessionDataService(UserWebSocketSessionDataRepository webSocketSession, RestTemplate restTemplate) {
		this.webSocketSession = webSocketSession;
		this.restTemplate = restTemplate;
	}
	public void saveUserSession(UserWebSocketSessionData sessionData) {
		webSocketSession.save(sessionData);
	}
	public void sendToEachSession(String[] roleList, BeanFactory context, String content) {
		String uri = "http://localhost:5000/employee/lostInmaltipoo";
		
		ResponseEntity<String[]> ids = restTemplate.postForEntity(URI.create(uri),new maltipoo(roleList), String[].class);
		System.out.println("why " + ids);
//		ResponseEntity<String[]> ids = restTemplate.getForEntity(, String[].class);
		Arrays.asList(ids.getBody()).parallelStream().forEach(userId -> {
			System.out.println(userId);
			String sessionId = webSocketSession.findSessionByuserId(userId).get().getSessionId();
			try {
				System.out.println("sess" + sessionId);
			Sinks.Many<String> sink = context.getBean(sessionId, Sinks.Many.class);
			sink.emitNext(content, Sinks.EmitFailureHandler.FAIL_FAST);
			}catch(Exception e) {
				System.out.println("error" + userId);
			}
			});
	}
	@Transactional
	public void deleteSocketService(String userId) {
		webSocketSession.deleteBySessionId(userId);
	}
}
