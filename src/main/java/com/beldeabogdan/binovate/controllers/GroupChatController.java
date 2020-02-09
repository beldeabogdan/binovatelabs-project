package com.beldeabogdan.binovate.controllers;

import com.beldeabogdan.binovate.models.GroupChat;
import com.beldeabogdan.binovate.models.User;
import com.beldeabogdan.binovate.requests.AddParticipantRequest;
import com.beldeabogdan.binovate.services.GroupChatService;
import com.beldeabogdan.binovate.services.UserGroupChatMappingService;
import com.beldeabogdan.binovate.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/group_chat")
public class GroupChatController {

    private final UserService userService;
    private final GroupChatService groupChatService;
    private final UserGroupChatMappingService userGroupChatMappingService;

    @Autowired
    public GroupChatController(UserService userService, GroupChatService groupChatService, UserGroupChatMappingService userGroupChatMappingService) {
        this.userService = userService;
        this.groupChatService = groupChatService;
        this.userGroupChatMappingService = userGroupChatMappingService;
    }

    @PostMapping
    public GroupChat createOneAction(@RequestBody GroupChat groupChat) {
        return groupChatService.createOne(groupChat);
    }

    @PostMapping("/{group_chat_id}/participants")
    public GroupChat addParticipantAction(
            @PathVariable("group_chat_id") String groupChatIdString,
            @RequestBody AddParticipantRequest addParticipantRequest
    ) {
        GroupChat groupChat = groupChatService.findById(groupChatIdString);
        User user = userService.findById(addParticipantRequest.getUserId());
        userGroupChatMappingService.createMapping(user, groupChat);

        return groupChat;
    }

    @DeleteMapping("/{group_chat_id}/participants/{user_id}")
    public GroupChat removeParticipantAction(
            @PathVariable("group_chat_id") String groupChatIdString,
            @PathVariable("user_id") String userIdString
    ) {
        GroupChat groupChat = groupChatService.findById(groupChatIdString);
        User user = userService.findById(userIdString);

        userGroupChatMappingService.removeMapping(user, groupChat);

        return groupChat;
    }
}
