package com.beldeabogdan.binovate.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddParticipantRequest {

    @JsonProperty("user_id")
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public AddParticipantRequest setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }
}
