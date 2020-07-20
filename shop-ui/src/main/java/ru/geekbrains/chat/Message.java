package ru.geekbrains.chat;

import java.time.LocalDateTime;

public class Message {

    private String name;

    private String receiver;

    private String message;

    private LocalDateTime localDateTime;

    public Message() {
        this.localDateTime = LocalDateTime.now();
    }

    public Message(String name, String message) {
        this();
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "username='" + name + '\'' +
                ", message='" + message + '\'' +
                ", receiver='" + receiver + '\'' +
                '}';
    }
}
