package com.carledwinti.websocket.chat.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import com.carledwinti.websocket.chat.model.ChatMessage;

@Component
public class WebSocketEventListener {

	private static final Logger logger = LoggerFactory.getLogger(WebSocketEventListener.class);
	
	@Autowired
	private SimpMessageSendingOperations simpMessageSendingOperations;
	
	@EventListener
	public void handleWebSocketConnectListener(SessionConnectedEvent sessionConnectedEvent) {
		
		logger.info("Received a new web socket connection....");
	}
	
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent sessionDisconnectEvent) {
		
		StompHeaderAccessor stompHeaderAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.getMessage());
		
		String username = (String) stompHeaderAccessor.getSessionAttributes().get("username");
		
		if(username != null) {
			
			logger.info("User disconnected: " + username);
			
			ChatMessage chatMessage = new ChatMessage();
			chatMessage.setType(ChatMessage.MessageTypeEnum.LEAVE);
			chatMessage.setSender(username);
			
			simpMessageSendingOperations.convertAndSend("/topic/public", chatMessage);
		}
	}
}
