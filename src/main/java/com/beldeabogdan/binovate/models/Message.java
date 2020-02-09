package com.beldeabogdan.binovate.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    @JsonProperty(
            value = "id",
            access = JsonProperty.Access.READ_ONLY
    )
    private Integer id;

    @Column(name = "content")
    @JsonProperty("content")
    private String content;

    @ManyToOne(targetEntity = GroupChat.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("group_chat")
    private GroupChat groupChat;

    @ManyToOne(targetEntity = User.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("created_by")
    private User createdBy;

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public Integer getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public Message setId(Integer id) {
        this.id = id;
        return this;
    }

    public Message setContent(String content) {
        this.content = content;
        return this;
    }

    public Message setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
        return this;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public Message setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
        return this;
    }
}
