package com.carledwinti.websocket.chat.model;

public class ChatMessage {

	private MessageTypeEnum type;
	private String content;
	private String sender;
	
	public enum MessageTypeEnum{
		CHAT, JOIN, LEAVE;
	}

	public MessageTypeEnum getType() {
		return type;
	}

	public void setType(MessageTypeEnum type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}
}
