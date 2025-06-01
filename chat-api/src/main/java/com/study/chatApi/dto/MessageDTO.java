package com.study.chatApi.dto;

import com.study.chatApi.entity.BaseTime;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class MessageDTO extends BaseTime {

    private Integer chatRoomId;
    private Integer userId;
    private String content;
}
