package com.delivery.notification.controller;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.delivery.notification.config.SinkBeans;
import com.delivery.notification.dto.NotiSentDTO;
import com.delivery.notification.dto.maltipoo;
import com.delivery.notification.model.UserWebSocketSessionData;
import com.delivery.notification.repository.UserWebSocketSessionDataRepository;
import com.delivery.notification.service.UserWebSocketSessionDataService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@RestController
public class UserWebSocketSessionDataController {
	private final UserWebSocketSessionDataService webSocketSessionService;
	private Sinks.Many<String> sink;
	private final BeanFactory context;
private final UserWebSocketSessionDataRepository userdata;
	public UserWebSocketSessionDataController(UserWebSocketSessionDataService webSocketSessionService, UserWebSocketSessionDataRepository userdata, BeanFactory beanFactory) {
		this.webSocketSessionService = webSocketSessionService;
		
		this.userdata = userdata;
		this.context = beanFactory;
		
	}
	@SuppressWarnings("unchecked")
	@PostMapping("/demo")
	public void demo(@RequestBody maltipoo noti) {
		webSocketSessionService.sendToEachSession(noti.getRoleList(), context, noti.getContent());
//	Arrays.asList(noti.getUserIdArr()).parallelStream().forEach(p -> webSocketSessionService.sendToEachSession(p, context, noti.getContent()));
		
		
			
//			var ws = socketId;
//			sink = (Many<String>) context.getBean(ws, Many.class);
////			var sink1 = context.getBean("sink1", Many.class);
////			var sink2 = context.getBean("sink2", Many.class);
////			var sink3 = context.getBean("sink3", Many.class);
////			System.out.println(sink1.hashCode() + " " + sink2.hashCode() + " " + sink3.hashCode());
//	
//		System.out.println("contrl " + sink.hashCode());
//		System.out.println(sink.currentSubscriberCount());
//
//		sink.emitNext("hello " + socketId, Sinks.EmitFailureHandler.FAIL_FAST);
	}
	@PostMapping("/test")
	public void test() {
		sink = (Many<String>) context.getBean("sinktest");
		sink.emitNext("atesthelo", Sinks.EmitFailureHandler.FAIL_FAST);
//	Arrays.asList(noti.getUserIdArr()).parallelStream().forEach(p -> webSocketSessionService.sendToEachSession(p, context, noti.getContent()));
//		
	
	}
	

}
