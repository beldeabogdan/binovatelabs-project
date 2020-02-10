package com.beldeabogdan.binovate.services;

import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.Message;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    private final ConversationSecurityService conversationSecurityService;
    private final MessageRepository messageRepository;

    @Autowired
    public ConversationService(
            ConversationSecurityService conversationSecurityService,
            MessageRepository messageRepository
    ) {
        this.conversationSecurityService = conversationSecurityService;
        this.messageRepository = messageRepository;
    }

    public Page<Message> getGroupChatMessages(GroupChat groupChat, User user, int page, int itemsPerPage) {
        conversationSecurityService.assertUserInGroup(user, groupChat);

        return messageRepository.findAllByGroupChat(groupChat, PageRequest.of(page, itemsPerPage));
    }
}
