package com.beldeabogdan.binovate.controllers;

import com.beldeabogdan.binovate.factories.ErrorResponseFactory;
import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.Message;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.requests.CreateMessageRequest;
import com.beldeabogdan.binovate.services.ConversationService;
import com.beldeabogdan.binovate.services.GroupChatService;
import com.beldeabogdan.binovate.services.MessageService;
import com.beldeabogdan.binovate.services.UserService;
import com.beldeabogdan.binovate.utils.IntegerParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/messages")
public class MessageController {

    private final UserService userService;
    private final MessageService messageService;
    private final GroupChatService groupChatService;
    private final ConversationService conversationService;

    @Autowired
    public MessageController(
            UserService userService,
            MessageService messageService,
            GroupChatService groupChatService,
            ConversationService conversationService
    ) {
        this.userService = userService;
        this.messageService = messageService;
        this.groupChatService = groupChatService;
        this.conversationService = conversationService;
    }

    @PostMapping
    public Message createMessage(
            @RequestHeader("user-id") String userIdString,
            @RequestBody CreateMessageRequest request
    ) {
        if (null == userIdString || null == request.getGroupChatId()) {
            throw ErrorResponseFactory.genericForbidden();
        }

        User user = userService.findById(userIdString);
        GroupChat groupChat = groupChatService.findById(request.getGroupChatId());

        return messageService.createMessage(request, user, groupChat);
    }

    @GetMapping("/{group_chat_id}")
    public Page<Message> getMessagesInChat(
            @RequestHeader("user-id") String userIdString,
            @PathVariable("group_chat_id") String groupChatIdString,
            @RequestParam(value = "page", defaultValue = "0") String pageString,
            @RequestParam(value = "itemsPerPage", defaultValue = "50") String itemsPerPageString
    ) {
        int page = IntegerParser.parse(pageString);
        int itemsPerPage = IntegerParser.parse(itemsPerPageString);

        User user = userService.findById(userIdString);
        GroupChat groupChat = groupChatService.findById(groupChatIdString);

        return conversationService.getGroupChatMessages(groupChat, user, page, itemsPerPage);
    }
}
