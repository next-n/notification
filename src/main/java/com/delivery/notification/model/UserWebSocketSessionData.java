package com.delivery.notification.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
@Entity
public class UserWebSocketSessionData {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String sessionId;
		public UserWebSocketSessionData(String sessionId, String userId) {
			super();
			this.sessionId = sessionId;
			this.userId = userId;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public UserWebSocketSessionData() {
			super();
		}
		public String getSessionId() {
			return sessionId;
		}
		public void setSessionId(String sessionId) {
			this.sessionId = sessionId;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		private String userId;
		

}


