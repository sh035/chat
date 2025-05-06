package com.study.chatApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.chatApi.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor_ = { @Autowired})
@Slf4j
public class KafkaProducerService {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String topic, String key, MessageDTO message) {
        try {
            String msg = new ObjectMapper().writeValueAsString(message);
            kafkaTemplate.send(topic, key, msg);
            log.info("토픽으로 메시지를 전송함, 토픽: {}, 키: {} 메시지: {}", topic, key, msg);

        } catch (JsonProcessingException e) {
            log.error("메시지 직렬화 실패: {}", e.getMessage(), e);
            throw new RuntimeException("메시지 직렬화 실패", e);
        }
    }
}
