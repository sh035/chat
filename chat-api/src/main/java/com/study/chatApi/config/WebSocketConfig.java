package com.study.chatApi.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/connection") // 웹소켓 엔드포인트
                .setAllowedOrigins("*") // CORS 설정
                .withSockJS();
        registry.addEndpoint("/connection")
                .setAllowedOriginPatterns("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        // 클라이언트가 메세지를 서버로 전송할 때 사용하는 prefix
        registry.setApplicationDestinationPrefixes("/ws");
        // 클라이언트가 메세지를 받을 때 사용하는 prefix
        registry.enableSimpleBroker("/topic/chat");
    }
}
