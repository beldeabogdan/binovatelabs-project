package com.beldeabogdan.binovate.controllers;

import com.beldeabogdan.binovate.factories.ErrorResponseFactory;
import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.Message;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.models.UserGroupChatMapping;
import com.beldeabogdan.binovate.repositories.GroupChatRepository;
import com.beldeabogdan.binovate.repositories.MessageRepository;
import com.beldeabogdan.binovate.repositories.UserGroupChatMappingRepository;
import com.beldeabogdan.binovate.repositories.UserRepository;
import com.beldeabogdan.binovate.requests.CreateMessageRequest;
import com.beldeabogdan.binovate.services.GroupChatService;
import com.beldeabogdan.binovate.services.MessageService;
import com.beldeabogdan.binovate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import java.util.NoSuchElementException;

@Controller
@RequestMapping("/v1/messages")
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;
    private final GroupChatService groupChatService;

    public MessageController(UserService userService, MessageService messageService, GroupChatService groupChatService) {
        this.userService = userService;
        this.messageService = messageService;
        this.groupChatService = groupChatService;
    }

    @PostMapping
    public Message createMessage(@RequestHeader("user-id") String userIdString, @RequestBody CreateMessageRequest request) {
        if (null == userIdString || null == request.getGroupChatId()) {
            throw ErrorResponseFactory.genericForbidden();
        }

        User user = userService.findById(userIdString);
        GroupChat groupChat = groupChatService.findById(request.getGroupChatId());

        return messageService.createMessage(request, user, groupChat);
    }
}
