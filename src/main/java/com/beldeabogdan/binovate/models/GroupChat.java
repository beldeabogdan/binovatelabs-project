package com.beldeabogdan.binovate.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "group_chat")
public class GroupChat {

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

    @OneToMany(
            targetEntity = UserGroupChatMapping.class,
            mappedBy = "groupChat",
            cascade = {CascadeType.REMOVE}
    )
    @JsonProperty(
            value = "user_group_chat_mappings",
            access = JsonProperty.Access.READ_ONLY
    )
    private Set<UserGroupChatMapping> userGroupChatMappings = new HashSet<>();

    @OneToMany(
            targetEntity = Message.class,
            mappedBy = "groupChat"
    )
    @JsonIgnore
    private Set<Message> messages = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public GroupChat setId(Integer id) {
        this.id = id;
        return this;
    }

    public Set<UserGroupChatMapping> getUserGroupChatMappings() {
        return userGroupChatMappings;
    }

    public GroupChat setUserGroupChatMappings(Set<UserGroupChatMapping> userGroupChatMappings) {
        this.userGroupChatMappings = userGroupChatMappings;
        return this;
    }

    public Set<Message> getMessages() {
        return messages;
    }

    public GroupChat setMessages(Set<Message> messages) {
        this.messages = messages;
        return this;
    }
}
