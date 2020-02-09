package com.beldeabogdan.binovate.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(
        name = "user_group_chat_mapping",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"user_id", "group_chat_id"})
        }
)
public class UserGroupChatMapping {

    @Id
    @GeneratedValue
    @Column(
            name = "id",
            nullable = false,
            unique = true
    )
    private Integer id;

    @ManyToOne(targetEntity = User.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user")
    private User user;

    @ManyToOne(targetEntity = GroupChat.class)
    @JsonIdentityInfo(
            generator = ObjectIdGenerators.PropertyGenerator.class,
            property = "id"
    )
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("group_chat")
    private GroupChat groupChat;

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public UserGroupChatMapping setId(Integer id) {
        this.id = id;
        return this;
    }

    public UserGroupChatMapping setUser(User user) {
        this.user = user;
        return this;
    }

    public UserGroupChatMapping setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
        return this;
    }
}
