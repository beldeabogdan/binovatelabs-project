package com.beldeabogdan.binovate.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CreateMessageRequest {

    @JsonProperty("group_chat_id")
    private Integer groupChatId;

    @JsonProperty("message")
    private String message;

    public Integer getGroupChatId() {
        return groupChatId;
    }

    public String getMessage() {
        return message;
    }
}
