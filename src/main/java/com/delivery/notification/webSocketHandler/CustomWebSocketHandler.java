package com.delivery.notification.webSocketHandler;

import java.util.function.Consumer;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.beans.factory.support.MergedBeanDefinitionPostProcessor;
import org.springframework.boot.ApplicationContextFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;

import com.delivery.notification.config.SinkBeans;
import com.delivery.notification.config.WebsocketConfig;
import com.delivery.notification.config.fluxandtrex;
import com.delivery.notification.model.UserWebSocketSessionData;
import com.delivery.notification.service.UserWebSocketSessionDataService;

import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxProcessor;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;
@Configuration
public class CustomWebSocketHandler extends AbstractWebSocketHandler {
	private Sinks.Many<String> sink;
	private final DefaultListableBeanFactory beanFactory;
	private final UserWebSocketSessionDataService userSocketDataService;
	private String ws = "";
	
	public CustomWebSocketHandler (DefaultListableBeanFactory beanFactory, UserWebSocketSessionDataService userSocketDataService) {
		this.userSocketDataService = userSocketDataService;
		this.beanFactory = beanFactory;
		
		
	
//		this.userWebsocketSessionService = s;
	}
		@SuppressWarnings("unchecked")
		@Override
		public Mono<Void> handle(WebSocketSession session) {
			
			
			
			String userId = session.getHandshakeInfo().getHeaders().getFirst("authorization");
//			this.ws = session.getHandshakeInfo().getHeaders().getFirst("type");
			this.ws = session.getId();
			
			sink = (Many<String>) beanFactory.getBean("sink");
			
			beanFactory.registerSingleton(ws, sink);
			userSocketDataService.saveUserSession(new UserWebSocketSessionData(ws, userId));
			
			
//		      sink = (Many<String>) beanFactory.getBean(session.getId());
//			var sink1 = context.getBean("sink1", Many.class);
		      
		      
//			var sink2 = context.getBean("sink2", Many.class);
//			var sink3 = context.getBean("sink3", Many.class);
//			System.out.println(sink1.hashCode() + " " + sink2.hashCode() + " " + sink3.hashCode());
	
			System.out.println("differn? " + userId);
			System.out.println(session.getId());
////			var f = Flux.just("A", "B", "C").map(e -> session.textMessage(e));
//			System.out.println("p" + sink.parents().count());
//			
//			System.out.println("a" + sink.actuals().count());
		Sinks.Many<String> sink2 = (Many<String>) beanFactory.getBean("sinktest");
		
		Flux <WebSocketMessage> f = sink.asFlux().map(p -> session.textMessage(p));
//			var<WebSocketMessage> f2 = sink2.asFlux().map(p -> session.textMessage(p));
//		var finalf = f.mergeWith(f2);
//			f.block
//			var hf = session.getHandshakeInfo();
//			var id = session.getId();
//			System.out.println("handshake everytime?" + hf);
//			System.out.println("session Id everytime?" + id);
//			UserWebSocketSessionData session2 = new UserWebSocketSessionData(session.getId(), id);
//			userWebsocketSessionService.saveUserSession(session2);
		session.receive().doFinally(p-> {
			userSocketDataService.deleteSocketService(session.getId());
		}).subscribe();
			return session.send(f);
			
		}
		
		private void checksystem() {
			 System.out.println(beanFactory.getBean(Sinks.Many.class));
			
		}
}
