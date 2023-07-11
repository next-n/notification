package com.delivery.notification.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.delivery.notification.model.UserWebSocketSessionData;

public interface UserWebSocketSessionDataRepository extends JpaRepository<UserWebSocketSessionData, String> {
	Optional<UserWebSocketSessionData> findSessionByuserId(String userId);
	Long deleteBySessionId(String sessionId);
}
