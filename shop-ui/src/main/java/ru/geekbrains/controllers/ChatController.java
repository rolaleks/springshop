package ru.geekbrains.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageType;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import ru.geekbrains.chat.Message;

public class ChatController {


    private final SimpMessagingTemplate template;

    @Autowired
    public ChatController(SimpMessagingTemplate template) {
        this.template = template;
    }

    @MessageMapping("/send_message")
    public void messageReceiver(@Payload Message message, SimpMessageHeaderAccessor headerAccessor) {
        if (headerAccessor.getUser() == null) {
            return;
        }
        if (headerAccessor.getUser().getName().equalsIgnoreCase("admin")) {
            template.convertAndSendToUser(message.getReceiver(), "/chat_out/receive_message",
                    new Message("Менеджер", message.getMessage()),
                    createHeaders(headerAccessor.getSessionId()));
        } else {
            template.convertAndSendToUser("admin", "/chat_out/receive_message",
                    new Message(headerAccessor.getUser().getName(), message.getMessage()),
                    createHeaders(headerAccessor.getSessionId()));
        }
    }

    private MessageHeaders createHeaders(String sessionId) {
        SimpMessageHeaderAccessor headerAccessor = SimpMessageHeaderAccessor.create(SimpMessageType.MESSAGE);
        headerAccessor.setSessionId(sessionId);
        headerAccessor.setLeaveMutable(true);
        return headerAccessor.getMessageHeaders();
    }
}
