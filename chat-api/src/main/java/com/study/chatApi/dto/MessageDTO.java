package com.study.chatApi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {

    private Integer chatRoomId;
    private Integer userId;
    private String content;
}
