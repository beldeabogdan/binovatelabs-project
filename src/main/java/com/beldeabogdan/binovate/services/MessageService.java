package com.beldeabogdan.binovate.services;

import com.beldeabogdan.binovate.factories.ErrorResponseFactory;
import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.Message;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.models.UserGroupChatMapping;
import com.beldeabogdan.binovate.repositories.MessageRepository;
import com.beldeabogdan.binovate.repositories.UserGroupChatMappingRepository;
import com.beldeabogdan.binovate.requests.CreateMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class MessageService {

    private final UserGroupChatMappingRepository userGroupChatMappingRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(UserGroupChatMappingRepository userGroupChatMappingRepository, MessageRepository messageRepository) {
        this.userGroupChatMappingRepository = userGroupChatMappingRepository;
        this.messageRepository = messageRepository;
    }

    public Message createMessage(CreateMessageRequest request, User user, GroupChat groupChat) {
        UserGroupChatMapping userGroupChatMapping;
        try {
            userGroupChatMapping = userGroupChatMappingRepository.findByUserAndGroupChat(user.getId(), groupChat.getId());
        } catch (NoSuchElementException e) {
            throw ErrorResponseFactory.generic(HttpStatus.FORBIDDEN, "User is not part of the group chat");
        }

        Message message = new Message()
                .setContent(request.getMessage())
                .setGroupChat(groupChat)
                .setCreatedBy(user);

        return messageRepository.save(message);
    }
}
