package com.study.chatApi.controller;

import com.study.chatApi.dto.MessageDTO;
import com.study.chatApi.service.KafkaProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChatController {
    private final KafkaProducerService kafkaProducerService;

    /**
     *
     * EndPoint: ws://localhost:8082/connection
     * Connect Header: accept-version: "1.2", host: "localhost"
     * Producer: /ws/message, MessageDTO: { "chatRoomId": 1, "userId": 111, "content": "hi 111" }
     * Consumer: /topic/chat/chatRoomId1
     */
    @MessageMapping("/message")
    public void sendMessage(@RequestBody MessageDTO message) {
        kafkaProducerService.sendMessage("chat-topic", "chatRoomId" + message.getChatRoomId(), message);
    }
}
