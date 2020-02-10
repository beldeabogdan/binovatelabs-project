package com.beldeabogdan.binovate.services;

import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.Message;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.repositories.MessageRepository;
import com.beldeabogdan.binovate.requests.CreateMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationSecurityService conversationSecurityService;

    @Autowired
    public MessageService(
            MessageRepository messageRepository,
            ConversationSecurityService conversationSecurityService
    ) {
        this.messageRepository = messageRepository;
        this.conversationSecurityService = conversationSecurityService;
    }

    public Message createMessage(CreateMessageRequest request, User user, GroupChat groupChat) {
        conversationSecurityService.assertUserInGroup(user, groupChat);

        Message message = new Message()
                .setContent(request.getMessage())
                .setGroupChat(groupChat)
                .setCreatedBy(user);

        return messageRepository.save(message);
    }
}
