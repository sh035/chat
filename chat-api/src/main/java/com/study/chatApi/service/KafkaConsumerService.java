package com.study.chatApi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.study.chatApi.dto.MessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerService {

    private final SimpMessagingTemplate messagingTemplate;
    /**
     *
     * @param record
     * KafkaListener 메서드 파라미터로 메시지와 Key 를 분리해서 읽을 수 없어서
     * Spring kafka 가 제공해주는 ConsumerRecord 를 활용하면 메시지와 Key 를 동시에 처리할 수 있다.
     */
    @KafkaListener(topics = "chat-topic", groupId = "consumer-group-1")
    public void consume(ConsumerRecord<String, String> record) {
        String key = record.key();
        String message = record.value();
        log.info("메시지 수신 - Key: {}, 메시지: {}", key, message);

        MessageDTO dto = parseMessage(message);

        messagingTemplate.convertAndSend("/topic/chat/" + key, dto);
        log.info("구독자들에게 메시지 전달 완료");
    }

    private MessageDTO parseMessage(String message) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(message, MessageDTO.class);
        } catch (Exception e) {
            log.error("메시지 역직렬화 실패 - 메시지: {}", message, e);
            return null;
        }
    }
}
