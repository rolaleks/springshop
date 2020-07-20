package ru.geekbrains.chat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {


    private final AtomicInteger userCounter = new AtomicInteger(1);

    @Bean
    public DefaultHandshakeHandler customHandshakeHandler() {
        return new DefaultHandshakeHandler() {

            @Override
            protected User determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                return new User("User " + userCounter.getAndIncrement());
            }
        };
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/chat_in");
        registry.enableSimpleBroker("/chat_out");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat")
                .setHandshakeHandler(customHandshakeHandler())
                .withSockJS()
                .setClientLibraryUrl("/webjars/sockjs-client/1.0.2/sockjs.min.js");
    }
}
