package com.carledwinti.websocket.chat.controller;

import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.carledwinti.websocket.chat.model.ChatMessage;

@Controller
public class ChatController {

	@SendTo("/topic/public/{cnpj_credor}/{cpf_cnpj}")
	@MessageMapping("/chat.sendMessage/{cnpj_credor}/{cpf_cnpj}")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage, @DestinationVariable Integer cnpj_credor, @DestinationVariable Integer cpf_cnpj) {
		
		return chatMessage;
	}
	
	@SendTo("/topic/public")
	@MessageMapping("/chat.addUser")
	public ChatMessage addUser(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}
}
